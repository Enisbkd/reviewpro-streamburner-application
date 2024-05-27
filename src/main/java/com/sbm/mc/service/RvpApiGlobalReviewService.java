package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiGlobalReview;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApiGlobalReview}.
 */
public interface RvpApiGlobalReviewService {
    /**
     * Save a rvpApiGlobalReview.
     *
     * @param rvpApiGlobalReview the entity to save.
     * @return the persisted entity.
     */
    RvpApiGlobalReview save(RvpApiGlobalReview rvpApiGlobalReview);

    /**
     * Updates a rvpApiGlobalReview.
     *
     * @param rvpApiGlobalReview the entity to update.
     * @return the persisted entity.
     */
    RvpApiGlobalReview update(RvpApiGlobalReview rvpApiGlobalReview);

    /**
     * Partially updates a rvpApiGlobalReview.
     *
     * @param rvpApiGlobalReview the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApiGlobalReview> partialUpdate(RvpApiGlobalReview rvpApiGlobalReview);

    /**
     * Get all the rvpApiGlobalReviews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApiGlobalReview> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApiGlobalReview.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApiGlobalReview> findOne(Integer id);

    /**
     * Delete the "id" rvpApiGlobalReview.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
