package com.amadeus.testelasticsearch;

import com.amadeus.testelasticsearch.dao.EmployeeRepository;
import com.amadeus.testelasticsearch.entity.Employee;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestElasticsearchApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void add() {
        Gson gson = new Gson();
        Employee employee = new Employee();
        employee.setId("123456");
        employee.setFirstName("Wang");
        employee.setLastName("Xinpeng");
        employee.setAge(22);
        Employee save = employeeRepository.save(employee);
        System.out.println(gson.toJson(save));
    }

}
