package com.pauloedney.metachallenge.dto;

import com.pauloedney.metachallenge.enums.UserRole;

public record MeResponseDTO(String id, String username, String firstName, String lastName, UserRole role) {
}
