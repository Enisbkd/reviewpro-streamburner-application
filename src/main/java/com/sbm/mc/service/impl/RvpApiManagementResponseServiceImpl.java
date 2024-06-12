package com.sbm.mc.service.impl;

import com.sbm.mc.domain.RvpApiManagementResponse;
import com.sbm.mc.repository.RvpApiManagementResponseRepository;
import com.sbm.mc.service.RvpApiManagementResponseService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.mc.domain.RvpApiManagementResponse}.
 */
@Service
@Transactional
public class RvpApiManagementResponseServiceImpl implements RvpApiManagementResponseService {

    private final Logger log = LoggerFactory.getLogger(RvpApiManagementResponseServiceImpl.class);

    private final RvpApiManagementResponseRepository rvpApiManagementResponseRepository;

    public RvpApiManagementResponseServiceImpl(RvpApiManagementResponseRepository rvpApiManagementResponseRepository) {
        this.rvpApiManagementResponseRepository = rvpApiManagementResponseRepository;
    }

    @Override
    public RvpApiManagementResponse save(RvpApiManagementResponse rvpApiManagementResponse) {
        log.debug("Request to save RvpApiManagementResponse : {}", rvpApiManagementResponse);
        return rvpApiManagementResponseRepository.save(rvpApiManagementResponse);
    }

    @Override
    public RvpApiManagementResponse update(RvpApiManagementResponse rvpApiManagementResponse) {
        log.debug("Request to update RvpApiManagementResponse : {}", rvpApiManagementResponse);
        return rvpApiManagementResponseRepository.save(rvpApiManagementResponse);
    }

    @Override
    public Optional<RvpApiManagementResponse> partialUpdate(RvpApiManagementResponse rvpApiManagementResponse) {
        log.debug("Request to partially update RvpApiManagementResponse : {}", rvpApiManagementResponse);

        return rvpApiManagementResponseRepository
            .findById(rvpApiManagementResponse.getId())
            .map(existingRvpApiManagementResponse -> {
                if (rvpApiManagementResponse.getSource() != null) {
                    existingRvpApiManagementResponse.setSource(rvpApiManagementResponse.getSource());
                }
                if (rvpApiManagementResponse.getLodgingId() != null) {
                    existingRvpApiManagementResponse.setLodgingId(rvpApiManagementResponse.getLodgingId());
                }
                if (rvpApiManagementResponse.getFd() != null) {
                    existingRvpApiManagementResponse.setFd(rvpApiManagementResponse.getFd());
                }
                if (rvpApiManagementResponse.getTd() != null) {
                    existingRvpApiManagementResponse.setTd(rvpApiManagementResponse.getTd());
                }
                if (rvpApiManagementResponse.getRespondableCountsPositive() != null) {
                    existingRvpApiManagementResponse.setRespondableCountsPositive(rvpApiManagementResponse.getRespondableCountsPositive());
                }
                if (rvpApiManagementResponse.getRespondableCountsNegative() != null) {
                    existingRvpApiManagementResponse.setRespondableCountsNegative(rvpApiManagementResponse.getRespondableCountsNegative());
                }
                if (rvpApiManagementResponse.getRespondedCountsPositive() != null) {
                    existingRvpApiManagementResponse.setRespondedCountsPositive(rvpApiManagementResponse.getRespondedCountsPositive());
                }
                if (rvpApiManagementResponse.getRespondedCountsNegative() != null) {
                    existingRvpApiManagementResponse.setRespondedCountsNegative(rvpApiManagementResponse.getRespondedCountsNegative());
                }

                return existingRvpApiManagementResponse;
            })
            .map(rvpApiManagementResponseRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RvpApiManagementResponse> findAll(Pageable pageable) {
        log.debug("Request to get all RvpApiManagementResponses");
        return rvpApiManagementResponseRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RvpApiManagementResponse> findOne(Integer id) {
        log.debug("Request to get RvpApiManagementResponse : {}", id);
        return rvpApiManagementResponseRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete RvpApiManagementResponse : {}", id);
        rvpApiManagementResponseRepository.deleteById(id);
    }
}
