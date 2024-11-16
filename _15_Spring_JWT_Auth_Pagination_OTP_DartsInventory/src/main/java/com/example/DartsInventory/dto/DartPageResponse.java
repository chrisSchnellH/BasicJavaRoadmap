package com.example.DartsInventory.dto;

import java.util.List;

public record DartPageResponse(List<DartDto> dartDtos,
                               Integer pageNumber,
                               Integer pageSize,
                               long totalElements,
                               int totalPages,
                               boolean isLast) {
}
