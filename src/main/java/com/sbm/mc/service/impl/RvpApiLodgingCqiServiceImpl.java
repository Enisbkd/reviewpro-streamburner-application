package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApiLodgingCqi;
import com.sbm.mc.repository.RvpApiLodgingCqiRepository;
import com.sbm.mc.service.RvpApiLodgingCqiService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApiLodgingCqi}.
 */
@Service
@Transactional
public class RvpApiLodgingCqiServiceImpl implements RvpApiLodgingCqiService {

    private final Logger log = LoggerFactory.getLogger(RvpApiLodgingCqiServiceImpl.class);

    private final RvpApiLodgingCqiRepository rvpApiLodgingCqiRepository;

    public RvpApiLodgingCqiServiceImpl(RvpApiLodgingCqiRepository rvpApiLodgingCqiRepository) {
        this.rvpApiLodgingCqiRepository = rvpApiLodgingCqiRepository;
    }

    @Override
    public RvpApiLodgingCqi save(RvpApiLodgingCqi rvpApiLodgingCqi) {
        log.debug("Request to save RvpApiLodgingCqi : {}", rvpApiLodgingCqi);
        return rvpApiLodgingCqiRepository.save(rvpApiLodgingCqi);
    }

    @Override
    public RvpApiLodgingCqi update(RvpApiLodgingCqi rvpApiLodgingCqi) {
        log.debug("Request to update RvpApiLodgingCqi : {}", rvpApiLodgingCqi);
        return rvpApiLodgingCqiRepository.save(rvpApiLodgingCqi);
    }

    @Override
    public Optional<RvpApiLodgingCqi> partialUpdate(RvpApiLodgingCqi rvpApiLodgingCqi) {
        log.debug("Request to partially update RvpApiLodgingCqi : {}", rvpApiLodgingCqi);

        return rvpApiLodgingCqiRepository
            .findById(rvpApiLodgingCqi.getId())
            .map(existingRvpApiLodgingCqi -> {
                if (rvpApiLodgingCqi.getLodgingId() != null) {
                    existingRvpApiLodgingCqi.setLodgingId(rvpApiLodgingCqi.getLodgingId());
                }
                if (rvpApiLodgingCqi.getAverageCurrentPeriod() != null) {
                    existingRvpApiLodgingCqi.setAverageCurrentPeriod(rvpApiLodgingCqi.getAverageCurrentPeriod());
                }
                if (rvpApiLodgingCqi.getTendancy() != null) {
                    existingRvpApiLodgingCqi.setTendancy(rvpApiLodgingCqi.getTendancy());
                }
                if (rvpApiLodgingCqi.getChange() != null) {
                    existingRvpApiLodgingCqi.setChange(rvpApiLodgingCqi.getChange());
                }
                if (rvpApiLodgingCqi.getName() != null) {
                    existingRvpApiLodgingCqi.setName(rvpApiLodgingCqi.getName());
                }
                if (rvpApiLodgingCqi.getAveragePreviousPeriod() != null) {
                    existingRvpApiLodgingCqi.setAveragePreviousPeriod(rvpApiLodgingCqi.getAveragePreviousPeriod());
                }
                if (rvpApiLodgingCqi.getFd() != null) {
                    existingRvpApiLodgingCqi.setFd(rvpApiLodgingCqi.getFd());
                }
                if (rvpApiLodgingCqi.getTd() != null) {
                    existingRvpApiLodgingCqi.setTd(rvpApiLodgingCqi.getTd());
                }

                return existingRvpApiLodgingCqi;
            })
            .map(rvpApiLodgingCqiRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApiLodgingCqi> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApiLodgingCqis");
        return rvpApiLodgingCqiRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApiLodgingCqi> findOne(Integer id) {
        log.debug("Request to get RvpApiLodgingCqi : {}", id);
        return rvpApiLodgingCqiRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RvpApiLodgingCqi : {}", id);
        rvpApiLodgingCqiRepository.deleteById(id);
    }
}
