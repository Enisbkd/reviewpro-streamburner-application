package com.sbm.mc.repository;

import com.sbm.mc.domain.RvpApiLodgingCqi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpApiLodgingCqi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpApiLodgingCqiRepository extends JpaRepository<RvpApiLodgingCqi, Integer> {}
