package org.joinfaces.example.view;


import lombok.Getter;
import org.joinfaces.example.entities.Employee;
import org.joinfaces.example.repositories.EmployeeRepository;
import org.joinfaces.example.services.DataService;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
@Getter
public class EmployeeBean {
    EmployeeLazyDataModel dataModel = new EmployeeLazyDataModel();

    public LazyDataModel<Employee> getModel(){
        return dataModel;
    }

//    private List<Employee> employeeList;
//    @Autowired
//    EmployeeRepository employeeRepository;
//
//    @PostConstruct
//    public void init(){
//        this.employeeList = employeeRepository.findAll();
//    }
}
