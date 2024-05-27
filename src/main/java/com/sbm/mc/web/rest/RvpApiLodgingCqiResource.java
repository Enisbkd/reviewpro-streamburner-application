package com.sbm.mc.web.rest;

import com.sbm.mc.domain.RvpApiLodgingCqi;
import com.sbm.mc.repository.RvpApiLodgingCqiRepository;
import com.sbm.mc.service.RvpApiLodgingCqiService;
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
 * REST controller for managing {@link com.sbm.mc.domain.RvpApiLodgingCqi}.
 */
@RestController
@RequestMapping("/api/rvp-api-lodging-cqis")
public class RvpApiLodgingCqiResource {

    private final Logger log = LoggerFactory.getLogger(RvpApiLodgingCqiResource.class);

    private static final String ENTITY_NAME = "rvpApiLodgingCqi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpApiLodgingCqiService rvpApiLodgingCqiService;

    private final RvpApiLodgingCqiRepository rvpApiLodgingCqiRepository;

    public RvpApiLodgingCqiResource(
        RvpApiLodgingCqiService rvpApiLodgingCqiService,
        RvpApiLodgingCqiRepository rvpApiLodgingCqiRepository
    ) {
        this.rvpApiLodgingCqiService = rvpApiLodgingCqiService;
        this.rvpApiLodgingCqiRepository = rvpApiLodgingCqiRepository;
    }

    /**
     * {@code POST  /rvp-api-lodging-cqis} : Create a new rvpApiLodgingCqi.
     *
     * @param rvpApiLodgingCqi the rvpApiLodgingCqi to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpApiLodgingCqi, or with status {@code 400 (Bad Request)} if the rvpApiLodgingCqi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpApiLodgingCqi> createRvpApiLodgingCqi(@RequestBody RvpApiLodgingCqi rvpApiLodgingCqi)
        throws URISyntaxException {
        log.debug("REST request to save RvpApiLodgingCqi : {}", rvpApiLodgingCqi);
        if (rvpApiLodgingCqi.getId() != null) {
            throw new BadRequestAlertException("A new rvpApiLodgingCqi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpApiLodgingCqi = rvpApiLodgingCqiService.save(rvpApiLodgingCqi);
        return ResponseEntity.created(new URI("/api/rvp-api-lodging-cqis/" + rvpApiLodgingCqi.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpApiLodgingCqi.getId().toString()))
            .body(rvpApiLodgingCqi);
    }

    /**
     * {@code PUT  /rvp-api-lodging-cqis/:id} : Updates an existing rvpApiLodgingCqi.
     *
     * @param id the id of the rvpApiLodgingCqi to save.
     * @param rvpApiLodgingCqi the rvpApiLodgingCqi to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiLodgingCqi,
     * or with status {@code 400 (Bad Request)} if the rvpApiLodgingCqi is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiLodgingCqi couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpApiLodgingCqi> updateRvpApiLodgingCqi(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiLodgingCqi rvpApiLodgingCqi
    ) throws URISyntaxException {
        log.debug("REST request to update RvpApiLodgingCqi : {}, {}", id, rvpApiLodgingCqi);
        if (rvpApiLodgingCqi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiLodgingCqi.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiLodgingCqiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpApiLodgingCqi = rvpApiLodgingCqiService.update(rvpApiLodgingCqi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiLodgingCqi.getId().toString()))
            .body(rvpApiLodgingCqi);
    }

    /**
     * {@code PATCH  /rvp-api-lodging-cqis/:id} : Partial updates given fields of an existing rvpApiLodgingCqi, field will ignore if it is null
     *
     * @param id the id of the rvpApiLodgingCqi to save.
     * @param rvpApiLodgingCqi the rvpApiLodgingCqi to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpApiLodgingCqi,
     * or with status {@code 400 (Bad Request)} if the rvpApiLodgingCqi is not valid,
     * or with status {@code 404 (Not Found)} if the rvpApiLodgingCqi is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpApiLodgingCqi couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpApiLodgingCqi> partialUpdateRvpApiLodgingCqi(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody RvpApiLodgingCqi rvpApiLodgingCqi
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpApiLodgingCqi partially : {}, {}", id, rvpApiLodgingCqi);
        if (rvpApiLodgingCqi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpApiLodgingCqi.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpApiLodgingCqiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpApiLodgingCqi> result = rvpApiLodgingCqiService.partialUpdate(rvpApiLodgingCqi);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpApiLodgingCqi.getId().toString())
        );
    }

    /**
     * {@code GET  /rvp-api-lodging-cqis} : get all the rvpApiLodgingCqis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpApiLodgingCqis in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RvpApiLodgingCqi>> getAllRvpApiLodgingCqis(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RvpApiLodgingCqis");
        Page<RvpApiLodgingCqi> page = rvpApiLodgingCqiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rvp-api-lodging-cqis/:id} : get the "id" rvpApiLodgingCqi.
     *
     * @param id the id of the rvpApiLodgingCqi to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpApiLodgingCqi, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpApiLodgingCqi> getRvpApiLodgingCqi(@PathVariable("id") Integer id) {
        log.debug("REST request to get RvpApiLodgingCqi : {}", id);
        Optional<RvpApiLodgingCqi> rvpApiLodgingCqi = rvpApiLodgingCqiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpApiLodgingCqi);
    }

    /**
     * {@code DELETE  /rvp-api-lodging-cqis/:id} : delete the "id" rvpApiLodgingCqi.
     *
     * @param id the id of the rvpApiLodgingCqi to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpApiLodgingCqi(@PathVariable("id") Integer id) {
        log.debug("REST request to delete RvpApiLodgingCqi : {}", id);
        rvpApiLodgingCqiService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
