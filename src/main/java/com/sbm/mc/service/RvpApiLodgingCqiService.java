package com.sbm.mc.service;

import com.sbm.mc.domain.RvpApiLodgingCqi;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.domain.RvpApiLodgingCqi}.
 */
public interface RvpApiLodgingCqiService {
    /**
     * Save a rvpApiLodgingCqi.
     *
     * @param rvpApiLodgingCqi the entity to save.
     * @return the persisted entity.
     */
    RvpApiLodgingCqi save(RvpApiLodgingCqi rvpApiLodgingCqi);

    /**
     * Updates a rvpApiLodgingCqi.
     *
     * @param rvpApiLodgingCqi the entity to update.
     * @return the persisted entity.
     */
    RvpApiLodgingCqi update(RvpApiLodgingCqi rvpApiLodgingCqi);

    /**
     * Partially updates a rvpApiLodgingCqi.
     *
     * @param rvpApiLodgingCqi the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpApiLodgingCqi> partialUpdate(RvpApiLodgingCqi rvpApiLodgingCqi);

    /**
     * Get all the rvpApiLodgingCqis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RvpApiLodgingCqi> findAll(Pageable pageable);

    /**
     * Get the "id" rvpApiLodgingCqi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpApiLodgingCqi> findOne(Integer id);

    /**
     * Delete the "id" rvpApiLodgingCqi.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
