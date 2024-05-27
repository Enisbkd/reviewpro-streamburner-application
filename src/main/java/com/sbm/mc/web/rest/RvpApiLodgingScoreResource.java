package com.sbm.mc.web.rest;

import com.sbm.mc.domain.RvpApiLodgingScore;
import com.sbm.mc.repository.RvpApiLodgingScoreRepository;
import com.sbm.mc.service.RvpApiLodgingScoreService;
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
 * REST controller for managing {@link com.sbm.mc.domain.RvpApiLodgingScore}.
 */
@RestController
@RequestMapping("/api/rvp-api-lodging-scores")
public class RvpApiLodgingScoreResource {

    private final Logger log = LoggerFactory.getLogger(RvpApiLodgingScoreResource.class);

    private static final String ENTITY_NAME = "rvpApiLodgingScore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpApiLodgingScoreService rvpApiLodgingScoreService;

    private final RvpApiLodgingScoreRepository rvpApiLodgingScoreRepository;

    public RvpApiLodgingScoreResource(
        RvpApiLodgingScoreService rvpApiLodgingScoreService,
        RvpApiLodgingScoreRepository rvpApiLodgingScoreRepository
    ) {
        this.rvpApiLodgingScoreService = rvpApiLodgingScoreService;
        this.rvpApiLodgingScoreRepository = rvpApiLodgingScoreRepository;
    }

    /**
     * {@code POST  /rvp-api-lodging-scores} : Create a new rvpApiLodgingScore.
     *
     * @param rvpApiLodgingScore the rvpApiLodgingScore to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpApiLodgingScore, or with status {@code 400 (Bad Request)} if the rvpApiLodgingScore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpApiLodgingScore> createRvpApiLodgingScore(@RequestBody RvpApiLodgingScore rvpApiLodgingScore)
        throws URISyntaxException {
        log.debug("REST request to save RvpApiLodgingScore : {}", rvpApiLodgingScore);
        if (rvpApiLodgingScore.getId() != null) {
            throw new BadRequestAlertException("A new rvpApiLodgingScore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpApiLodgingScore = rvpApiLodgingScoreService.save(rvpApiLodgingScore);
        return ResponseEntity.created(new URI("/api/rvp-api-lodging-scores/" + rvpApiLodgingScore.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpApiLodgingScore.getId().toString()))
            .body(rvpApiLodgingScore);
    }

    /**
     * {@code PUT  /rvp-api-lodging-scores/:id} : Updates an existing rvpApiLodgingScore.
     *
     * @param id the id of the rvpApiLodgingScore to save.
     * @param rvpApiLodgingScore the rvpApiLodgingScore to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiLodgingScore,
     * or with status {@code 400 (Bad Request)} if the rvpApiLodgingScore is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiLodgingScore couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpApiLodgingScore> updateRvpApiLodgingScore(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiLodgingScore rvpApiLodgingScore
    ) throws URISyntaxException {
        log.debug("REST request to update RvpApiLodgingScore : {}, {}", id, rvpApiLodgingScore);
        if (rvpApiLodgingScore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiLodgingScore.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiLodgingScoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpApiLodgingScore = rvpApiLodgingScoreService.update(rvpApiLodgingScore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiLodgingScore.getId().toString()))
            .body(rvpApiLodgingScore);
    }

    /**
     * {@code PATCH  /rvp-api-lodging-scores/:id} : Partial updates given fields of an existing rvpApiLodgingScore, field will ignore if it is null
     *
     * @param id the id of the rvpApiLodgingScore to save.
     * @param rvpApiLodgingScore the rvpApiLodgingScore to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiLodgingScore,
     * or with status {@code 400 (Bad Request)} if the rvpApiLodgingScore is not valid,
     * or with status {@code 404 (Not Found)} if the rvpApiLodgingScore is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiLodgingScore couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpApiLodgingScore> partialUpdateRvpApiLodgingScore(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiLodgingScore rvpApiLodgingScore
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpApiLodgingScore partially : {}, {}", id, rvpApiLodgingScore);
        if (rvpApiLodgingScore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiLodgingScore.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiLodgingScoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpApiLodgingScore> result = rvpApiLodgingScoreService.partialUpdate(rvpApiLodgingScore);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiLodgingScore.getId().toString())
        );
    }

    /**
     * {@code GET  /rvp-api-lodging-scores} : get all the rvpApiLodgingScores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpApiLodgingScores in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RvpApiLodgingScore>> getAllRvpApiLodgingScores(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RvpApiLodgingScores");
        Page<RvpApiLodgingScore> page = rvpApiLodgingScoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rvp-api-lodging-scores/:id} : get the "id" rvpApiLodgingScore.
     *
     * @param id the id of the rvpApiLodgingScore to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpApiLodgingScore, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpApiLodgingScore> getRvpApiLodgingScore(@PathVariable("id") Integer id) {
        log.debug("REST request to get RvpApiLodgingScore : {}", id);
        Optional<RvpApiLodgingScore> rvpApiLodgingScore = rvpApiLodgingScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpApiLodgingScore);
    }

    /**
     * {@code DELETE  /rvp-api-lodging-scores/:id} : delete the "id" rvpApiLodgingScore.
     *
     * @param id the id of the rvpApiLodgingScore to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpApiLodgingScore(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RvpApiLodgingScore : {}", id);
        rvpApiLodgingScoreService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
