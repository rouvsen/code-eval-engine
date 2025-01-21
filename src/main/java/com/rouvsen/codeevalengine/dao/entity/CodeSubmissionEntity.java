package com.rouvsen.codeevalengine.dao.entity;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

import com.rouvsen.codeevalengine.model.enums.ProgrammingLanguage;
import com.rouvsen.codeevalengine.model.enums.QuestionCategory;
import com.rouvsen.codeevalengine.model.enums.QuestionPurpose;
import com.rouvsen.codeevalengine.model.enums.QuestionType;
import com.rouvsen.codeevalengine.model.enums.ResponseType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@FieldDefaults(level = PRIVATE)
@Table(name = "code_submission")
public class CodeSubmissionEntity extends BaseEntity {

    String question;
    String result;

    @Enumerated(STRING)
    ResponseType responseType;

    @Enumerated(STRING)
    QuestionType questionType;

    @Enumerated(STRING)
    QuestionPurpose questionPurpose;

    @Enumerated(STRING)
    QuestionCategory questionCategory;

    @Enumerated(STRING)
    ProgrammingLanguage programmingLanguage;

}
