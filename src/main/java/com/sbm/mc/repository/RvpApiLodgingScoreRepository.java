package com.sbm.mc.repository;

import com.sbm.mc.domain.RvpApiLodgingScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpApiLodgingScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpApiLodgingScoreRepository extends JpaRepository<RvpApiLodgingScore, Integer> {}
