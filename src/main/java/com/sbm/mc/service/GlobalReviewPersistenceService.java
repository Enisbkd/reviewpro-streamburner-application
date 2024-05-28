package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiGlobalReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GlobalReviewPersistenceService {

    private final Logger logger = LoggerFactory.getLogger(GlobalReviewPersistenceService.class);

    private final RvpApiGlobalReviewService rvpApiGlobalReviewService;

    public GlobalReviewPersistenceService(RvpApiGlobalReviewService rvpApiGlobalReviewService) {
        this.rvpApiGlobalReviewService = rvpApiGlobalReviewService;
    }

    public RvpApiGlobalReview upsertGlobalReview(RvpApiGlobalReview globalReview) {
        RvpApiGlobalReview globalReviewIdinDB = rvpApiGlobalReviewService.save(globalReview);
        logger.debug("Persisting globalReview : " + globalReviewIdinDB.toString());
        return globalReviewIdinDB;
    }
}
