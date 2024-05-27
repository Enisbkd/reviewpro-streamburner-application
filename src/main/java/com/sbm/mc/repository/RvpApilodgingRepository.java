package com.sbm.mc.repository;

import com.sbm.mc.domain.RvpApilodging;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpApilodging entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpApilodgingRepository extends JpaRepository<RvpApilodging, String> {}
