package com.teckstudy.book.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesNoStatus {
    TRUE("Y"),
    FALSE("N")
    ;

    private final String value;
}
