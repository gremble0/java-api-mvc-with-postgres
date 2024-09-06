package com.booleanuk.api.salaries;

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

import com.booleanuk.api.database.DatabaseConnector;

@RestController
@RequestMapping(value = "/salaries")
public class SalaryController {
  private final SalaryRepository repository;

  public SalaryController() throws SQLException {
    this.repository = new SalaryRepository(DatabaseConnector.getConnection());
  }

  @GetMapping
  public ResponseEntity<List<Salary>> get() {
    return new ResponseEntity<>(this.repository.get(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Salary> get(@PathVariable int id) {
    return new ResponseEntity<>(this.repository.get(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Salary> post(@RequestBody Salary salary) {
    return new ResponseEntity<>(this.repository.create(salary), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Salary> put(@PathVariable int id, @RequestBody Salary salary) {
    return new ResponseEntity<>(this.repository.update(id, salary), HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Salary> delete(@PathVariable int id) {
    return new ResponseEntity<>(this.repository.delete(id), HttpStatus.OK);
  }
}
