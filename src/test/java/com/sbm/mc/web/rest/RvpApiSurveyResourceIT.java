package com.sbm.mc.web.rest;

import static com.sbm.mc.domain.RvpApiSurveyAsserts.*;
import static com.sbm.mc.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.IntegrationTest;
import com.sbm.mc.domain.RvpApiSurvey;
import com.sbm.mc.repository.RvpApiSurveyRepository;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RvpApiSurveyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RvpApiSurveyResourceIT {

    private static final Boolean DEFAULT_OVERALL_SCORE_ENABLED = false;
    private static final Boolean UPDATED_OVERALL_SCORE_ENABLED = true;

    private static final String DEFAULT_LANGUAGES = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGES = "BBBBBBBBBB";

    private static final Integer DEFAULT_OUT_OF = 1;
    private static final Integer UPDATED_OUT_OF = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_PIDS = "AAAAAAAAAA";
    private static final String UPDATED_PIDS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PRIMARY = false;
    private static final Boolean UPDATED_PRIMARY = true;

    private static final String ENTITY_API_URL = "/api/rvp-api-surveys";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RvpApiSurveyRepository rvpApiSurveyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRvpApiSurveyMockMvc;

    private RvpApiSurvey rvpApiSurvey;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpApiSurvey createEntity(EntityManager em) {
        RvpApiSurvey rvpApiSurvey = new RvpApiSurvey()
            .overallScoreEnabled(DEFAULT_OVERALL_SCORE_ENABLED)
            .languages(DEFAULT_LANGUAGES)
            .outOf(DEFAULT_OUT_OF)
            .name(DEFAULT_NAME)
            .active(DEFAULT_ACTIVE)
            .pids(DEFAULT_PIDS)
            .primary(DEFAULT_PRIMARY);
        return rvpApiSurvey;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpApiSurvey createUpdatedEntity(EntityManager em) {
        RvpApiSurvey rvpApiSurvey = new RvpApiSurvey()
            .overallScoreEnabled(UPDATED_OVERALL_SCORE_ENABLED)
            .languages(UPDATED_LANGUAGES)
            .outOf(UPDATED_OUT_OF)
            .name(UPDATED_NAME)
            .active(UPDATED_ACTIVE)
            .pids(UPDATED_PIDS)
            .primary(UPDATED_PRIMARY);
        return rvpApiSurvey;
    }

    @BeforeEach
    public void initTest() {
        rvpApiSurvey = createEntity(em);
    }

    @Test
    @Transactional
    void createRvpApiSurvey() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RvpApiSurvey
        var returnedRvpApiSurvey = om.readValue(
            restRvpApiSurveyMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpApiSurvey)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RvpApiSurvey.class
        );

        // Validate the RvpApiSurvey in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRvpApiSurveyUpdatableFieldsEquals(returnedRvpApiSurvey, getPersistedRvpApiSurvey(returnedRvpApiSurvey));
    }

    @Test
    @Transactional
    void createRvpApiSurveyWithExistingId() throws Exception {
        // Create the RvpApiSurvey with an existing ID
        rvpApiSurvey.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRvpApiSurveyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpApiSurvey)))
            .andExpect(status().isBadRequest());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRvpApiSurveys() throws Exception {
        // Initialize the database
        rvpApiSurveyRepository.saveAndFlush(rvpApiSurvey);

        // Get all the rvpApiSurveyList
        restRvpApiSurveyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rvpApiSurvey.getId())))
            .andExpect(jsonPath("$.[*].overallScoreEnabled").value(hasItem(DEFAULT_OVERALL_SCORE_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].languages").value(hasItem(DEFAULT_LANGUAGES)))
            .andExpect(jsonPath("$.[*].outOf").value(hasItem(DEFAULT_OUT_OF)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].pids").value(hasItem(DEFAULT_PIDS)))
            .andExpect(jsonPath("$.[*].primary").value(hasItem(DEFAULT_PRIMARY.booleanValue())));
    }

    @Test
    @Transactional
    void getRvpApiSurvey() throws Exception {
        // Initialize the database
        rvpApiSurveyRepository.saveAndFlush(rvpApiSurvey);

        // Get the rvpApiSurvey
        restRvpApiSurveyMockMvc
            .perform(get(ENTITY_API_URL_ID, rvpApiSurvey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rvpApiSurvey.getId()))
            .andExpect(jsonPath("$.overallScoreEnabled").value(DEFAULT_OVERALL_SCORE_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.languages").value(DEFAULT_LANGUAGES))
            .andExpect(jsonPath("$.outOf").value(DEFAULT_OUT_OF))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.pids").value(DEFAULT_PIDS))
            .andExpect(jsonPath("$.primary").value(DEFAULT_PRIMARY.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingRvpApiSurvey() throws Exception {
        // Get the rvpApiSurvey
        restRvpApiSurveyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRvpApiSurvey() throws Exception {
        // Initialize the database
        rvpApiSurveyRepository.saveAndFlush(rvpApiSurvey);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpApiSurvey
        RvpApiSurvey updatedRvpApiSurvey = rvpApiSurveyRepository.findById(rvpApiSurvey.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRvpApiSurvey are not directly saved in db
        em.detach(updatedRvpApiSurvey);
        updatedRvpApiSurvey
            .overallScoreEnabled(UPDATED_OVERALL_SCORE_ENABLED)
            .languages(UPDATED_LANGUAGES)
            .outOf(UPDATED_OUT_OF)
            .name(UPDATED_NAME)
            .active(UPDATED_ACTIVE)
            .pids(UPDATED_PIDS)
            .primary(UPDATED_PRIMARY);

        restRvpApiSurveyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRvpApiSurvey.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRvpApiSurvey))
            )
            .andExpect(status().isOk());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRvpApiSurveyToMatchAllProperties(updatedRvpApiSurvey);
    }

    @Test
    @Transactional
    void putNonExistingRvpApiSurvey() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiSurvey.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpApiSurveyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rvpApiSurvey.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rvpApiSurvey))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRvpApiSurvey() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiSurvey.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiSurveyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rvpApiSurvey))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRvpApiSurvey() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiSurvey.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiSurveyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpApiSurvey)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRvpApiSurveyWithPatch() throws Exception {
        // Initialize the database
        rvpApiSurveyRepository.saveAndFlush(rvpApiSurvey);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpApiSurvey using partial update
        RvpApiSurvey partialUpdatedRvpApiSurvey = new RvpApiSurvey();
        partialUpdatedRvpApiSurvey.setId(rvpApiSurvey.getId());

        partialUpdatedRvpApiSurvey
            .overallScoreEnabled(UPDATED_OVERALL_SCORE_ENABLED)
            .outOf(UPDATED_OUT_OF)
            .active(UPDATED_ACTIVE)
            .primary(UPDATED_PRIMARY);

        restRvpApiSurveyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpApiSurvey.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpApiSurvey))
            )
            .andExpect(status().isOk());

        // Validate the RvpApiSurvey in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpApiSurveyUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRvpApiSurvey, rvpApiSurvey),
            getPersistedRvpApiSurvey(rvpApiSurvey)
        );
    }

    @Test
    @Transactional
    void fullUpdateRvpApiSurveyWithPatch() throws Exception {
        // Initialize the database
        rvpApiSurveyRepository.saveAndFlush(rvpApiSurvey);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpApiSurvey using partial update
        RvpApiSurvey partialUpdatedRvpApiSurvey = new RvpApiSurvey();
        partialUpdatedRvpApiSurvey.setId(rvpApiSurvey.getId());

        partialUpdatedRvpApiSurvey
            .overallScoreEnabled(UPDATED_OVERALL_SCORE_ENABLED)
            .languages(UPDATED_LANGUAGES)
            .outOf(UPDATED_OUT_OF)
            .name(UPDATED_NAME)
            .active(UPDATED_ACTIVE)
            .pids(UPDATED_PIDS)
            .primary(UPDATED_PRIMARY);

        restRvpApiSurveyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpApiSurvey.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpApiSurvey))
            )
            .andExpect(status().isOk());

        // Validate the RvpApiSurvey in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpApiSurveyUpdatableFieldsEquals(partialUpdatedRvpApiSurvey, getPersistedRvpApiSurvey(partialUpdatedRvpApiSurvey));
    }

    @Test
    @Transactional
    void patchNonExistingRvpApiSurvey() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiSurvey.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpApiSurveyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rvpApiSurvey.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpApiSurvey))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRvpApiSurvey() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiSurvey.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiSurveyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpApiSurvey))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRvpApiSurvey() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiSurvey.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiSurveyMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rvpApiSurvey)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpApiSurvey in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRvpApiSurvey() throws Exception {
        // Initialize the database
        rvpApiSurveyRepository.saveAndFlush(rvpApiSurvey);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rvpApiSurvey
        restRvpApiSurveyMockMvc
            .perform(delete(ENTITY_API_URL_ID, rvpApiSurvey.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rvpApiSurveyRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected RvpApiSurvey getPersistedRvpApiSurvey(RvpApiSurvey rvpApiSurvey) {
        return rvpApiSurveyRepository.findById(rvpApiSurvey.getId()).orElseThrow();
    }

    protected void assertPersistedRvpApiSurveyToMatchAllProperties(RvpApiSurvey expectedRvpApiSurvey) {
        assertRvpApiSurveyAllPropertiesEquals(expectedRvpApiSurvey, getPersistedRvpApiSurvey(expectedRvpApiSurvey));
    }

    protected void assertPersistedRvpApiSurveyToMatchUpdatableProperties(RvpApiSurvey expectedRvpApiSurvey) {
        assertRvpApiSurveyAllUpdatablePropertiesEquals(expectedRvpApiSurvey, getPersistedRvpApiSurvey(expectedRvpApiSurvey));
    }
}
