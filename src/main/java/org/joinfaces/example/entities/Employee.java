package org.joinfaces.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @SequenceGenerator(name = "eSGen", sequenceName = "employeeSeq",
            initialValue = 50)
    @GeneratedValue(generator = "eSGen")
    private long id;
    private String name;
    private String phoneNumber;
    private String address;
private String department;

}