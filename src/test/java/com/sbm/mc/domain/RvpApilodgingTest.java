package com.sbm.mc.domain;

import static com.sbm.mc.domain.RvpApilodgingTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RvpApilodgingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RvpApilodging.class);
        RvpApilodging rvpApilodging1 = getRvpApilodgingSample1();
        RvpApilodging rvpApilodging2 = new RvpApilodging();
        assertThat(rvpApilodging1).isNotEqualTo(rvpApilodging2);

        rvpApilodging2.setId(rvpApilodging1.getId());
        assertThat(rvpApilodging1).isEqualTo(rvpApilodging2);

        rvpApilodging2 = getRvpApilodgingSample2();
        assertThat(rvpApilodging1).isNotEqualTo(rvpApilodging2);
    }
}
