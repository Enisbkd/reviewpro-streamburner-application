package com.sbm.mc.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RvpApiResponseTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RvpApiResponse getRvpApiResponseSample1() {
        return new RvpApiResponse().id(1).surveyId("surveyId1").lodgingId(1).label("label1");
    }

    public static RvpApiResponse getRvpApiResponseSample2() {
        return new RvpApiResponse().id(2).surveyId("surveyId2").lodgingId(2).label("label2");
    }

    public static RvpApiResponse getRvpApiResponseRandomSampleGenerator() {
        return new RvpApiResponse()
            .id(intCount.incrementAndGet())
            .surveyId(UUID.randomUUID().toString())
            .lodgingId(intCount.incrementAndGet())
            .label(UUID.randomUUID().toString());
    }
}
