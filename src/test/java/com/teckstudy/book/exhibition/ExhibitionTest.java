package com.teckstudy.book.exhibition;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teckstudy.book.common.WebIntegrationTest;
import com.teckstudy.book.entity.ContentsType;
import com.teckstudy.book.entity.Exhibition;
import com.teckstudy.book.entity.enums.ContentEnum;
import com.teckstudy.book.entity.enums.ExhibitionType;
import com.teckstudy.book.entity.enums.YesNoStatus;
import com.teckstudy.book.exhibition.repository.ContentsTypeRepository;
import com.teckstudy.book.exhibition.repository.ExhibitionRepository;
import com.teckstudy.book.lib.common.util.BoValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("전시코너명은 20자를 넘으면 Exception을 발생시킨다.")
    public void twentyExhibitionTest() {
        //given
        String keyWord = "이 달의 추천 도서 글자수 초과합니다. 20자 넘어감";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new BoValidation().nameValidation(keyWord));
//        assertThatExceptionOfType(IllegalArgumentException.class)
//                .isThrownBy(() -> new BoValidation(keyWord));
    }

    @Test
    @DisplayName("같은 유형을 등록하면 Exception을 발생시킨다.")
    public void duplicateContentTest() {
        //given
        Map<ContentEnum, Integer> contentMap = new LinkedHashMap<>();
        List<ContentEnum> contentEnums = Arrays.asList(ContentEnum.PRODUCT, ContentEnum.PRODUCT, ContentEnum.TEXT, ContentEnum.IMAGE, ContentEnum.VIDEO);
        int bundleMaxCnt = 3;

        for (ContentEnum content : contentEnums) {
            contentMap.put(content, contentMap.getOrDefault(content, 0) +1);
        }

        //when
        String start = "2021-10-30 12:30";
        String end = "2021-11-05 13:50";
        Exhibition exData = Exhibition.builder()
                .name("이 달의 추천 도서")
                .use_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .date_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .exhibition_start(start)
                .exhibition_end(end)
                .image(String.valueOf(Paths.get("C:/Programer/imageTest/bigImage.bmp")))
                .exhibitionType(ExhibitionType.IMAGE)
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

        List<ContentsType> contentsTypeList = contentsTypeRepository.findAll();

        //then
        for (ContentEnum key : contentMap.keySet()) {
            if(contentMap.get(key).equals(2)) {
                assertThrows(IllegalArgumentException.class,
                        () -> new BoValidation().boContentValidation(contentMap, contentsTypeList, bundleMaxCnt));
                System.out.println("key : " + key + ", value : " + contentMap.get(key));
                break;
            }
        }
    }

    @Test
    @DisplayName("묶을 때 2개 이하 유형을 등록하면 Exception을 발생시킨다.")
    public void oneContentTest() {
        //given
        Map<ContentEnum, Integer> contentMap = new LinkedHashMap<>();
        List<ContentEnum> contentEnums = Arrays.asList(ContentEnum.PRODUCT);
        int bundleMaxCnt = 3;

        for (ContentEnum content : contentEnums) {
            contentMap.put(content, contentMap.getOrDefault(content, 0) +1);
        }
        //when
        String start = "2021-10-30 12:30";
        String end = "2021-11-05 13:50";
        Exhibition exData = Exhibition.builder()
                .name("이 달의 추천 도서")
                .use_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .date_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .exhibition_start(start)
                .exhibition_end(end)
                .image(String.valueOf(Paths.get("C:/Programer/imageTest/bigImage.bmp")))
                .exhibitionType(ExhibitionType.IMAGE)
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

        List<ContentsType> contentsTypeList = contentsTypeRepository.findAll();

        //then
        for (ContentEnum key : contentMap.keySet()) {
            assertThrows(IllegalArgumentException.class,
                    () -> new BoValidation().boContentValidation(contentMap, contentsTypeList, bundleMaxCnt));
        }
    }

    @Test
    @DisplayName("묶을 때 선택한 유형이 없으면 Exception을 발생시킨다.")
    public void zeroContentTest() {
        //given
        Map<ContentEnum, Integer> contentMap = new LinkedHashMap<>();
        List<ContentEnum> contentEnums = new ArrayList<>();
        int bundleMaxCnt = 3;

        for (ContentEnum content : contentEnums) {
            contentMap.put(content, contentMap.getOrDefault(content, 0) +1);
        }

        //when
        String start = "2021-10-30 12:30";
        String end = "2021-11-05 13:50";
        Exhibition exData = Exhibition.builder()
                .name("이 달의 추천 도서")
                .use_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .date_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .exhibition_start(start)
                .exhibition_end(end)
                .image(String.valueOf(Paths.get("C:/Programer/imageTest/bigImage.bmp")))
                .exhibitionType(ExhibitionType.IMAGE)
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

        List<ContentsType> contentsTypeList = contentsTypeRepository.findAll();

        //then
        assertThrows(IllegalArgumentException.class,
                () -> new BoValidation().boContentValidation(contentMap, contentsTypeList, bundleMaxCnt));
    }

    @Test
    @DisplayName("전시코너명을 입력하지 않으면 Exception을 발생시킨다.")
    public void noExhibitionTest() {
        //given
        String keyWord = "";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new BoValidation().nameValidation(keyWord));
    }

    @Test
    @DisplayName("전시코너명에 노출할 이미지를 등록하지 않으면 Exception을 발생시킨다.")
    public void noExhibitionImageTest() {
        //given
        ExhibitionType imageType = ExhibitionType.IMAGE;
        String image = "";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new BoValidation().imageValidation(imageType, image));
    }

    @Test
    @DisplayName("전시기간 및 일시를 입력하지 않으면 Exception을 발생시킨다.")
    public void noDateTest() {
        //given
        YesNoStatus date_yn = YesNoStatus.Y;
        String start = "";
        String end = "";

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new BoValidation().dateValidation(date_yn, start, end));
    }

    @Test
    @DisplayName("전시기간의 종료일이 시작일보다 빠르면 Exception을 발생시킨다.")
    public void noEndDateStartDateTest() {
        //given
        YesNoStatus date_yn = YesNoStatus.Y;
        String start = "2021-11-05 13:50";
        String end = "2021-10-30 12:30";

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new BoValidation().dateValidation(date_yn, start, end));
    }

    @Test
    @DisplayName("컨텐츠 유형을 등록하지 않으면 Exception을 발생시킨다.")
    public void noContentTypeTest() {
//        //given

//        //when

//        //then
    }

    @Test
    @DisplayName("전시코너 기본정보 등록 테스트 or 컨텐츠 타입 등록한 갯수가 4개인지 검증")
    public void exhibitionReqTest() {
        //given
//        LocalDateTime start = LocalDateTime.parse("2021-10-30 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime end = LocalDateTime.parse("2021-11-05 13:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String start = "2021-10-30 12:30";
        String end = "2021-11-05 13:50";

        Map<ContentEnum, Integer> contentMap = new LinkedHashMap<>();
        List<ContentEnum> contentEnums = Arrays.asList(ContentEnum.PRODUCT, ContentEnum.TEXT, ContentEnum.IMAGE, ContentEnum.VIDEO);

        for (ContentEnum content : contentEnums) {
            contentMap.put(content, contentMap.getOrDefault(content, 0) +1);
        }

        Exhibition exData = Exhibition.builder()
                .name("이 달의 추천 도서")
                .use_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .date_yn(random.nextInt(2) % 2 == 0 ? YesNoStatus.Y : YesNoStatus.N)
                .exhibition_start(start)
                .exhibition_end(end)
                .image(String.valueOf(Paths.get("C:/Programer/imageTest/bigImage.bmp")))
                .exhibitionType(ExhibitionType.IMAGE)
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
}