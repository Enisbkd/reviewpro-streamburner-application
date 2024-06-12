package com.sbm.mc.web.rest;

import com.sbm.mc.domain.RvpApiManagementResponse;
import com.sbm.mc.repository.RvpApiManagementResponseRepository;
import com.sbm.mc.service.RvpApiManagementResponseService;
import com.sbm.mc.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.mc.domain.RvpApiManagementResponse}.
 */
@RestController
@RequestMapping("/api/rvp-api-management-responses")
public class RvpApiManagementResponseResource {

    private final Logger log = LoggerFactory.getLogger(RvpApiManagementResponseResource.class);

    private static final String ENTITY_NAME = "rvpApiManagementResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpApiManagementResponseService rvpApiManagementResponseService;

    private final RvpApiManagementResponseRepository rvpApiManagementResponseRepository;

    public RvpApiManagementResponseResource(
        RvpApiManagementResponseService rvpApiManagementResponseService,
        RvpApiManagementResponseRepository rvpApiManagementResponseRepository
    ) {
        this.rvpApiManagementResponseService = rvpApiManagementResponseService;
        this.rvpApiManagementResponseRepository = rvpApiManagementResponseRepository;
    }

    /**
     * {@code POST  /rvp-api-management-responses} : Create a new rvpApiManagementResponse.
     *
     * @param rvpApiManagementResponse the rvpApiManagementResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpApiManagementResponse, or with status {@code 400 (Bad Request)} if the rvpApiManagementResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpApiManagementResponse> createRvpApiManagementResponse(
        @RequestBody RvpApiManagementResponse rvpApiManagementResponse
    ) throws URISyntaxException {
        log.debug("REST request to save RvpApiManagementResponse : {}", rvpApiManagementResponse);
        if (rvpApiManagementResponse.getId() != null) {
            throw new BadRequestAlertException("A new rvpApiManagementResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpApiManagementResponse = rvpApiManagementResponseService.save(rvpApiManagementResponse);
        return ResponseEntity.created(new URI("/api/rvp-api-management-responses/" + rvpApiManagementResponse.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpApiManagementResponse.getId().toString()))
            .body(rvpApiManagementResponse);
    }

    /**
     * {@code PUT  /rvp-api-management-responses/:id} : Updates an existing rvpApiManagementResponse.
     *
     * @param id the id of the rvpApiManagementResponse to save.
     * @param rvpApiManagementResponse the rvpApiManagementResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiManagementResponse,
     * or with status {@code 400 (Bad Request)} if the rvpApiManagementResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiManagementResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpApiManagementResponse> updateRvpApiManagementResponse(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiManagementResponse rvpApiManagementResponse
    ) throws URISyntaxException {
        log.debug("REST request to update RvpApiManagementResponse : {}, {}", id, rvpApiManagementResponse);
        if (rvpApiManagementResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiManagementResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiManagementResponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpApiManagementResponse = rvpApiManagementResponseService.update(rvpApiManagementResponse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiManagementResponse.getId().toString()))
            .body(rvpApiManagementResponse);
    }

    /**
     * {@code PATCH  /rvp-api-management-responses/:id} : Partial updates given fields of an existing rvpApiManagementResponse, field will ignore if it is null
     *
     * @param id the id of the rvpApiManagementResponse to save.
     * @param rvpApiManagementResponse the rvpApiManagementResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiManagementResponse,
     * or with status {@code 400 (Bad Request)} if the rvpApiManagementResponse is not valid,
     * or with status {@code 404 (Not Found)} if the rvpApiManagementResponse is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiManagementResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpApiManagementResponse> partialUpdateRvpApiManagementResponse(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiManagementResponse rvpApiManagementResponse
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpApiManagementResponse partially : {}, {}", id, rvpApiManagementResponse);
        if (rvpApiManagementResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiManagementResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiManagementResponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpApiManagementResponse> result = rvpApiManagementResponseService.partialUpdate(rvpApiManagementResponse);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiManagementResponse.getId().toString())
        );
    }

    /**
     * {@code GET  /rvp-api-management-responses} : get all the rvpApiManagementResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpApiManagementResponses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RvpApiManagementResponse>> getAllRvpApiManagementResponses(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RvpApiManagementResponses");
        Page<RvpApiManagementResponse> page = rvpApiManagementResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rvp-api-management-responses/:id} : get the "id" rvpApiManagementResponse.
     *
     * @param id the id of the rvpApiManagementResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpApiManagementResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpApiManagementResponse> getRvpApiManagementResponse(@PathVariable("id") Integer id) {
        log.debug("REST request to get RvpApiManagementResponse : {}", id);
        Optional<RvpApiManagementResponse> rvpApiManagementResponse = rvpApiManagementResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpApiManagementResponse);
    }

    /**
     * {@code DELETE  /rvp-api-management-responses/:id} : delete the "id" rvpApiManagementResponse.
     *
     * @param id the id of the rvpApiManagementResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpApiManagementResponse(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RvpApiManagementResponse : {}", id);
        rvpApiManagementResponseService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
