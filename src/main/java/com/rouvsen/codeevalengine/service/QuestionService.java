package com.rouvsen.codeevalengine.service;

import static lombok.AccessLevel.PRIVATE;

import com.rouvsen.codeevalengine.dao.entity.CodeSubmissionEntity;
import com.rouvsen.codeevalengine.dao.entity.QuestionGroupEntity;
import com.rouvsen.codeevalengine.dao.repository.CodeSubmissionRepository;
import com.rouvsen.codeevalengine.dao.repository.QuestionGroupRepository;
import com.rouvsen.codeevalengine.mapper.QuestionMapper;
import com.rouvsen.codeevalengine.model.dto.BulkQuestionsCreationDto;
import com.rouvsen.codeevalengine.model.dto.QuestionDto;
import com.rouvsen.codeevalengine.model.dto.QuestionDeletionDto;
import com.rouvsen.codeevalengine.model.dto.QuestionGroupDto;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class QuestionService {

    QuestionGroupRepository questionGroupRepository;
    CodeSubmissionRepository codeSubmissionRepository;

    public void createQuestion(String group, QuestionDto questionDto) {
        questionGroupRepository.findByNameWithSubmissions(group)
                .ifPresentOrElse(questionGroup -> {
                    questionGroup.getCodeSubmissions()
                            .add(QuestionMapper.INSTANCE.toCodeSubmissionEntity(questionDto));
                    questionGroupRepository.save(questionGroup);
                }, () -> {
                    questionGroupRepository.save(QuestionGroupEntity.builder()
                            .name(group)
                            .codeSubmissions(
                                    List.of(QuestionMapper.INSTANCE.toCodeSubmissionEntity(questionDto)))
                            .build());
                    log.info("Question group {} created!", group);
                });
    }

    public QuestionGroupDto getAllQuestions(String group) {
        QuestionGroupEntity questionGroup = questionGroupRepository.findByNameWithSubmissions(group)
                .orElseThrow(() -> new RuntimeException("Question group " + group + " not found"));

        return QuestionMapper.INSTANCE.toQuestionGroupDto(questionGroup);
    }

    public void deleteAllQuestions(String group) {
        QuestionGroupEntity questionGroup = questionGroupRepository.findByName(group)
                .orElseThrow(() -> new RuntimeException("Question group " + group + " not found"));
        questionGroupRepository.delete(questionGroup);
    }

    public void deleteQuestion(String group, QuestionDeletionDto questionDeletionDto) {
        List<Long> codeSubmissions = questionDeletionDto.getCodeSubmissions();

        if (Objects.isNull(codeSubmissions) || codeSubmissions.isEmpty()) {
            throw new IllegalArgumentException("CodeSubmissions deletion size cannot be empty!");
        }

        QuestionGroupEntity questionGroup = questionGroupRepository.findByNameWithSubmissions(group)
                .orElseThrow(() -> new RuntimeException("Question group " + group + " not found"));

        List<CodeSubmissionEntity> toRemove = questionGroup.getCodeSubmissions().stream()
                .filter(cs -> codeSubmissions.contains(cs.getId()))
                .toList();

        questionGroup.getCodeSubmissions().removeAll(toRemove);

        questionGroupRepository.save(questionGroup);
    }

    public void createBulkQuestions(String group, BulkQuestionsCreationDto bulkQuestionsCreationDto) {
        List<QuestionDto> questions = bulkQuestionsCreationDto.getQuestions();
        if (Objects.isNull(questions) || questions.isEmpty()) {
            throw new IllegalArgumentException("Bulk questions creation size cannot be empty!");
        }

        questionGroupRepository.findByNameWithSubmissions(group)
                .ifPresentOrElse(questionGroup -> {
                    questionGroup.getCodeSubmissions()
                            .addAll(QuestionMapper.INSTANCE.toCodeSubmissionEntityList(questions));
                    questionGroupRepository.save(questionGroup);
                }, () -> {
                    questionGroupRepository.save(QuestionGroupEntity.builder()
                            .name(group)
                            .codeSubmissions(QuestionMapper.INSTANCE.toCodeSubmissionEntityList(questions))
                            .build());
                    log.info("Question group {} created!", group);
                });
    }

    public void updateQuestion(String group, QuestionDto questionDto) {
        questionGroupRepository.findByName(group)
                .orElseThrow(() -> new RuntimeException("Question group " + group + " not found"));

        CodeSubmissionEntity codeSubmissionEntity = QuestionMapper.INSTANCE.toCodeSubmissionEntity(questionDto);
        codeSubmissionRepository.save(codeSubmissionEntity);
    }
}
