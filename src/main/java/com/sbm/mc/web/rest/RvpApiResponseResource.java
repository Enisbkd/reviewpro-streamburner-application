package com.sbm.mc.web.rest;

import com.sbm.mc.domain.RvpApiResponse;
import com.sbm.mc.repository.RvpApiResponseRepository;
import com.sbm.mc.service.RvpApiResponseService;
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
 * REST controller for managing {@link com.sbm.mc.domain.RvpApiResponse}.
 */
@RestController
@RequestMapping("/api/rvp-api-responses")
public class RvpApiResponseResource {

    private final Logger log = LoggerFactory.getLogger(RvpApiResponseResource.class);

    private static final String ENTITY_NAME = "rvpApiResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpApiResponseService rvpApiResponseService;

    private final RvpApiResponseRepository rvpApiResponseRepository;

    public RvpApiResponseResource(RvpApiResponseService rvpApiResponseService, RvpApiResponseRepository rvpApiResponseRepository) {
        this.rvpApiResponseService = rvpApiResponseService;
        this.rvpApiResponseRepository = rvpApiResponseRepository;
    }

    /**
     * {@code POST  /rvp-api-responses} : Create a new rvpApiResponse.
     *
     * @param rvpApiResponse the rvpApiResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpApiResponse, or with status {@code 400 (Bad Request)} if the rvpApiResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpApiResponse> createRvpApiResponse(@RequestBody RvpApiResponse rvpApiResponse) throws URISyntaxException {
        log.debug("REST request to save RvpApiResponse : {}", rvpApiResponse);
        if (rvpApiResponse.getId() != null) {
            throw new BadRequestAlertException("A new rvpApiResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpApiResponse = rvpApiResponseService.save(rvpApiResponse);
        return ResponseEntity.created(new URI("/api/rvp-api-responses/" + rvpApiResponse.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpApiResponse.getId().toString()))
            .body(rvpApiResponse);
    }

    /**
     * {@code PUT  /rvp-api-responses/:id} : Updates an existing rvpApiResponse.
     *
     * @param id the id of the rvpApiResponse to save.
     * @param rvpApiResponse the rvpApiResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiResponse,
     * or with status {@code 400 (Bad Request)} if the rvpApiResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpApiResponse> updateRvpApiResponse(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiResponse rvpApiResponse
    ) throws URISyntaxException {
        log.debug("REST request to update RvpApiResponse : {}, {}", id, rvpApiResponse);
        if (rvpApiResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiResponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpApiResponse = rvpApiResponseService.update(rvpApiResponse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiResponse.getId().toString()))
            .body(rvpApiResponse);
    }

    /**
     * {@code PATCH  /rvp-api-responses/:id} : Partial updates given fields of an existing rvpApiResponse, field will ignore if it is null
     *
     * @param id the id of the rvpApiResponse to save.
     * @param rvpApiResponse the rvpApiResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiResponse,
     * or with status {@code 400 (Bad Request)} if the rvpApiResponse is not valid,
     * or with status {@code 404 (Not Found)} if the rvpApiResponse is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpApiResponse> partialUpdateRvpApiResponse(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiResponse rvpApiResponse
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpApiResponse partially : {}, {}", id, rvpApiResponse);
        if (rvpApiResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiResponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpApiResponse> result = rvpApiResponseService.partialUpdate(rvpApiResponse);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiResponse.getId().toString())
        );
    }

    /**
     * {@code GET  /rvp-api-responses} : get all the rvpApiResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpApiResponses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RvpApiResponse>> getAllRvpApiResponses(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RvpApiResponses");
        Page<RvpApiResponse> page = rvpApiResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rvp-api-responses/:id} : get the "id" rvpApiResponse.
     *
     * @param id the id of the rvpApiResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpApiResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpApiResponse> getRvpApiResponse(@PathVariable("id") Integer id) {
        log.debug("REST request to get RvpApiResponse : {}", id);
        Optional<RvpApiResponse> rvpApiResponse = rvpApiResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpApiResponse);
    }

    /**
     * {@code DELETE  /rvp-api-responses/:id} : delete the "id" rvpApiResponse.
     *
     * @param id the id of the rvpApiResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpApiResponse(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RvpApiResponse : {}", id);
        rvpApiResponseService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
