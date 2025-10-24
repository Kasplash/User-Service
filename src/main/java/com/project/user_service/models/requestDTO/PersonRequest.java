package com.project.user_service.models.requestDTO;


import jakarta.validation.constraints.NotNull;

public record PersonRequest(@NotNull long ssn,@NotNull String name, @NotNull String address, @NotNull int age) {
}
