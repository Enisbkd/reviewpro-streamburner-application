package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApilodging;
import com.sbm.mc.repository.RvpApilodgingRepository;
import com.sbm.mc.service.RvpApilodgingService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApilodging}.
 */
@Service
@Transactional
public class RvpApilodgingServiceImpl implements RvpApilodgingService {

    private final Logger log = LoggerFactory.getLogger(RvpApilodgingServiceImpl.class);

    private final RvpApilodgingRepository rvpApilodgingRepository;

    public RvpApilodgingServiceImpl(RvpApilodgingRepository rvpApilodgingRepository) {
        this.rvpApilodgingRepository = rvpApilodgingRepository;
    }

    @Override
    public RvpApilodging save(RvpApilodging rvpApilodging) {
        log.debug("Request to save RvpApilodging : {}", rvpApilodging);
        return rvpApilodgingRepository.save(rvpApilodging);
    }

    @Override
    public RvpApilodging update(RvpApilodging rvpApilodging) {
        log.debug("Request to update RvpApilodging : {}", rvpApilodging);
        return rvpApilodgingRepository.save(rvpApilodging);
    }

    @Override
    public Optional<RvpApilodging> partialUpdate(RvpApilodging rvpApilodging) {
        log.debug("Request to partially update RvpApilodging : {}", rvpApilodging);

        return rvpApilodgingRepository
            .findById(rvpApilodging.getId())
            .map(existingRvpApilodging -> {
                if (rvpApilodging.getName() != null) {
                    existingRvpApilodging.setName(rvpApilodging.getName());
                }

                return existingRvpApilodging;
            })
            .map(rvpApilodgingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApilodging> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApilodgings");
        return rvpApilodgingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApilodging> findOne(String id) {
        log.debug("Request to get RvpApilodging : {}", id);
        return rvpApilodgingRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete RvpApilodging : {}", id);
        rvpApilodgingRepository.deleteById(id);
    }
}
