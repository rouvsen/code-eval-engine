package com.rouvsen.codeevalengine.model.enums;

import lombok.Getter;

@Getter
public enum QuestionPurpose {
    DEMOGRAPHIC("Demographic Information"),
    BEHAVIORAL("Behavioral Assessment"),
    ATTITUDINAL("Attitude Measurement"),
    KNOWLEDGE("Knowledge Testing"),
    SCREENING("Screening/Qualification"),
    FEEDBACK("Feedback Collection");

    private final String description;

    QuestionPurpose(String description) {
        this.description = description;
    }
}
