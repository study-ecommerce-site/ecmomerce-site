package com.teckstudy.book.exhibition;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.common.WebIntegrationTest;
import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.entity.enums.ContentEnum;
import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import com.teckstudy.book.lib.common.BoValidation;
import com.teckstudy.book.lib.common.UploadResult;
import com.teckstudy.book.product.repository.ContentsTypeRepository;
import com.teckstudy.book.product.repository.ExhibitionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class ExhibitionTest extends WebIntegrationTest {


    @Autowired // 또는 자바 표준 스택 @PersistenceContext 최신버전부터 @Autowired 지원 됨
    EntityManager em;

    // queryDsl 선언
    JPAQueryFactory queryFactory;

    @Autowired
    ExhibitionRepository exhibitionRepository;

    @Autowired
    ContentsTypeRepository contentsTypeRepository;

    private Random random = new Random();

    @Test
    @DisplayName("메뉴명은 20자를 넘으면 Exception을 발생시킨다.")
    public void twentyKorCharMenuTest() {
        //given
        String keyWord = "데이터 검증단어 글자수 초과합니다. 20자 넘어감";
        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation(keyWord));
    }

    @Test
    @DisplayName("최소 1개의 카테고리가 없으면 Exception을 발생시킨다.")
    public void categoryMinTest() {
        //given
        List<String> categories = new ArrayList<>();

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation().categoryValidation(categories));
    }

    @Test
    @DisplayName("카테고리가 10개를 넘어으면 Exception을 발생시킨다.")
    public void categoryMaxTest() {
        //given
        List<String> categories = new ArrayList<>();
        String category = "카테고리명";

        //when
        for (int i = 1; i <= 11; i++) {
            categories.add(category + i);
        }

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation().categoryValidation(categories));
    }

    @Test
    @DisplayName("전시코너 기본정보 등록 테스트 or 컨텐츠 타입 등록한 갯수가 4개인지 검증")
    public void exhibitionReqTest() {
        //given
        Map<ContentEnum, Integer> contentMap = new LinkedHashMap<>();

        contentMap.put(ContentEnum.PRODUCT, 8);
        contentMap.put(ContentEnum.TEXT, 9);
        contentMap.put(ContentEnum.IMAGE, 10);
        contentMap.put(ContentEnum.VIDEO, 5);

        Exhibition exData = Exhibition.builder()
                .name("이 달의 추천 도서")
                .use_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .date_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .image("아들과아버지.jpg")
                .exhibitionType(random.nextInt(2) % 2 == 0 ? ExhibitionType.IMAGE : ExhibitionType.TEXT)
                .description("자유롭게 설명합니다")
                .url("www.naver.com")
                .build();
        exhibitionRepository.save(exData);

        Optional<Exhibition> exhibitionSn = exhibitionRepository.findById(10000001L);

        for (ContentEnum contentEnum : contentMap.keySet()) {
            ContentsType contentsData = ContentsType.builder()
                    .exhibition(exhibitionSn.get())
                    .contentEnum(contentEnum)
                    .contentCnt(contentMap.get(contentEnum))
                    .build();
            contentsTypeRepository.save(contentsData);
        }

        //when
        List<ContentsType> contentsTypeList = contentsTypeRepository.findAll();

        //then
        assertThat(contentsTypeList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("파일 업로드시 10MB를 초과하면 Exception을 발생시킨다.")
    public void fileUploadTest() throws IOException {


        Path path = Paths.get("C:/Programer/imageTest/bigImage.bmp");

        long bytes = Files.size(path);
        long kilobyte = bytes / 1024;
        long megabyte = kilobyte / 1024;
        System.out.println(bytes + " byte"); // 12441654 byte
        System.out.println(kilobyte + " kb"); // 12150 kb
        System.out.println(megabyte + " mb"); // 11 mb

        //then
        assertTrue(megabyte > 10);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BoValidation().multiFileUpload(bytes));
    }

    @Test
    @DisplayName("서버에서 넘겨준 파일 업로드시 10MB를 초과하면 Exception을 발생시킨다.")
    public void fileServerUploadTest() {

        MultiValueMap form = new LinkedMultiValueMap();
        form.add("file", new ClassPathResource("testimage.jpg"));
        HttpEntity request = new HttpEntity(form, null);

        ResponseEntity<UploadResult> response = client.postForEntity(url("/upload"), request, UploadResult.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("/files/testimage.jpg", response.getBody().getPath());
    }

}