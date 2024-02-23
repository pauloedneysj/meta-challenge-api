package com.pauloedney.metachallenge.dto;

import java.util.Date;

public record ProductResponseDTO(String name, String code, String userId, Boolean sent, Date createdAt) {
}
