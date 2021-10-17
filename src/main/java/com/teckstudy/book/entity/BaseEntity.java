package com.teckstudy.book.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // 매핑정보 속성만 받는 슈퍼클래스 자주씀
@Getter
@Setter
public class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false) // 등록 안되게
    private LocalDate regDate;
    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDate modDate;

    @CreatedBy
    @Column(name = "regdatedby")
    private String regDateBy;

    @LastModifiedBy
    @Column(name = "moddatedby")
    private String modDatedBy;


}
