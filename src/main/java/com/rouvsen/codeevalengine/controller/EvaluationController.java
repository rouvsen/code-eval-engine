package com.rouvsen.codeevalengine.controller;

import static lombok.AccessLevel.PRIVATE;

import com.rouvsen.codeevalengine.model.dto.CodeSubmissionDto;
import com.rouvsen.codeevalengine.model.dto.EvaluationResultDto;
import com.rouvsen.codeevalengine.service.QuizEvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/evaluate")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class EvaluationController {

    QuizEvaluationService quizEvaluationService;

    @PostMapping
    public EvaluationResultDto evaluateCode(@RequestBody CodeSubmissionDto submission) {
        return quizEvaluationService.evaluateSolution(submission);
    }
}
