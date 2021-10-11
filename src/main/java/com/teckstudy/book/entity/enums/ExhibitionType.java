package com.teckstudy.book.entity.enums;

public enum ExhibitionType {

    TEXT("텍스트", "10"),
    IMAGE("이미지", "20"),
    UNEXPOSED("비노출", "30");

    private final String name;
    private final String code;

    ExhibitionType(String name, String code) {
        this.name = name;
        this.code = code;
    }

}