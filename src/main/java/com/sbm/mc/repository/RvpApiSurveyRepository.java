package com.sbm.mc.repository;

import com.sbm.mc.domain.RvpApiSurvey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RvpApiSurvey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpApiSurveyRepository extends JpaRepository<RvpApiSurvey, String> {}
