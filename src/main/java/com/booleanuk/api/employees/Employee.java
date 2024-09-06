package com.booleanuk.api.employees;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Employee(
    int id,
    String name,
    String jobName,
    String salaryGrade,
    String department) {

  public record DTO(
      @JsonProperty(required = true) String name,

      @JsonProperty(required = true) String jobName,

      @JsonProperty(required = true) String salaryGrade,

      @JsonProperty(required = true) String department) {
  }
}
