package com.rouvsen.codeevalengine.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class CompilationResult {
    final boolean success;
    final String errors;
}
