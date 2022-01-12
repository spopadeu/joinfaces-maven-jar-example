package org.joinfaces.example.view;


import lombok.Getter;
import org.joinfaces.example.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Component
@ViewScoped
@Getter
public class EmployeeBean {

    private EmployeeLazyDataModel dataModel;

    private EmployeeRepository employeeRepository;

    @Inject
    public EmployeeBean(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.dataModel = new EmployeeLazyDataModel(employeeRepository);
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
