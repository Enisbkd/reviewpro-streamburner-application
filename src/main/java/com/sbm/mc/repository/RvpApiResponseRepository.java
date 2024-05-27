package com.sbm.mc.repository;

import com.sbm.mc.domain.RvpApiResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpApiResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpApiResponseRepository extends JpaRepository<RvpApiResponse, Integer> {}
