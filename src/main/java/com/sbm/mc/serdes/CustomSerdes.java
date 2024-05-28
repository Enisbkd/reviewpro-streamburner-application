package com.sbm.mc.serdes;

import com.sbm.mc.domain.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<RvpApiGlobalReview> globalReviewSerde() {
        JacksonSerializer<RvpApiGlobalReview> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiGlobalReview> deserializer = new JacksonDeserializer<>();
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApilodging> lodgingSerde() {
        JacksonSerializer<RvpApilodging> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApilodging> deserializer = new JacksonDeserializer<>();
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiLodgingCqi> lodgingCqiSerde() {
        JacksonSerializer<RvpApiLodgingCqi> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiLodgingCqi> deserializer = new JacksonDeserializer<>();
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiLodgingScore> lodgingScoreSerde() {
        JacksonSerializer<RvpApiLodgingScore> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiLodgingScore> deserializer = new JacksonDeserializer<>();
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiResponse> responseSerde() {
        JacksonSerializer<RvpApiResponse> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiResponse> deserializer = new JacksonDeserializer<>();
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<RvpApiSurvey> surveySerde() {
        JacksonSerializer<RvpApiSurvey> serializer = new JacksonSerializer<>();
        JacksonDeserializer<RvpApiSurvey> deserializer = new JacksonDeserializer<>();
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
