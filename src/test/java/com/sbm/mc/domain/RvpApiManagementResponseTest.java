package com.sbm.mc.domain;

import static com.sbm.mc.domain.RvpApiManagementResponseTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RvpApiManagementResponseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RvpApiManagementResponse.class);
        RvpApiManagementResponse rvpApiManagementResponse1 = getRvpApiManagementResponseSample1();
        RvpApiManagementResponse rvpApiManagementResponse2 = new RvpApiManagementResponse();
        assertThat(rvpApiManagementResponse1).isNotEqualTo(rvpApiManagementResponse2);

        rvpApiManagementResponse2.setId(rvpApiManagementResponse1.getId());
        assertThat(rvpApiManagementResponse1).isEqualTo(rvpApiManagementResponse2);

        rvpApiManagementResponse2 = getRvpApiManagementResponseSample2();
        assertThat(rvpApiManagementResponse1).isNotEqualTo(rvpApiManagementResponse2);
    }
}
