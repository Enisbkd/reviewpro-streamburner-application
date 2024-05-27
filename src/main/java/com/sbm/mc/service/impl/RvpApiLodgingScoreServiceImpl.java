package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApiLodgingScore;
import com.sbm.mc.repository.RvpApiLodgingScoreRepository;
import com.sbm.mc.service.RvpApiLodgingScoreService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApiLodgingScore}.
 */
@Service
@Transactional
public class RvpApiLodgingScoreServiceImpl implements RvpApiLodgingScoreService {

    private final Logger log = LoggerFactory.getLogger(RvpApiLodgingScoreServiceImpl.class);

    private final RvpApiLodgingScoreRepository rvpApiLodgingScoreRepository;

    public RvpApiLodgingScoreServiceImpl(RvpApiLodgingScoreRepository rvpApiLodgingScoreRepository) {
        this.rvpApiLodgingScoreRepository = rvpApiLodgingScoreRepository;
    }

    @Override
    public RvpApiLodgingScore save(RvpApiLodgingScore rvpApiLodgingScore) {
        log.debug("Request to save RvpApiLodgingScore : {}", rvpApiLodgingScore);
        return rvpApiLodgingScoreRepository.save(rvpApiLodgingScore);
    }

    @Override
    public RvpApiLodgingScore update(RvpApiLodgingScore rvpApiLodgingScore) {
        log.debug("Request to update RvpApiLodgingScore : {}", rvpApiLodgingScore);
        return rvpApiLodgingScoreRepository.save(rvpApiLodgingScore);
    }

    @Override
    public Optional<RvpApiLodgingScore> partialUpdate(RvpApiLodgingScore rvpApiLodgingScore) {
        log.debug("Request to partially update RvpApiLodgingScore : {}", rvpApiLodgingScore);

        return rvpApiLodgingScoreRepository
            .findById(rvpApiLodgingScore.getId())
            .map(existingRvpApiLodgingScore -> {
                if (rvpApiLodgingScore.getLodgingId() != null) {
                    existingRvpApiLodgingScore.setLodgingId(rvpApiLodgingScore.getLodgingId());
                }
                if (rvpApiLodgingScore.getSurveyId() != null) {
                    existingRvpApiLodgingScore.setSurveyId(rvpApiLodgingScore.getSurveyId());
                }
                if (rvpApiLodgingScore.getNps() != null) {
                    existingRvpApiLodgingScore.setNps(rvpApiLodgingScore.getNps());
                }
                if (rvpApiLodgingScore.getRating() != null) {
                    existingRvpApiLodgingScore.setRating(rvpApiLodgingScore.getRating());
                }
                if (rvpApiLodgingScore.getCustomScore() != null) {
                    existingRvpApiLodgingScore.setCustomScore(rvpApiLodgingScore.getCustomScore());
                }
                if (rvpApiLodgingScore.getFd() != null) {
                    existingRvpApiLodgingScore.setFd(rvpApiLodgingScore.getFd());
                }
                if (rvpApiLodgingScore.getTd() != null) {
                    existingRvpApiLodgingScore.setTd(rvpApiLodgingScore.getTd());
                }

                return existingRvpApiLodgingScore;
            })
            .map(rvpApiLodgingScoreRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApiLodgingScore> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApiLodgingScores");
        return rvpApiLodgingScoreRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApiLodgingScore> findOne(Integer id) {
        log.debug("Request to get RvpApiLodgingScore : {}", id);
        return rvpApiLodgingScoreRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RvpApiLodgingScore : {}", id);
        rvpApiLodgingScoreRepository.deleteById(id);
    }
}
