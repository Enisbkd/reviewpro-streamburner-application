package com.sbm.mc.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class RvpApiManagementResponseTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static RvpApiManagementResponse getRvpApiManagementResponseSample1() {
        return new RvpApiManagementResponse()
            .id(1)
            .source("source1")
            .lodgingId(1)
            .respondableCountsPositive(1)
            .respondableCountsNegative(1)
            .respondedCountsPositive(1)
            .respondedCountsNegative(1);
    }

    public static RvpApiManagementResponse getRvpApiManagementResponseSample2() {
        return new RvpApiManagementResponse()
            .id(2)
            .source("source2")
            .lodgingId(2)
            .respondableCountsPositive(2)
            .respondableCountsNegative(2)
            .respondedCountsPositive(2)
            .respondedCountsNegative(2);
    }

    public static RvpApiManagementResponse getRvpApiManagementResponseRandomSampleGenerator() {
        return new RvpApiManagementResponse()
            .id(intCount.incrementAndGet())
            .source(UUID.randomUUID().toString())
            .lodgingId(intCount.incrementAndGet())
            .respondableCountsPositive(intCount.incrementAndGet())
            .respondableCountsNegative(intCount.incrementAndGet())
            .respondedCountsPositive(intCount.incrementAndGet())
            .respondedCountsNegative(intCount.incrementAndGet());
    }
}
