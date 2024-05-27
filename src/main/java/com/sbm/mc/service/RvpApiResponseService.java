package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApiResponse}.
 */
public interface RvpApiResponseService {
    /**
     * Save a rvpApiResponse.
     *
     * @param rvpApiResponse the entity to save.
     * @return the persisted entity.
     */
    RvpApiResponse save(RvpApiResponse rvpApiResponse);

    /**
     * Updates a rvpApiResponse.
     *
     * @param rvpApiResponse the entity to update.
     * @return the persisted entity.
     */
    RvpApiResponse update(RvpApiResponse rvpApiResponse);

    /**
     * Partially updates a rvpApiResponse.
     *
     * @param rvpApiResponse the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApiResponse> partialUpdate(RvpApiResponse rvpApiResponse);

    /**
     * Get all the rvpApiResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApiResponse> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApiResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApiResponse> findOne(Integer id);

    /**
     * Delete the "id" rvpApiResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
