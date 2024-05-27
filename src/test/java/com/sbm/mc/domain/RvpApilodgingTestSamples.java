package com.sbm.mc.domain;

import java.util.UUID;

public class RvpApilodgingTestSamples {

    public static RvpApilodging getRvpApilodgingSample1() {
        return new RvpApilodging().id("id1").name("name1");
    }

    public static RvpApilodging getRvpApilodgingSample2() {
        return new RvpApilodging().id("id2").name("name2");
    }

    public static RvpApilodging getRvpApilodgingRandomSampleGenerator() {
        return new RvpApilodging().id(UUID.randomUUID().toString()).name(UUID.randomUUID().toString());
    }
}
