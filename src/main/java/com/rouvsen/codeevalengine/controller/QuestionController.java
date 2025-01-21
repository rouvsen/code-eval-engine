package com.rouvsen.codeevalengine.controller;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.rouvsen.codeevalengine.model.dto.BulkQuestionsCreationDto;
import com.rouvsen.codeevalengine.model.dto.QuestionDto;
import com.rouvsen.codeevalengine.model.dto.QuestionDeletionDto;
import com.rouvsen.codeevalengine.model.dto.QuestionGroupDto;
import com.rouvsen.codeevalengine.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/questions")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class QuestionController {

    QuestionService questionService;

    @PostMapping("/{group}")
    public ResponseEntity<Void> createQuestion(@PathVariable String group, @RequestBody QuestionDto question) {
        questionService.createQuestion(group, question);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/{group}/bulk")
    public ResponseEntity<Void> createBulkQuestions(@PathVariable String group,
                                                    @RequestBody BulkQuestionsCreationDto bulkQuestions) {
        questionService.createBulkQuestions(group, bulkQuestions);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{group}/individual")
    public ResponseEntity<Void> updateQuestion(@PathVariable String group, @RequestBody QuestionDto question) {
        questionService.updateQuestion(group, question);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/{group}")
    public ResponseEntity<QuestionGroupDto> getAllQuestions(@PathVariable String group) {
        return ResponseEntity.ok(questionService.getAllQuestions(group));
    }

    @DeleteMapping("/{group}")
    public ResponseEntity<Void> deleteAllQuestions(@PathVariable String group) {
        questionService.deleteAllQuestions(group);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @DeleteMapping("/{group}/selected-questions")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String group,
                                               @RequestBody QuestionDeletionDto questionDeletionDto) {
        questionService.deleteQuestion(group, questionDeletionDto);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
