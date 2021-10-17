package com.teckstudy.book.entity.enums;

import lombok.Getter;

@Getter
public enum ContentEnum {

    PRODUCT("상품", 10),
    TEXT("텍스트", 20),
    IMAGE("이미지", 30),
    VIDEO("동영상", 40),
    HTML("HTML", 50),
    CONTENTS("개발컨텐츠", 60);

    private String legacyName;
    private int legacyCode;

    ContentEnum(String legacyName, int legacyCode) {
        this.legacyName = legacyName;
        this.legacyCode = legacyCode;
    }
}