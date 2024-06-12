package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiManagementResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApiManagementResponse}.
 */
public interface RvpApiManagementResponseService {
    /**
     * Save a rvpApiManagementResponse.
     *
     * @param rvpApiManagementResponse the entity to save.
     * @return the persisted entity.
     */
    RvpApiManagementResponse save(RvpApiManagementResponse rvpApiManagementResponse);

    /**
     * Updates a rvpApiManagementResponse.
     *
     * @param rvpApiManagementResponse the entity to update.
     * @return the persisted entity.
     */
    RvpApiManagementResponse update(RvpApiManagementResponse rvpApiManagementResponse);

    /**
     * Partially updates a rvpApiManagementResponse.
     *
     * @param rvpApiManagementResponse the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApiManagementResponse> partialUpdate(RvpApiManagementResponse rvpApiManagementResponse);

    /**
     * Get all the rvpApiManagementResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApiManagementResponse> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApiManagementResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApiManagementResponse> findOne(Integer id);

    /**
     * Delete the "id" rvpApiManagementResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
