package com.amadeus.testelasticsearch.controller;

import com.amadeus.testelasticsearch.dao.EmployeeRepository;
import com.amadeus.testelasticsearch.entity.Employee;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id) {
        Gson gson = new Gson();
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("Wang");
        employee.setLastName("Xinpeng");
        employee.setAge(22);
        Employee save = employeeRepository.save(employee);
        return gson.toJson(save);
    }
}
