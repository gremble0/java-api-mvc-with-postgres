package com.booleanuk.api.salaries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.booleanuk.api.base.Repository;

public class SalaryRepository implements Repository<Salary, Salary> {
  private final Connection connection;

  public SalaryRepository(Connection connection) {
    this.connection = connection;
  }

  private static List<Salary> sqlResultToSalaries(ResultSet result) throws SQLException {
    List<Salary> salaries = new ArrayList<>();
    while (result.next()) {
      salaries.add(new Salary(
          result.getString("grade"),
          result.getInt("min_salary"),
          result.getInt("max_salary")));
    }

    return salaries.stream()
        .sorted((salary1, salary2) -> Integer.parseInt(salary1.grade()) - Integer.parseInt(salary2.grade()))
        .toList();
  }

  public List<Salary> get() throws ResponseStatusException {
    try {
      PreparedStatement selection = this.connection.prepareStatement("SELECT * FROM employees;");
      return SalaryRepository.sqlResultToSalaries(selection.executeQuery());
    } catch (SQLException e) {
      throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
    }
  }

  public Salary get(int id) throws ResponseStatusException {
    return null;
  }

  public Salary post(Salary salary) throws ResponseStatusException {
    return null;
  }

  public Salary put(int id, Salary salary) throws ResponseStatusException {
    return null;
  }

  public Salary delete(int id) throws ResponseStatusException {
    return null;
  }
}
