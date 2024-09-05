package com.booleanuk.api.model;

public record Employee(
    int id,
    String name,
    String jobName,
    String salaryGrade,
    String department) {
}
