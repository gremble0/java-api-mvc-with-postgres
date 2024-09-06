package com.booleanuk.api.employees;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booleanuk.api.base.Controller;
import com.booleanuk.api.database.DatabaseConnector;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController extends Controller<Employee, EmployeeDTO> {
  public EmployeeController() throws SQLException {
    super(new EmployeeRepository(DatabaseConnector.getConnection()));
  }

  @GetMapping
  public ResponseEntity<List<Employee>> get() {
    return new ResponseEntity<>(this.repository.get(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Employee> get(@PathVariable int id) {
    return new ResponseEntity<>(this.repository.get(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Employee> post(@RequestBody EmployeeDTO employeeDTO) {
    return new ResponseEntity<>(this.repository.post(employeeDTO), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Employee> put(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
    return new ResponseEntity<>(this.repository.put(id, employeeDTO), HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Employee> delete(@PathVariable int id) {
    return new ResponseEntity<>(this.repository.delete(id), HttpStatus.OK);
  }
}
