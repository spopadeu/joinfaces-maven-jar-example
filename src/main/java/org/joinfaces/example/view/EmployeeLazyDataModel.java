package org.joinfaces.example.view;

import org.joinfaces.example.entities.Employee;
import org.joinfaces.example.repositories.EmployeeRepository;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeLazyDataModel extends LazyDataModel<Employee> {


    private EmployeeRepository employeeRepository;

    public EmployeeLazyDataModel(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.setRowCount((int) employeeRepository.count());
    }

    @Override
    // PF Datatable
    public List<Employee> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        int page = offset / pageSize;
        // Spring Data JPA
        Pageable pageable = PageRequest.of(page, pageSize);
        Sort sort = null;
        Page<Employee> list;
        if (!sortBy.isEmpty()) {
            String first = sortBy.keySet().stream().findFirst().get();
            sort = Sort.by(first);
            SortMeta sortMeta = sortBy.get(first);
            if (sortMeta.getOrder().isDescending()) {
                sort = sort.descending();
            } else {
                sort = sort.ascending();
            }
            list = employeeRepository.findAll(PageRequest.of(page, pageSize,sort));
        } else {
            list = employeeRepository.findAll(PageRequest.of(page, pageSize));
        }
        return list.toList();
    }

//    @Override
//    public List<Employee> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy){
//        int page = offset / pageSize ;
//
//        Sort sort= Sort.by(sortField);
//        if(sortOrder.isDescending()){
//            sort=sort.descending();
//        }else{
//            sort=sort.ascending();
//        }
//
//
//        // Spring Data JPA
//        Pageable pageable = PageRequest.of(page, pageSize, sort);
//
//        Page <Employee> list = employeeRepository.findAll(pageable);
//        return list.toList();
//    }

}
