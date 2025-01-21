package com.rouvsen.codeevalengine.service;

import com.rouvsen.codeevalengine.compiler.InMemoryJavaCompiler;
import com.rouvsen.codeevalengine.model.CompilationResult;
import com.rouvsen.codeevalengine.model.dto.CodeSubmissionDto;
import com.rouvsen.codeevalengine.model.dto.EvaluationResultDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@Service
public class QuizEvaluationService {

    private final InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();

    public EvaluationResultDto evaluateSolution(CodeSubmissionDto submission) {
        String className = extractClassName(submission.getSourceCode());
        if (className == null) {
            return new EvaluationResultDto(false,
                    "Could not extract class name from source",
                    "",
                    0L,
                    0L
            );
        }

        CompilationResult result = compiler.compile(className, submission.getSourceCode());
        if (!result.isSuccess()) {
            return new EvaluationResultDto(false,
                    "Compilation Failed",
                    result.getErrors(),
                    0L,
                    0L
            );
        }

        long usedMemoryBefore = getUsedMemory();
        long startTime = System.currentTimeMillis();

        try {
            File currentDir = new File(System.getProperty("user.dir"));
            URLClassLoader classLoader = new URLClassLoader(new URL[]{currentDir.toURI().toURL()});
            Class<?> compiledClass = classLoader.loadClass(className);
            Method solveMethod = compiledClass.getDeclaredMethod("solve", String.class);

            String input = "rouvsen";
            Object solveOutput = solveMethod.invoke(null, input);

            long usedMemoryAfter = getUsedMemory();
            long endTime = System.currentTimeMillis();

            boolean correct = """
                    *
                    **
                    ***
                    """.equals(solveOutput);
            if (correct) {
                return new EvaluationResultDto(true,
                        "Correct Answer",
                        String.valueOf(solveOutput),
                        (endTime - startTime),
                        (usedMemoryAfter - usedMemoryBefore)
                );
            } else {
                return new EvaluationResultDto(false,
                        "Wrong Answer",
                        String.valueOf(solveOutput),
                        (endTime - startTime),
                        (usedMemoryAfter - usedMemoryBefore)
                );
            }

        } catch (Exception e) {
            return new EvaluationResultDto(false,
                    "Runtime Error",
                    e.getMessage(),
                    0L,
                    0L
            );
        }
    }

    private String extractClassName(String sourceCode) {
        String keyword = "class ";
        int index = sourceCode.indexOf(keyword);
        if (index == -1) return null;
        int start = index + keyword.length();
        int end = start;
        while (end < sourceCode.length() && Character.isJavaIdentifierPart(sourceCode.charAt(end))) {
            end++;
        }
        if (start == end) return null;
        return sourceCode.substring(start, end);
    }

    private long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
