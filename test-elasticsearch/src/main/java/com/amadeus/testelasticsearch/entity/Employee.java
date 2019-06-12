package com.amadeus.testelasticsearch.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 7796061224907915567L;

    private String id;

    private String firstName;

    private String lastName;

    private Integer age = 0;

    private String about;

}
