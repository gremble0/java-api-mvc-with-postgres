package com.booleanuk.api.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeRepository {
  private Connection connection;

  public EmployeeRepository(Connection connection) {
    this.connection = connection;
  }

  private static List<Employee> sqlResultToEmployees(ResultSet result) throws SQLException {
    List<Employee> employees = new ArrayList<>();
    while (result.next()) {
      int id = result.getInt("id");
      String name = result.getString("name");
      String jobName = result.getString("jobName");
      String salaryGrade = result.getString("salaryGrade");
      String department = result.getString("department");
      employees.add(new Employee(id, name, jobName, salaryGrade, department));
    }

    return employees;
  }

  public List<Employee> get() throws ResponseStatusException {
    try {
      PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM employees");
      return EmployeeRepository.sqlResultToEmployees(statement.executeQuery());
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  public Employee get(int id) throws ResponseStatusException {
    return null;
  }

  public Employee create(EmployeeDTO employeeDTO) throws ResponseStatusException {
    try {
      PreparedStatement statement = this.connection
          .prepareStatement(
              "INSERT INTO employees(name, job_name, salary_grade, department) VALUES('?', '?', '?', '?')");
      statement.setString(1, employeeDTO.name());
      statement.setString(2, employeeDTO.jobName());
      statement.setString(3, employeeDTO.salaryGrade());
      statement.setString(4, employeeDTO.department());
      statement.executeQuery();

      return null;
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  public Employee update(int id, EmployeeDTO employeeDTO) throws ResponseStatusException {
    return null;
  }

  public Employee delete(int id) throws ResponseStatusException {
    return null;
  }
}
