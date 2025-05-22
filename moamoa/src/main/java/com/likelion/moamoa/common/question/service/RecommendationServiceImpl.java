package com.likelion.moamoa.common.question.service;

import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.common.question.exception.DuplicateRecommendationException;
import com.likelion.moamoa.common.question.exception.NotFoundReferenceException;
import com.likelion.moamoa.common.question.repository.RecommendationRepository;
import com.likelion.moamoa.common.question.web.dto.CreateQuestionReq;
import com.likelion.moamoa.common.question.web.dto.CreateQuestionRes;
import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.reference.entity.Reference;
import com.likelion.moamoa.domain.reference.repository.ReferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final ReferenceRepository referenceRepository;
    private final RecommendationRepository recommendationRepository;
    private final OpenAiService openAiService;

    @Override
    @Transactional
    public CreateQuestionRes createRecommendation(Long referenceId) {
        Reference reference = referenceRepository.findById(referenceId)
                .orElseThrow(NotFoundReferenceException::new);

        if(recommendationRepository.existsByReference_ReferenceId(referenceId)) {
            throw new DuplicateRecommendationException();
        }
        // Reference의 description과 imgurl 사용
        String description = reference.getDescription();
        String imgUrl = reference.getImgUrl();

        String question = openAiService.getRecommendationQuestion(imgUrl, description);

        Recommendation recommendation = new Recommendation(question, reference);
        Recommendation saved = recommendationRepository.save(recommendation);

        Folder folder = reference.getFolder();
        User user = folder.getUser();


        return CreateQuestionRes.builder()
                .recommendationId(saved.getRecommendationId())
                .userId(user.getUserId())
                .folderId(folder.getFolderId())
                .referenceId(reference.getReferenceId())
                .question(saved.getQuestion())
                .build();
    }



    }


