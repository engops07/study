package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok @Data annotation get/set method 자동 생성
// java code 줄여주며, 생산성 향상

// Entity : JPA에서는 테이블을 자동으로 생성해주는 기능
// DB Table == JPA Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam {

    private String account;
    private String email;
    private int page;
}
