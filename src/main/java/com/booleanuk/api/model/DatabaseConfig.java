package com.booleanuk.api.model;

public record DatabaseConfig(
    String dbUser,
    String dbUrl,
    String dbPassword,
    String dbDatabase) {
}
