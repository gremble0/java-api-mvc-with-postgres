package com.booleanuk.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.booleanuk.api.model.Employee;
import com.booleanuk.api.model.EmployeeDTO;

public class EmployeeRepository {
  private int idCounter = 1;
  // TODO: remove this, use postgres DB
  private List<Employee> employees = new ArrayList<>();

  public List<Employee> getAll() {
    return this.employees;
  }

  public Employee get(int id) throws ResponseStatusException {
    return this.employees.stream()
        .filter(employee -> employee.id() == id)
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Employee create(EmployeeDTO employeeDTO) throws ResponseStatusException {
    Employee employee = new Employee(this.idCounter++, employeeDTO.name(), employeeDTO.jobName(),
        employeeDTO.salaryGrade(), employeeDTO.department());
    this.employees.add(employee);
    return employee;
  }

  public Employee update(int id, EmployeeDTO employeeDTO) throws ResponseStatusException {
    Employee oldEmployee = this.get(id);
    Employee newEmployee = new Employee(oldEmployee.id(), employeeDTO.name(), employeeDTO.jobName(),
        employeeDTO.salaryGrade(), employeeDTO.department());
    this.employees.set(this.employees.indexOf(oldEmployee), newEmployee);

    return newEmployee;
  }

  public Employee delete(int id) {
    Employee toRemove = this.get(id);
    this.employees.remove(toRemove);

    return toRemove;
  }
}
