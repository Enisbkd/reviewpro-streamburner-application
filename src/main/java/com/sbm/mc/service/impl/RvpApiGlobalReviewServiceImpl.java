package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApiGlobalReview;
import com.sbm.mc.repository.RvpApiGlobalReviewRepository;
import com.sbm.mc.service.RvpApiGlobalReviewService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApiGlobalReview}.
 */
@Service
@Transactional
public class RvpApiGlobalReviewServiceImpl implements RvpApiGlobalReviewService {

    private final Logger log = LoggerFactory.getLogger(RvpApiGlobalReviewServiceImpl.class);

    private final RvpApiGlobalReviewRepository rvpApiGlobalReviewRepository;

    public RvpApiGlobalReviewServiceImpl(RvpApiGlobalReviewRepository rvpApiGlobalReviewRepository) {
        this.rvpApiGlobalReviewRepository = rvpApiGlobalReviewRepository;
    }

    @Override
    public RvpApiGlobalReview save(RvpApiGlobalReview rvpApiGlobalReview) {
        log.debug("Request to save RvpApiGlobalReview : {}", rvpApiGlobalReview);
        return rvpApiGlobalReviewRepository.save(rvpApiGlobalReview);
    }

    @Override
    public RvpApiGlobalReview update(RvpApiGlobalReview rvpApiGlobalReview) {
        log.debug("Request to update RvpApiGlobalReview : {}", rvpApiGlobalReview);
        return rvpApiGlobalReviewRepository.save(rvpApiGlobalReview);
    }

    @Override
    public Optional<RvpApiGlobalReview> partialUpdate(RvpApiGlobalReview rvpApiGlobalReview) {
        log.debug("Request to partially update RvpApiGlobalReview : {}", rvpApiGlobalReview);

        return rvpApiGlobalReviewRepository
            .findById(rvpApiGlobalReview.getId())
            .map(existingRvpApiGlobalReview -> {
                if (rvpApiGlobalReview.getLodgingid() != null) {
                    existingRvpApiGlobalReview.setLodgingid(rvpApiGlobalReview.getLodgingid());
                }
                if (rvpApiGlobalReview.getPrevGri() != null) {
                    existingRvpApiGlobalReview.setPrevGri(rvpApiGlobalReview.getPrevGri());
                }
                if (rvpApiGlobalReview.getDistribution() != null) {
                    existingRvpApiGlobalReview.setDistribution(rvpApiGlobalReview.getDistribution());
                }
                if (rvpApiGlobalReview.getGri() != null) {
                    existingRvpApiGlobalReview.setGri(rvpApiGlobalReview.getGri());
                }
                if (rvpApiGlobalReview.getFd() != null) {
                    existingRvpApiGlobalReview.setFd(rvpApiGlobalReview.getFd());
                }
                if (rvpApiGlobalReview.getTd() != null) {
                    existingRvpApiGlobalReview.setTd(rvpApiGlobalReview.getTd());
                }

                return existingRvpApiGlobalReview;
            })
            .map(rvpApiGlobalReviewRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApiGlobalReview> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApiGlobalReviews");
        return rvpApiGlobalReviewRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApiGlobalReview> findOne(Integer id) {
        log.debug("Request to get RvpApiGlobalReview : {}", id);
        return rvpApiGlobalReviewRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RvpApiGlobalReview : {}", id);
        rvpApiGlobalReviewRepository.deleteById(id);
    }
}
