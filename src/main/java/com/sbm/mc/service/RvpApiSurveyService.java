package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiSurvey;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApiSurvey}.
 */
public interface RvpApiSurveyService {
    /**
     * Save a rvpApiSurvey.
     *
     * @param rvpApiSurvey the entity to save.
     * @return the persisted entity.
     */
    RvpApiSurvey save(RvpApiSurvey rvpApiSurvey);

    /**
     * Updates a rvpApiSurvey.
     *
     * @param rvpApiSurvey the entity to update.
     * @return the persisted entity.
     */
    RvpApiSurvey update(RvpApiSurvey rvpApiSurvey);

    /**
     * Partially updates a rvpApiSurvey.
     *
     * @param rvpApiSurvey the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApiSurvey> partialUpdate(RvpApiSurvey rvpApiSurvey);

    /**
     * Get all the rvpApiSurveys.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApiSurvey> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApiSurvey.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApiSurvey> findOne(String id);

    /**
     * Delete the "id" rvpApiSurvey.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
