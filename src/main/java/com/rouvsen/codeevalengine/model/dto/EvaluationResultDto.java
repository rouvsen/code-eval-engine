package com.rouvsen.codeevalengine.model.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class EvaluationResultDto {
    boolean success;
    String message;
    String output;
    long executionTimeMillis;
    long memoryUsageBytes;
}
