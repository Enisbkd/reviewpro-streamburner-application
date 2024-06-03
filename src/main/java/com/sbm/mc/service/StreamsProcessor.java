package com.sbm.mc.service;

import com.sbm.mc.domain.*;
import com.sbm.mc.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StreamsProcessor {

    private final Logger logger = LoggerFactory.getLogger(StreamsProcessor.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<RvpApiGlobalReview> GLOBAL_REVIEW_SERDE = CustomSerdes.globalReviewSerde();
    private static final Serde<RvpApilodging> LODGING_SERDE = CustomSerdes.lodgingSerde();
    private static final Serde<RvpApiLodgingCqi> LODGING_CQI_SERDE = CustomSerdes.lodgingCqiSerde();
    private static final Serde<RvpApiLodgingScore> LODGING_SCORE_SERDE = CustomSerdes.lodgingScoreSerde();
    private static final Serde<RvpApiResponse> RVP_API_RESPONSE_SERDE = CustomSerdes.responseSerde();
    private static final Serde<RvpApiSurvey> RVP_API_SURVEY_SERDE = CustomSerdes.surveySerde();

    @Value(value = "${spring.kafka.topics.global-reviews-topic}")
    private String globalReviewsTopic;

    @Value(value = "${spring.kafka.topics.lodgings-topic}")
    private String lodgingsTopic;

    @Value(value = "${spring.kafka.topics.lodging-cqis-topic}")
    private String lodgingCqisTopic;

    @Value(value = "${spring.kafka.topics.lodging-scores-topic}")
    private String lodgingScoresTopic;

    @Value(value = "${spring.kafka.topics.responses-topic}")
    private String responsesTopic;

    @Value(value = "${spring.kafka.topics.surveys-topic}")
    private String surveysTopic;

    private final PersistenceService persistenceService;

    public StreamsProcessor(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        logger.debug("Launching Streams ....");

        KStream<String, RvpApiGlobalReview> globalReviewKStream = streamsBuilder.stream(
            globalReviewsTopic,
            Consumed.with(STRING_SERDE, GLOBAL_REVIEW_SERDE)
        );
        globalReviewKStream.foreach((key, globalReview) -> {
            persistenceService.upsertGlobalReview(globalReview);
        });

        KStream<String, RvpApilodging> lodgingStream = streamsBuilder.stream(lodgingsTopic, Consumed.with(STRING_SERDE, LODGING_SERDE));
        lodgingStream.foreach((key, globalReview) -> persistenceService.upsertlodging(globalReview));

        KStream<String, RvpApiLodgingCqi> lodgingCqiStream = streamsBuilder.stream(
            lodgingCqisTopic,
            Consumed.with(STRING_SERDE, LODGING_CQI_SERDE)
        );
        lodgingCqiStream.foreach((key, globalReview) -> persistenceService.upsertlodgingCqi(globalReview));

        KStream<String, RvpApiLodgingScore> lodgingScoreStream = streamsBuilder.stream(
            lodgingScoresTopic,
            Consumed.with(STRING_SERDE, LODGING_SCORE_SERDE)
        );
        lodgingScoreStream.foreach((key, globalReview) -> persistenceService.upsertlodgingScore(globalReview));

        KStream<String, RvpApiResponse> responseStream = streamsBuilder.stream(
            responsesTopic,
            Consumed.with(STRING_SERDE, RVP_API_RESPONSE_SERDE)
        );
        responseStream.foreach((key, globalReview) -> persistenceService.upsertResponse(globalReview));

        KStream<String, RvpApiSurvey> surveyStream = streamsBuilder.stream(surveysTopic, Consumed.with(STRING_SERDE, RVP_API_SURVEY_SERDE));
        surveyStream.foreach((key, globalReview) -> persistenceService.upsertSurvey(globalReview));
    }
}
