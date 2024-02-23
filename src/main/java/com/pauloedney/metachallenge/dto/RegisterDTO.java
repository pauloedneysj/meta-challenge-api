package com.pauloedney.metachallenge.dto;

import com.pauloedney.metachallenge.enums.UserRole;

public record RegisterDTO(String firstName, String lastName, String username, String password, UserRole role) {
}
