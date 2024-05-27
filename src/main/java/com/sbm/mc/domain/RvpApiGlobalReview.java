package com.sbm.mc.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RvpApiGlobalReview.
 */
@Entity
@Table(name = "rvp_api_global_review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpApiGlobalReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "lodgingid")
    private Integer lodgingid;

    @Column(name = "prev_gri")
    private Double prevGri;

    @Column(name = "distribution")
    private String distribution;

    @Column(name = "gri")
    private Double gri;

    @Column(name = "fd")
    private LocalDate fd;

    @Column(name = "td")
    private LocalDate td;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public RvpApiGlobalReview id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLodgingid() {
        return this.lodgingid;
    }

    public RvpApiGlobalReview lodgingid(Integer lodgingid) {
        this.setLodgingid(lodgingid);
        return this;
    }

    public void setLodgingid(Integer lodgingid) {
        this.lodgingid = lodgingid;
    }

    public Double getPrevGri() {
        return this.prevGri;
    }

    public RvpApiGlobalReview prevGri(Double prevGri) {
        this.setPrevGri(prevGri);
        return this;
    }

    public void setPrevGri(Double prevGri) {
        this.prevGri = prevGri;
    }

    public String getDistribution() {
        return this.distribution;
    }

    public RvpApiGlobalReview distribution(String distribution) {
        this.setDistribution(distribution);
        return this;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public Double getGri() {
        return this.gri;
    }

    public RvpApiGlobalReview gri(Double gri) {
        this.setGri(gri);
        return this;
    }

    public void setGri(Double gri) {
        this.gri = gri;
    }

    public LocalDate getFd() {
        return this.fd;
    }

    public RvpApiGlobalReview fd(LocalDate fd) {
        this.setFd(fd);
        return this;
    }

    public void setFd(LocalDate fd) {
        this.fd = fd;
    }

    public LocalDate getTd() {
        return this.td;
    }

    public RvpApiGlobalReview td(LocalDate td) {
        this.setTd(td);
        return this;
    }

    public void setTd(LocalDate td) {
        this.td = td;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RvpApiGlobalReview)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpApiGlobalReview) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RvpApiGlobalReview{" +
            "id=" + getId() +
            ", lodgingid=" + getLodgingid() +
            ", prevGri=" + getPrevGri() +
            ", distribution='" + getDistribution() + "'" +
            ", gri=" + getGri() +
            ", fd='" + getFd() + "'" +
            ", td='" + getTd() + "'" +
            "}";
    }
}
