package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiLodgingScore;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApiLodgingScore}.
 */
public interface RvpApiLodgingScoreService {
    /**
     * Save a rvpApiLodgingScore.
     *
     * @param rvpApiLodgingScore the entity to save.
     * @return the persisted entity.
     */
    RvpApiLodgingScore save(RvpApiLodgingScore rvpApiLodgingScore);

    /**
     * Updates a rvpApiLodgingScore.
     *
     * @param rvpApiLodgingScore the entity to update.
     * @return the persisted entity.
     */
    RvpApiLodgingScore update(RvpApiLodgingScore rvpApiLodgingScore);

    /**
     * Partially updates a rvpApiLodgingScore.
     *
     * @param rvpApiLodgingScore the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApiLodgingScore> partialUpdate(RvpApiLodgingScore rvpApiLodgingScore);

    /**
     * Get all the rvpApiLodgingScores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApiLodgingScore> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApiLodgingScore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApiLodgingScore> findOne(Integer id);

    /**
     * Delete the "id" rvpApiLodgingScore.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
