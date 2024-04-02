package com.pauloedney.metachallenge.models.dtos;

import com.pauloedney.metachallenge.models.enums.UserRole;

public record RegisterDTO(String firstName, String lastName, String username, String password, UserRole role) {
}
