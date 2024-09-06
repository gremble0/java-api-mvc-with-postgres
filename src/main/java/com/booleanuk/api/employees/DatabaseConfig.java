package com.booleanuk.api.employees;

public record DatabaseConfig(
    String dbUser,
    String dbUrl,
    String dbPassword) {
}
