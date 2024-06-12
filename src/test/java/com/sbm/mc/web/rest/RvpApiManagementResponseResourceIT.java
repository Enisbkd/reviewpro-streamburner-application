package com.sbm.mc.web.rest;

import static com.sbm.mc.domain.RvpApiManagementResponseAsserts.*;
import static com.sbm.mc.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.IntegrationTest;
import com.sbm.mc.domain.RvpApiManagementResponse;
import com.sbm.mc.repository.RvpApiManagementResponseRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RvpApiManagementResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RvpApiManagementResponseResourceIT {

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LODGING_ID = 1;
    private static final Integer UPDATED_LODGING_ID = 2;

    private static final LocalDate DEFAULT_FD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FD = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TD = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_RESPONDABLE_COUNTS_POSITIVE = 1;
    private static final Integer UPDATED_RESPONDABLE_COUNTS_POSITIVE = 2;

    private static final Integer DEFAULT_RESPONDABLE_COUNTS_NEGATIVE = 1;
    private static final Integer UPDATED_RESPONDABLE_COUNTS_NEGATIVE = 2;

    private static final Integer DEFAULT_RESPONDED_COUNTS_POSITIVE = 1;
    private static final Integer UPDATED_RESPONDED_COUNTS_POSITIVE = 2;

    private static final Integer DEFAULT_RESPONDED_COUNTS_NEGATIVE = 1;
    private static final Integer UPDATED_RESPONDED_COUNTS_NEGATIVE = 2;

    private static final String ENTITY_API_URL = "/api/rvp-api-management-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RvpApiManagementResponseRepository rvpApiManagementResponseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRvpApiManagementResponseMockMvc;

    private RvpApiManagementResponse rvpApiManagementResponse;

    private RvpApiManagementResponse insertedRvpApiManagementResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpApiManagementResponse createEntity(EntityManager em) {
        RvpApiManagementResponse rvpApiManagementResponse = new RvpApiManagementResponse()
            .source(DEFAULT_SOURCE)
            .lodgingId(DEFAULT_LODGING_ID)
            .fd(DEFAULT_FD)
            .td(DEFAULT_TD)
            .respondableCountsPositive(DEFAULT_RESPONDABLE_COUNTS_POSITIVE)
            .respondableCountsNegative(DEFAULT_RESPONDABLE_COUNTS_NEGATIVE)
            .respondedCountsPositive(DEFAULT_RESPONDED_COUNTS_POSITIVE)
            .respondedCountsNegative(DEFAULT_RESPONDED_COUNTS_NEGATIVE);
        return rvpApiManagementResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpApiManagementResponse createUpdatedEntity(EntityManager em) {
        RvpApiManagementResponse rvpApiManagementResponse = new RvpApiManagementResponse()
            .source(UPDATED_SOURCE)
            .lodgingId(UPDATED_LODGING_ID)
            .fd(UPDATED_FD)
            .td(UPDATED_TD)
            .respondableCountsPositive(UPDATED_RESPONDABLE_COUNTS_POSITIVE)
            .respondableCountsNegative(UPDATED_RESPONDABLE_COUNTS_NEGATIVE)
            .respondedCountsPositive(UPDATED_RESPONDED_COUNTS_POSITIVE)
            .respondedCountsNegative(UPDATED_RESPONDED_COUNTS_NEGATIVE);
        return rvpApiManagementResponse;
    }

    @BeforeEach
    public void initTest() {
        rvpApiManagementResponse = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRvpApiManagementResponse != null) {
            rvpApiManagementResponseRepository.delete(insertedRvpApiManagementResponse);
            insertedRvpApiManagementResponse = null;
        }
    }

    @Test
    @Transactional
    void createRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RvpApiManagementResponse
        var returnedRvpApiManagementResponse = om.readValue(
            restRvpApiManagementResponseMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpApiManagementResponse))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RvpApiManagementResponse.class
        );

        // Validate the RvpApiManagementResponse in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRvpApiManagementResponseUpdatableFieldsEquals(
            returnedRvpApiManagementResponse,
            getPersistedRvpApiManagementResponse(returnedRvpApiManagementResponse)
        );

        insertedRvpApiManagementResponse = returnedRvpApiManagementResponse;
    }

    @Test
    @Transactional
    void createRvpApiManagementResponseWithExistingId() throws Exception {
        // Create the RvpApiManagementResponse with an existing ID
        rvpApiManagementResponse.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRvpApiManagementResponseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpApiManagementResponse)))
            .andExpect(status().isBadRequest());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRvpApiManagementResponses() throws Exception {
        // Initialize the database
        insertedRvpApiManagementResponse = rvpApiManagementResponseRepository.saveAndFlush(rvpApiManagementResponse);

        // Get all the rvpApiManagementResponseList
        restRvpApiManagementResponseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rvpApiManagementResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].lodgingId").value(hasItem(DEFAULT_LODGING_ID)))
            .andExpect(jsonPath("$.[*].fd").value(hasItem(DEFAULT_FD.toString())))
            .andExpect(jsonPath("$.[*].td").value(hasItem(DEFAULT_TD.toString())))
            .andExpect(jsonPath("$.[*].respondableCountsPositive").value(hasItem(DEFAULT_RESPONDABLE_COUNTS_POSITIVE)))
            .andExpect(jsonPath("$.[*].respondableCountsNegative").value(hasItem(DEFAULT_RESPONDABLE_COUNTS_NEGATIVE)))
            .andExpect(jsonPath("$.[*].respondedCountsPositive").value(hasItem(DEFAULT_RESPONDED_COUNTS_POSITIVE)))
            .andExpect(jsonPath("$.[*].respondedCountsNegative").value(hasItem(DEFAULT_RESPONDED_COUNTS_NEGATIVE)));
    }

    @Test
    @Transactional
    void getRvpApiManagementResponse() throws Exception {
        // Initialize the database
        insertedRvpApiManagementResponse = rvpApiManagementResponseRepository.saveAndFlush(rvpApiManagementResponse);

        // Get the rvpApiManagementResponse
        restRvpApiManagementResponseMockMvc
            .perform(get(ENTITY_API_URL_ID, rvpApiManagementResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rvpApiManagementResponse.getId().intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.lodgingId").value(DEFAULT_LODGING_ID))
            .andExpect(jsonPath("$.fd").value(DEFAULT_FD.toString()))
            .andExpect(jsonPath("$.td").value(DEFAULT_TD.toString()))
            .andExpect(jsonPath("$.respondableCountsPositive").value(DEFAULT_RESPONDABLE_COUNTS_POSITIVE))
            .andExpect(jsonPath("$.respondableCountsNegative").value(DEFAULT_RESPONDABLE_COUNTS_NEGATIVE))
            .andExpect(jsonPath("$.respondedCountsPositive").value(DEFAULT_RESPONDED_COUNTS_POSITIVE))
            .andExpect(jsonPath("$.respondedCountsNegative").value(DEFAULT_RESPONDED_COUNTS_NEGATIVE));
    }

    @Test
    @Transactional
    void getNonExistingRvpApiManagementResponse() throws Exception {
        // Get the rvpApiManagementResponse
        restRvpApiManagementResponseMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRvpApiManagementResponse() throws Exception {
        // Initialize the database
        insertedRvpApiManagementResponse = rvpApiManagementResponseRepository.saveAndFlush(rvpApiManagementResponse);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpApiManagementResponse
        RvpApiManagementResponse updatedRvpApiManagementResponse = rvpApiManagementResponseRepository
            .findById(rvpApiManagementResponse.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedRvpApiManagementResponse are not directly saved in db
        em.detach(updatedRvpApiManagementResponse);
        updatedRvpApiManagementResponse
            .source(UPDATED_SOURCE)
            .lodgingId(UPDATED_LODGING_ID)
            .fd(UPDATED_FD)
            .td(UPDATED_TD)
            .respondableCountsPositive(UPDATED_RESPONDABLE_COUNTS_POSITIVE)
            .respondableCountsNegative(UPDATED_RESPONDABLE_COUNTS_NEGATIVE)
            .respondedCountsPositive(UPDATED_RESPONDED_COUNTS_POSITIVE)
            .respondedCountsNegative(UPDATED_RESPONDED_COUNTS_NEGATIVE);

        restRvpApiManagementResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRvpApiManagementResponse.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRvpApiManagementResponse))
            )
            .andExpect(status().isOk());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRvpApiManagementResponseToMatchAllProperties(updatedRvpApiManagementResponse);
    }

    @Test
    @Transactional
    void putNonExistingRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiManagementResponse.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpApiManagementResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rvpApiManagementResponse.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rvpApiManagementResponse))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiManagementResponse.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiManagementResponseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rvpApiManagementResponse))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiManagementResponse.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiManagementResponseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpApiManagementResponse)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRvpApiManagementResponseWithPatch() throws Exception {
        // Initialize the database
        insertedRvpApiManagementResponse = rvpApiManagementResponseRepository.saveAndFlush(rvpApiManagementResponse);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpApiManagementResponse using partial update
        RvpApiManagementResponse partialUpdatedRvpApiManagementResponse = new RvpApiManagementResponse();
        partialUpdatedRvpApiManagementResponse.setId(rvpApiManagementResponse.getId());

        partialUpdatedRvpApiManagementResponse.fd(UPDATED_FD).td(UPDATED_TD).respondableCountsPositive(UPDATED_RESPONDABLE_COUNTS_POSITIVE);

        restRvpApiManagementResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpApiManagementResponse.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpApiManagementResponse))
            )
            .andExpect(status().isOk());

        // Validate the RvpApiManagementResponse in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpApiManagementResponseUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRvpApiManagementResponse, rvpApiManagementResponse),
            getPersistedRvpApiManagementResponse(rvpApiManagementResponse)
        );
    }

    @Test
    @Transactional
    void fullUpdateRvpApiManagementResponseWithPatch() throws Exception {
        // Initialize the database
        insertedRvpApiManagementResponse = rvpApiManagementResponseRepository.saveAndFlush(rvpApiManagementResponse);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpApiManagementResponse using partial update
        RvpApiManagementResponse partialUpdatedRvpApiManagementResponse = new RvpApiManagementResponse();
        partialUpdatedRvpApiManagementResponse.setId(rvpApiManagementResponse.getId());

        partialUpdatedRvpApiManagementResponse
            .source(UPDATED_SOURCE)
            .lodgingId(UPDATED_LODGING_ID)
            .fd(UPDATED_FD)
            .td(UPDATED_TD)
            .respondableCountsPositive(UPDATED_RESPONDABLE_COUNTS_POSITIVE)
            .respondableCountsNegative(UPDATED_RESPONDABLE_COUNTS_NEGATIVE)
            .respondedCountsPositive(UPDATED_RESPONDED_COUNTS_POSITIVE)
            .respondedCountsNegative(UPDATED_RESPONDED_COUNTS_NEGATIVE);

        restRvpApiManagementResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpApiManagementResponse.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpApiManagementResponse))
            )
            .andExpect(status().isOk());

        // Validate the RvpApiManagementResponse in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpApiManagementResponseUpdatableFieldsEquals(
            partialUpdatedRvpApiManagementResponse,
            getPersistedRvpApiManagementResponse(partialUpdatedRvpApiManagementResponse)
        );
    }

    @Test
    @Transactional
    void patchNonExistingRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiManagementResponse.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpApiManagementResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rvpApiManagementResponse.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpApiManagementResponse))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiManagementResponse.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiManagementResponseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpApiManagementResponse))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRvpApiManagementResponse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpApiManagementResponse.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpApiManagementResponseMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rvpApiManagementResponse))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpApiManagementResponse in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRvpApiManagementResponse() throws Exception {
        // Initialize the database
        insertedRvpApiManagementResponse = rvpApiManagementResponseRepository.saveAndFlush(rvpApiManagementResponse);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rvpApiManagementResponse
        restRvpApiManagementResponseMockMvc
            .perform(delete(ENTITY_API_URL_ID, rvpApiManagementResponse.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rvpApiManagementResponseRepository.count();
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

    protected RvpApiManagementResponse getPersistedRvpApiManagementResponse(RvpApiManagementResponse rvpApiManagementResponse) {
        return rvpApiManagementResponseRepository.findById(rvpApiManagementResponse.getId()).orElseThrow();
    }

    protected void assertPersistedRvpApiManagementResponseToMatchAllProperties(RvpApiManagementResponse expectedRvpApiManagementResponse) {
        assertRvpApiManagementResponseAllPropertiesEquals(
            expectedRvpApiManagementResponse,
            getPersistedRvpApiManagementResponse(expectedRvpApiManagementResponse)
        );
    }

    protected void assertPersistedRvpApiManagementResponseToMatchUpdatableProperties(
        RvpApiManagementResponse expectedRvpApiManagementResponse
    ) {
        assertRvpApiManagementResponseAllUpdatablePropertiesEquals(
            expectedRvpApiManagementResponse,
            getPersistedRvpApiManagementResponse(expectedRvpApiManagementResponse)
        );
    }
}
