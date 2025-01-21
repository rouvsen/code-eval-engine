package com.rouvsen.codeevalengine.model.enums;

import lombok.Getter;

@Getter
public enum QuestionType {
    MULTIPLE_CHOICE("Multiple Choice", ResponseType.CLOSED),
    SINGLE_CHOICE("Single Choice", ResponseType.CLOSED),
    YES_NO("Yes/No", ResponseType.CLOSED),
    TRUE_FALSE("True/False", ResponseType.CLOSED),
    LIKERT_SCALE("Likert Scale", ResponseType.CLOSED),
    RATING_SCALE("Rating Scale", ResponseType.CLOSED),
    RANKING("Ranking", ResponseType.CLOSED),
    MATRIX("Matrix", ResponseType.CLOSED),

    SHORT_ANSWER("Short Answer", ResponseType.OPEN),
    LONG_ANSWER("Long Answer/Essay", ResponseType.OPEN),
    OPINION("Opinion", ResponseType.OPEN),
    FILE_UPLOAD("File Upload", ResponseType.OPEN),

    NUMERIC("Numeric", ResponseType.HYBRID),
    DATE_TIME("Date/Time", ResponseType.HYBRID),
    SEMANTIC_DIFFERENTIAL("Semantic Differential", ResponseType.HYBRID);

    private final String displayName;
    private final ResponseType responseType;

    QuestionType(String displayName, ResponseType responseType) {
        this.displayName = displayName;
        this.responseType = responseType;
    }

    public boolean isClosedEnded() {
        return responseType == ResponseType.CLOSED;
    }

    public boolean isOpenEnded() {
        return responseType == ResponseType.OPEN;
    }
}
