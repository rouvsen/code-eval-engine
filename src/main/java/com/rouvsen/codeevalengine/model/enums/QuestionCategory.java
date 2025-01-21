package com.rouvsen.codeevalengine.model.enums;

import lombok.Getter;

@Getter
public enum QuestionCategory {
    ALGORITHM_IMPLEMENTATION("Algorithm Implementation"),
    DATA_STRUCTURE("Data Structure Implementation"),
    DEBUGGING("Debug Existing Code"),
    CODE_COMPLETION("Complete Missing Code"),
    OPTIMIZATION("Code Optimization"),
    REFACTORING("Code Refactoring"),
    SYSTEM_DESIGN("System Design Implementation"),
    API_INTEGRATION("API Implementation"),
    DATABASE_QUERY("Database Query Writing"),
    UNIT_TEST("Unit Test Writing");

    private final String displayName;

    QuestionCategory(String displayName) {
        this.displayName = displayName;
    }
}
