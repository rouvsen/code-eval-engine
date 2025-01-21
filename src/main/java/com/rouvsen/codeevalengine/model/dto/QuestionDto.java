package com.rouvsen.codeevalengine.model.dto;

import static lombok.AccessLevel.PRIVATE;

import com.rouvsen.codeevalengine.model.enums.ProgrammingLanguage;
import com.rouvsen.codeevalengine.model.enums.QuestionCategory;
import com.rouvsen.codeevalengine.model.enums.QuestionPurpose;
import com.rouvsen.codeevalengine.model.enums.QuestionType;
import com.rouvsen.codeevalengine.model.enums.ResponseType;
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
public class QuestionDto {

    Long id;
    String question;
    String result;
    ResponseType responseType;
    QuestionType questionType;
    QuestionPurpose questionPurpose;
    QuestionCategory questionCategory;
    ProgrammingLanguage programmingLanguage;

}
