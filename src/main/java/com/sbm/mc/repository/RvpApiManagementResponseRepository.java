package com.sbm.mc.repository;

import com.sbm.mc.domain.RvpApiManagementResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpApiManagementResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpApiManagementResponseRepository extends JpaRepository<RvpApiManagementResponse, Integer> {}
