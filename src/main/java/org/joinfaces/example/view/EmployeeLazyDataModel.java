package org.joinfaces.example.view;

import org.joinfaces.example.entities.Employee;
import org.joinfaces.example.repositories.EmployeeRepository;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public class EmployeeLazyDataModel  extends LazyDataModel<Employee> {


    private EmployeeRepository employeeRepository;

    public EmployeeLazyDataModel(){
        this.setRowCount((int) employeeRepository.count());
    }

    @Override
    public List<Employee> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        Pageable firstPageWithTwoElements = PageRequest.of(first, pageSize);

        Page <Employee> list = employeeRepository.findAll(firstPageWithTwoElements);
        return list.toList();
    }


}
