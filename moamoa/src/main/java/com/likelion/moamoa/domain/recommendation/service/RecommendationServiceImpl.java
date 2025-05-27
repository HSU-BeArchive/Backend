package com.likelion.moamoa.domain.recommendation.service;

import com.likelion.moamoa.domain.recommendation.entity.Recommendation;
import com.likelion.moamoa.domain.recommendation.exception.DuplicateRecommendationException;
import com.likelion.moamoa.domain.recommendation.exception.NotFoundReferenceException;
import com.likelion.moamoa.domain.recommendation.repository.RecommendationRepository;
import com.likelion.moamoa.domain.recommendation.web.dto.CreateRecommendationRes;
import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.recommendation.web.dto.RecommendationDetailRes;
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
    public CreateRecommendationRes createRecommendation(Long referenceId) {
        Reference reference = referenceRepository.findById(referenceId)
                .orElseThrow(NotFoundReferenceException::new);

        if(recommendationRepository.existsByReference_ReferenceId(referenceId)) {
            throw new DuplicateRecommendationException();
        }
        // Reference의 description과 imgurl 사용
        String description = reference.getDescription();
        String imgUrl = reference.getImgUrl();

        String question = openAiService.getRecommendationQuestion(imgUrl, description);

        Folder folder = reference.getFolder();
        User user = folder.getUser();

        Recommendation recommendation = Recommendation
                .builder()
                .question(question)
                .reference(reference)
                .folder(folder)
                .build();

        Recommendation saved = recommendationRepository.save(recommendation);


        return new CreateRecommendationRes(
                saved.getRecommendationId(),
                saved.getQuestion(),
                saved.getReference().getFolder().getUser().getUserId(),
                saved.getReference().getFolder().getFolderId(),
                saved.getReference().getReferenceId()
        );
    }

    @Override
    public RecommendationDetailRes getRecommendation(Long referenceId) {
        Reference reference = referenceRepository.findById(referenceId)
                .orElseThrow(NotFoundReferenceException::new);

        Recommendation recommendation = recommendationRepository.findByReference_ReferenceId(referenceId);


        return new RecommendationDetailRes(
                recommendation.getRecommendationId(),
                recommendation.getQuestion(),
                recommendation.getReference().getFolder().getUser().getUserId(),
                recommendation.getReference().getFolder().getFolderId(),
                recommendation.getReference().getReferenceId()
        );
    }
}


