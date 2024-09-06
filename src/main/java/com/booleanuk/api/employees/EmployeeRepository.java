package com.booleanuk.api.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
      employees.add(new Employee(
          result.getInt("id"),
          result.getString("name"),
          result.getString("job_name"),
          result.getString("salary_grade"),
          result.getString("department")));
    }

    return employees;
  }

  public List<Employee> get() throws ResponseStatusException {
    try {
      PreparedStatement selection = this.connection.prepareStatement("SELECT * FROM employees;");
      return EmployeeRepository.sqlResultToEmployees(selection.executeQuery());
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
    }
  }

  public Employee get(int id) throws ResponseStatusException {
    try {
      PreparedStatement selection = this.connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
      selection.setInt(1, id);
      return EmployeeRepository.sqlResultToEmployees(selection.executeQuery()).getFirst();
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
    }
  }

  public Employee create(EmployeeDTO employeeDTO) throws ResponseStatusException {
    try {
      PreparedStatement insertion = this.connection
          .prepareStatement(
              "INSERT INTO employees(name, job_name, salary_grade, department) VALUES(?, ?, ?, ?);",
              Statement.RETURN_GENERATED_KEYS);
      insertion.setString(1, employeeDTO.name());
      insertion.setString(2, employeeDTO.jobName());
      insertion.setString(3, employeeDTO.salaryGrade());
      insertion.setString(4, employeeDTO.department());
      insertion.executeUpdate();

      ResultSet resultSet = insertion.getGeneratedKeys();
      resultSet.next();
      int createdId = resultSet.getInt(1);

      return new Employee(createdId, employeeDTO.name(), employeeDTO.jobName(), employeeDTO.salaryGrade(),
          employeeDTO.department());
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
    }
  }

  public Employee update(int id, EmployeeDTO employeeDTO) throws ResponseStatusException {
    return null;
  }

  public Employee delete(int id) throws ResponseStatusException {
    return null;
  }
}
