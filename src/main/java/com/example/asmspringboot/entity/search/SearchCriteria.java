package com.example.asmspringboot.entity.search;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private SearchCriteriaOperator operator;
    private Object value;
}
