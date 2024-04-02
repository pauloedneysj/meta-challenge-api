package com.pauloedney.metachallenge.models.dtos;

import java.util.Date;

public record ProductResponseDTO(String name, String code, String userId, Boolean sent, Date createdAt) {
}
