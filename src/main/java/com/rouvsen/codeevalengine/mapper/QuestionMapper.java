package com.rouvsen.codeevalengine.mapper;

import com.rouvsen.codeevalengine.dao.entity.CodeSubmissionEntity;
import com.rouvsen.codeevalengine.dao.entity.QuestionGroupEntity;
import com.rouvsen.codeevalengine.model.dto.QuestionDto;
import com.rouvsen.codeevalengine.model.dto.QuestionGroupDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CodeSubmissionEntity toCodeSubmissionEntity(QuestionDto source);

    QuestionGroupDto toQuestionGroupDto(QuestionGroupEntity questionGroup);

    List<CodeSubmissionEntity> toCodeSubmissionEntityList(List<QuestionDto> source);
}
