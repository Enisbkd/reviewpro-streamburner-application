package com.sbm.mc.serdes;

import com.sbm.mc.domain.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<RvpApiGlobalReview> globalReviewSerde() {
        JacksonSerializer<RvpApiGlobalReview> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiGlobalReview> deserializer = new JacksonDeserializer<>(RvpApiGlobalReview.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApilodging> lodgingSerde() {
        JacksonSerializer<RvpApilodging> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApilodging> deserializer = new JacksonDeserializer<>(RvpApilodging.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiLodgingCqi> lodgingCqiSerde() {
        JacksonSerializer<RvpApiLodgingCqi> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiLodgingCqi> deserializer = new JacksonDeserializer<>(RvpApiLodgingCqi.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiLodgingScore> lodgingScoreSerde() {
        JacksonSerializer<RvpApiLodgingScore> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiLodgingScore> deserializer = new JacksonDeserializer<>(RvpApiLodgingScore.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiResponse> responseSerde() {
        JacksonSerializer<RvpApiResponse> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiResponse> deserializer = new JacksonDeserializer<>(RvpApiResponse.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiSurvey> surveySerde() {
        JacksonSerializer<RvpApiSurvey> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiSurvey> deserializer = new JacksonDeserializer<>(RvpApiSurvey.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
