package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApilodging;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApilodging}.
 */
public interface RvpApilodgingService {
    /**
     * Save a rvpApilodging.
     *
     * @param rvpApilodging the entity to save.
     * @return the persisted entity.
     */
    RvpApilodging save(RvpApilodging rvpApilodging);

    /**
     * Updates a rvpApilodging.
     *
     * @param rvpApilodging the entity to update.
     * @return the persisted entity.
     */
    RvpApilodging update(RvpApilodging rvpApilodging);

    /**
     * Partially updates a rvpApilodging.
     *
     * @param rvpApilodging the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApilodging> partialUpdate(RvpApilodging rvpApilodging);

    /**
     * Get all the rvpApilodgings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApilodging> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApilodging.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApilodging> findOne(String id);

    /**
     * Delete the "id" rvpApilodging.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
