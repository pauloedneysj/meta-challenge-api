package com.pauloedney.metachallenge.models.dtos;

import com.pauloedney.metachallenge.models.enums.UserRole;

public record MeResponseDTO(String id, String username, String firstName, String lastName, UserRole role) {
}
