package com.sbm.mc.service;

import com.sbm.mc.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService {

    private final Logger logger = LoggerFactory.getLogger(PersistenceService.class);

    private final RvpApiGlobalReviewService rvpApiGlobalReviewService;
    private final RvpApilodgingService rvpApilodgingService;
    private final RvpApiLodgingCqiService rvpApiLodgingCqiService;
    private final RvpApiLodgingScoreService rvpApiLodgingScoreService;
    private final RvpApiResponseService rvpApiResponseService;
    private final RvpApiSurveyService rvpApiSurveyService;

    public PersistenceService(
        RvpApiGlobalReviewService rvpApiGlobalReviewService,
        RvpApilodgingService rvpApilodgingService,
        RvpApiLodgingCqiService rvpApiLodgingCqiService,
        RvpApiLodgingScoreService rvpApiLodgingScoreService,
        RvpApiResponseService rvpApiResponseService,
        RvpApiSurveyService rvpApiSurveyService
    ) {
        this.rvpApiGlobalReviewService = rvpApiGlobalReviewService;
        this.rvpApilodgingService = rvpApilodgingService;
        this.rvpApiLodgingCqiService = rvpApiLodgingCqiService;
        this.rvpApiLodgingScoreService = rvpApiLodgingScoreService;
        this.rvpApiResponseService = rvpApiResponseService;
        this.rvpApiSurveyService = rvpApiSurveyService;
    }

    public void upsertGlobalReview(RvpApiGlobalReview globalReview) {
        try {
            if (globalReview != null) {
                RvpApiGlobalReview globalReviewIdinDB = rvpApiGlobalReviewService.save(globalReview);
                logger.debug("Persisting globalReview : " + globalReviewIdinDB.toString());
            } else {
                logger.info("GlobalReview Null , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    public void upsertlodging(RvpApilodging rvpApilodging) {
        try {
            if (rvpApilodging != null) {
                RvpApilodging rvpApilodgingInDB = rvpApilodgingService.save(rvpApilodging);
                logger.debug("Persisting Lodging : " + rvpApilodgingInDB.toString());
            } else {
                logger.info("Lodging Null , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    public void upsertlodgingCqi(RvpApiLodgingCqi rvpApiLodgingCqi) {
        try {
            if (rvpApiLodgingCqi != null) {
                RvpApiLodgingCqi rvpApilodgingCqiInDB = rvpApiLodgingCqiService.save(rvpApiLodgingCqi);
                logger.debug("Persisting LodgingCqi : " + rvpApilodgingCqiInDB.toString());
            } else {
                logger.info("LodgingCqi Null , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    public void upsertlodgingScore(RvpApiLodgingScore rvpApiLodgingScore) {
        try {
            if (rvpApiLodgingScore != null) {
                RvpApiLodgingScore rvpApilodgingScoreInDB = rvpApiLodgingScoreService.save(rvpApiLodgingScore);
                logger.debug("Persisting LodgingScore : " + rvpApilodgingScoreInDB.toString());
            } else {
                logger.info("LodgingScore Null , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    public void upsertResponse(RvpApiResponse rvpApiResponse) {
        try {
            if (rvpApiResponse != null) {
                RvpApiResponse rvpApiResponseInDB = rvpApiResponseService.save(rvpApiResponse);
                logger.debug("Persisting rvpApiResponse : " + rvpApiResponseInDB.toString());
            } else {
                logger.info("RvpApiResponse Null , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    public void upsertSurvey(RvpApiSurvey rvpApiSurvey) {
        try {
            if (rvpApiSurvey != null) {
                RvpApiSurvey rvpApiSurveyInDB = rvpApiSurveyService.save(rvpApiSurvey);
                logger.debug("Persisting Survey : " + rvpApiSurveyInDB.toString());
            } else {
                logger.info("Survey Null , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }
}
