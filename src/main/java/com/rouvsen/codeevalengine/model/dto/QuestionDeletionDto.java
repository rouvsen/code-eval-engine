package com.rouvsen.codeevalengine.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDeletionDto {
    List<Long> codeSubmissions;
}
