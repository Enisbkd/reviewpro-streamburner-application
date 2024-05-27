package com.sbm.mc.web.rest;

import com.sbm.mc.domain.RvpApiGlobalReview;
import com.sbm.mc.repository.RvpApiGlobalReviewRepository;
import com.sbm.mc.service.RvpApiGlobalReviewService;
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
 * REST controller for managing {@link com.sbm.mc.domain.RvpApiGlobalReview}.
 */
@RestController
@RequestMapping("/api/rvp-api-global-reviews")
public class RvpApiGlobalReviewResource {

    private final Logger log = LoggerFactory.getLogger(RvpApiGlobalReviewResource.class);

    private static final String ENTITY_NAME = "rvpApiGlobalReview";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpApiGlobalReviewService rvpApiGlobalReviewService;

    private final RvpApiGlobalReviewRepository rvpApiGlobalReviewRepository;

    public RvpApiGlobalReviewResource(
        RvpApiGlobalReviewService rvpApiGlobalReviewService,
        RvpApiGlobalReviewRepository rvpApiGlobalReviewRepository
    ) {
        this.rvpApiGlobalReviewService = rvpApiGlobalReviewService;
        this.rvpApiGlobalReviewRepository = rvpApiGlobalReviewRepository;
    }

    /**
     * {@code POST  /rvp-api-global-reviews} : Create a new rvpApiGlobalReview.
     *
     * @param rvpApiGlobalReview the rvpApiGlobalReview to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpApiGlobalReview, or with status {@code 400 (Bad Request)} if the rvpApiGlobalReview has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpApiGlobalReview> createRvpApiGlobalReview(@RequestBody RvpApiGlobalReview rvpApiGlobalReview)
        throws URISyntaxException {
        log.debug("REST request to save RvpApiGlobalReview : {}", rvpApiGlobalReview);
        if (rvpApiGlobalReview.getId() != null) {
            throw new BadRequestAlertException("A new rvpApiGlobalReview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpApiGlobalReview = rvpApiGlobalReviewService.save(rvpApiGlobalReview);
        return ResponseEntity.created(new URI("/api/rvp-api-global-reviews/" + rvpApiGlobalReview.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpApiGlobalReview.getId().toString()))
            .body(rvpApiGlobalReview);
    }

    /**
     * {@code PUT  /rvp-api-global-reviews/:id} : Updates an existing rvpApiGlobalReview.
     *
     * @param id the id of the rvpApiGlobalReview to save.
     * @param rvpApiGlobalReview the rvpApiGlobalReview to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiGlobalReview,
     * or with status {@code 400 (Bad Request)} if the rvpApiGlobalReview is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiGlobalReview couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpApiGlobalReview> updateRvpApiGlobalReview(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiGlobalReview rvpApiGlobalReview
    ) throws URISyntaxException {
        log.debug("REST request to update RvpApiGlobalReview : {}, {}", id, rvpApiGlobalReview);
        if (rvpApiGlobalReview.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiGlobalReview.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiGlobalReviewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpApiGlobalReview = rvpApiGlobalReviewService.update(rvpApiGlobalReview);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiGlobalReview.getId().toString()))
            .body(rvpApiGlobalReview);
    }

    /**
     * {@code PATCH  /rvp-api-global-reviews/:id} : Partial updates given fields of an existing rvpApiGlobalReview, field will ignore if it is null
     *
     * @param id the id of the rvpApiGlobalReview to save.
     * @param rvpApiGlobalReview the rvpApiGlobalReview to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiGlobalReview,
     * or with status {@code 400 (Bad Request)} if the rvpApiGlobalReview is not valid,
     * or with status {@code 404 (Not Found)} if the rvpApiGlobalReview is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiGlobalReview couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpApiGlobalReview> partialUpdateRvpApiGlobalReview(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiGlobalReview rvpApiGlobalReview
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpApiGlobalReview partially : {}, {}", id, rvpApiGlobalReview);
        if (rvpApiGlobalReview.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiGlobalReview.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiGlobalReviewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpApiGlobalReview> result = rvpApiGlobalReviewService.partialUpdate(rvpApiGlobalReview);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiGlobalReview.getId().toString())
        );
    }

    /**
     * {@code GET  /rvp-api-global-reviews} : get all the rvpApiGlobalReviews.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpApiGlobalReviews in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RvpApiGlobalReview>> getAllRvpApiGlobalReviews(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RvpApiGlobalReviews");
        Page<RvpApiGlobalReview> page = rvpApiGlobalReviewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rvp-api-global-reviews/:id} : get the "id" rvpApiGlobalReview.
     *
     * @param id the id of the rvpApiGlobalReview to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpApiGlobalReview, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpApiGlobalReview> getRvpApiGlobalReview(@PathVariable("id") Integer id) {
        log.debug("REST request to get RvpApiGlobalReview : {}", id);
        Optional<RvpApiGlobalReview> rvpApiGlobalReview = rvpApiGlobalReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpApiGlobalReview);
    }

    /**
     * {@code DELETE  /rvp-api-global-reviews/:id} : delete the "id" rvpApiGlobalReview.
     *
     * @param id the id of the rvpApiGlobalReview to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpApiGlobalReview(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RvpApiGlobalReview : {}", id);
        rvpApiGlobalReviewService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
