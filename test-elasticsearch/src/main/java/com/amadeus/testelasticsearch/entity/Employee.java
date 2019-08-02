package com.amadeus.testelasticsearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "testEmployee",type = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 436111711625436698L;

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Integer age = 0;

    private String about;
}
