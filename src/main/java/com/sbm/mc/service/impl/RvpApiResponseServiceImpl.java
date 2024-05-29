package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApiResponse;
import com.sbm.mc.repository.RvpApiResponseRepository;
import com.sbm.mc.service.RvpApiResponseService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApiResponse}.
 */
@Service
@Transactional
public class RvpApiResponseServiceImpl implements RvpApiResponseService {

    private final Logger log = LoggerFactory.getLogger(RvpApiResponseServiceImpl.class);

    private final RvpApiResponseRepository rvpApiResponseRepository;

    public RvpApiResponseServiceImpl(RvpApiResponseRepository rvpApiResponseRepository) {
        this.rvpApiResponseRepository = rvpApiResponseRepository;
    }

    @Override
    public RvpApiResponse save(RvpApiResponse rvpApiResponse) {
        log.debug("Request to save RvpApiResponse : {}", rvpApiResponse);
        return rvpApiResponseRepository.save(rvpApiResponse);
    }

    @Override
    public RvpApiResponse update(RvpApiResponse rvpApiResponse) {
        log.debug("Request to update RvpApiResponse : {}", rvpApiResponse);
        return rvpApiResponseRepository.save(rvpApiResponse);
    }

    @Override
    public Optional<RvpApiResponse> partialUpdate(RvpApiResponse rvpApiResponse) {
        log.debug("Request to partially update RvpApiResponse : {}", rvpApiResponse);

        return rvpApiResponseRepository
            .findById(rvpApiResponse.getId())
            .map(existingRvpApiResponse -> {
                if (rvpApiResponse.getSurveyId() != null) {
                    existingRvpApiResponse.setSurveyId(rvpApiResponse.getSurveyId());
                }
                if (rvpApiResponse.getLodgingId() != null) {
                    existingRvpApiResponse.setLodgingId(rvpApiResponse.getLodgingId());
                }
                if (rvpApiResponse.getDate() != null) {
                    existingRvpApiResponse.setDate(rvpApiResponse.getDate());
                }
                if (rvpApiResponse.getOverallsatsifaction() != null) {
                    existingRvpApiResponse.setOverallsatsifaction(rvpApiResponse.getOverallsatsifaction());
                }
                if (rvpApiResponse.getCustomScore() != null) {
                    existingRvpApiResponse.setCustomScore(rvpApiResponse.getCustomScore());
                }
                if (rvpApiResponse.getPlantorevisit() != null) {
                    existingRvpApiResponse.setPlantorevisit(rvpApiResponse.getPlantorevisit());
                }

                return existingRvpApiResponse;
            })
            .map(rvpApiResponseRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApiResponse> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApiResponses");
        return rvpApiResponseRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApiResponse> findOne(Integer id) {
        log.debug("Request to get RvpApiResponse : {}", id);
        return rvpApiResponseRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RvpApiResponse : {}", id);
        rvpApiResponseRepository.deleteById(id);
    }
}
