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

    //    @Value(value = "${spring.kafka.topics.client-topic}")
    //    private String clientTopic;
    //
    //    @Value(value = "${spring.kafka.topics.reservation-topic}")
    //    private String reservationTopic;
    //
    //    @Value(value = "${spring.kafka.topics.venue-topic}")
    //    private String venueTopic;

    private final PersistenceService persistenceService;

    public StreamsProcessor(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        logger.debug("Launching Streams ....");

        KStream<String, RvpApiGlobalReview> globalReviewKStream = streamsBuilder.stream(
            "data-reviewpro-global-review",
            Consumed.with(STRING_SERDE, GLOBAL_REVIEW_SERDE)
        );
        globalReviewKStream.foreach((key, globalReview) -> {
            persistenceService.upsertGlobalReview(globalReview);
        });

        KStream<String, RvpApilodging> lodgingStream = streamsBuilder.stream(
            "data-reviewpro-lodgings",
            Consumed.with(STRING_SERDE, LODGING_SERDE)
        );
        lodgingStream.foreach((key, globalReview) -> persistenceService.upsertlodging(globalReview));

        KStream<String, RvpApiLodgingCqi> lodgingCqiStream = streamsBuilder.stream(
            "data-reviewpro-lodgingCqis",
            Consumed.with(STRING_SERDE, LODGING_CQI_SERDE)
        );
        lodgingCqiStream.foreach((key, globalReview) -> persistenceService.upsertlodgingCqi(globalReview));

        KStream<String, RvpApiLodgingScore> lodgingScoreStream = streamsBuilder.stream(
            "data-reviewpro-lodgingScores",
            Consumed.with(STRING_SERDE, LODGING_SCORE_SERDE)
        );
        lodgingScoreStream.foreach((key, globalReview) -> persistenceService.upsertlodgingScore(globalReview));

        KStream<String, RvpApiResponse> responseStream = streamsBuilder.stream(
            "data-reviewpro-responses",
            Consumed.with(STRING_SERDE, RVP_API_RESPONSE_SERDE)
        );
        responseStream.foreach((key, globalReview) -> persistenceService.upsertResponse(globalReview));

        KStream<String, RvpApiSurvey> surveyStream = streamsBuilder.stream(
            "data-reviewpro-surveys",
            Consumed.with(STRING_SERDE, RVP_API_SURVEY_SERDE)
        );
        surveyStream.foreach((key, globalReview) -> persistenceService.upsertSurvey(globalReview));
    }
}
