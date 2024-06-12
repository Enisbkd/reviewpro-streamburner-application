package com.sbm.mc.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RvpApiManagementResponse.
 */
@Entity
@Table(name = "rvp_api_management_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpApiManagementResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "source")
    private String source;

    @Column(name = "lodging_id")
    private Integer lodgingId;

    @Column(name = "fd")
    private LocalDate fd;

    @Column(name = "td")
    private LocalDate td;

    @Column(name = "respondable_counts_positive")
    private Integer respondableCountsPositive;

    @Column(name = "respondable_counts_negative")
    private Integer respondableCountsNegative;

    @Column(name = "responded_counts_positive")
    private Integer respondedCountsPositive;

    @Column(name = "responded_counts_negative")
    private Integer respondedCountsNegative;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public RvpApiManagementResponse id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return this.source;
    }

    public RvpApiManagementResponse source(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getLodgingId() {
        return this.lodgingId;
    }

    public RvpApiManagementResponse lodgingId(Integer lodgingId) {
        this.setLodgingId(lodgingId);
        return this;
    }

    public void setLodgingId(Integer lodgingId) {
        this.lodgingId = lodgingId;
    }

    public LocalDate getFd() {
        return this.fd;
    }

    public RvpApiManagementResponse fd(LocalDate fd) {
        this.setFd(fd);
        return this;
    }

    public void setFd(LocalDate fd) {
        this.fd = fd;
    }

    public LocalDate getTd() {
        return this.td;
    }

    public RvpApiManagementResponse td(LocalDate td) {
        this.setTd(td);
        return this;
    }

    public void setTd(LocalDate td) {
        this.td = td;
    }

    public Integer getRespondableCountsPositive() {
        return this.respondableCountsPositive;
    }

    public RvpApiManagementResponse respondableCountsPositive(Integer respondableCountsPositive) {
        this.setRespondableCountsPositive(respondableCountsPositive);
        return this;
    }

    public void setRespondableCountsPositive(Integer respondableCountsPositive) {
        this.respondableCountsPositive = respondableCountsPositive;
    }

    public Integer getRespondableCountsNegative() {
        return this.respondableCountsNegative;
    }

    public RvpApiManagementResponse respondableCountsNegative(Integer respondableCountsNegative) {
        this.setRespondableCountsNegative(respondableCountsNegative);
        return this;
    }

    public void setRespondableCountsNegative(Integer respondableCountsNegative) {
        this.respondableCountsNegative = respondableCountsNegative;
    }

    public Integer getRespondedCountsPositive() {
        return this.respondedCountsPositive;
    }

    public RvpApiManagementResponse respondedCountsPositive(Integer respondedCountsPositive) {
        this.setRespondedCountsPositive(respondedCountsPositive);
        return this;
    }

    public void setRespondedCountsPositive(Integer respondedCountsPositive) {
        this.respondedCountsPositive = respondedCountsPositive;
    }

    public Integer getRespondedCountsNegative() {
        return this.respondedCountsNegative;
    }

    public RvpApiManagementResponse respondedCountsNegative(Integer respondedCountsNegative) {
        this.setRespondedCountsNegative(respondedCountsNegative);
        return this;
    }

    public void setRespondedCountsNegative(Integer respondedCountsNegative) {
        this.respondedCountsNegative = respondedCountsNegative;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RvpApiManagementResponse)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpApiManagementResponse) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RvpApiManagementResponse{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", lodgingId=" + getLodgingId() +
            ", fd='" + getFd() + "'" +
            ", td='" + getTd() + "'" +
            ", respondableCountsPositive=" + getRespondableCountsPositive() +
            ", respondableCountsNegative=" + getRespondableCountsNegative() +
            ", respondedCountsPositive=" + getRespondedCountsPositive() +
            ", respondedCountsNegative=" + getRespondedCountsNegative() +
            "}";
    }
}
