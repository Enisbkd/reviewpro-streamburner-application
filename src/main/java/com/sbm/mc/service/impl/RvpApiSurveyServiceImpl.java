package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApiSurvey;
import com.sbm.mc.repository.RvpApiSurveyRepository;
import com.sbm.mc.service.RvpApiSurveyService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApiSurvey}.
 */
@Service
@Transactional
public class RvpApiSurveyServiceImpl implements RvpApiSurveyService {

    private final Logger log = LoggerFactory.getLogger(RvpApiSurveyServiceImpl.class);

    private final RvpApiSurveyRepository rvpApiSurveyRepository;

    public RvpApiSurveyServiceImpl(RvpApiSurveyRepository rvpApiSurveyRepository) {
        this.rvpApiSurveyRepository = rvpApiSurveyRepository;
    }

    @Override
    public RvpApiSurvey save(RvpApiSurvey rvpApiSurvey) {
        log.debug("Request to save RvpApiSurvey : {}", rvpApiSurvey);
        return rvpApiSurveyRepository.save(rvpApiSurvey);
    }

    @Override
    public RvpApiSurvey update(RvpApiSurvey rvpApiSurvey) {
        log.debug("Request to update RvpApiSurvey : {}", rvpApiSurvey);
        return rvpApiSurveyRepository.save(rvpApiSurvey);
    }

    @Override
    public Optional<RvpApiSurvey> partialUpdate(RvpApiSurvey rvpApiSurvey) {
        log.debug("Request to partially update RvpApiSurvey : {}", rvpApiSurvey);

        return rvpApiSurveyRepository
            .findById(rvpApiSurvey.getId())
            .map(existingRvpApiSurvey -> {
                if (rvpApiSurvey.getOverallScoreEnabled() != null) {
                    existingRvpApiSurvey.setOverallScoreEnabled(rvpApiSurvey.getOverallScoreEnabled());
                }
                if (rvpApiSurvey.getLanguages() != null) {
                    existingRvpApiSurvey.setLanguages(rvpApiSurvey.getLanguages());
                }
                if (rvpApiSurvey.getOutOf() != null) {
                    existingRvpApiSurvey.setOutOf(rvpApiSurvey.getOutOf());
                }
                if (rvpApiSurvey.getName() != null) {
                    existingRvpApiSurvey.setName(rvpApiSurvey.getName());
                }
                if (rvpApiSurvey.getActive() != null) {
                    existingRvpApiSurvey.setActive(rvpApiSurvey.getActive());
                }
                if (rvpApiSurvey.getPids() != null) {
                    existingRvpApiSurvey.setPids(rvpApiSurvey.getPids());
                }
                if (rvpApiSurvey.getPrimary() != null) {
                    existingRvpApiSurvey.setPrimary(rvpApiSurvey.getPrimary());
                }

                return existingRvpApiSurvey;
            })
            .map(rvpApiSurveyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApiSurvey> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApiSurveys");
        return rvpApiSurveyRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApiSurvey> findOne(String id) {
        log.debug("Request to get RvpApiSurvey : {}", id);
        return rvpApiSurveyRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete RvpApiSurvey : {}", id);
        rvpApiSurveyRepository.deleteById(id);
    }
}
