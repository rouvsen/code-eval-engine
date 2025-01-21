package com.rouvsen.codeevalengine.model.dto;

import static lombok.AccessLevel.PRIVATE;

import com.rouvsen.codeevalengine.dao.entity.CodeSubmissionEntity;
import java.util.List;
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
public class QuestionGroupDto {
    String name;
    List<CodeSubmissionEntity> codeSubmissions;
}
