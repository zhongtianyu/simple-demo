package com.sz.entity;

import lombok.*;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/9/7 15:53
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {

    private String name;

    private Integer status;

    public Person(String name, Integer status) {
        this.name = name;
        this.status = status;
    }
}
