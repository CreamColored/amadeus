package com.amadeus.testelasticsearch.controller;

import com.amadeus.testelasticsearch.entity.Employee;
import com.google.gson.Gson;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id) {
        Gson gson = new Gson();
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("王");
        employee.setLastName("鑫鹏");
        employee.setAge(22);
        employee.setAbout("我叫王鑫鹏");
        return gson.toJson(employee);
    }

}
