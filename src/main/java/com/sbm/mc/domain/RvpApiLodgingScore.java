package com.sbm.mc.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RvpApiLodgingScore.
 */
@Entity
@Table(name = "rvp_api_lodging_score")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpApiLodgingScore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "lodging_id")
    private Integer lodgingId;

    @Column(name = "survey_id")
    private String surveyId;

    @Column(name = "nps")
    private Double nps;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "custom_score")
    private Double customScore;

    @Column(name = "fd")
    private LocalDate fd;

    @Column(name = "td")
    private LocalDate td;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public RvpApiLodgingScore id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLodgingId() {
        return this.lodgingId;
    }

    public RvpApiLodgingScore lodgingId(Integer lodgingId) {
        this.setLodgingId(lodgingId);
        return this;
    }

    public void setLodgingId(Integer lodgingId) {
        this.lodgingId = lodgingId;
    }

    public String getSurveyId() {
        return this.surveyId;
    }

    public RvpApiLodgingScore surveyId(String surveyId) {
        this.setSurveyId(surveyId);
        return this;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public Double getNps() {
        return this.nps;
    }

    public RvpApiLodgingScore nps(Double nps) {
        this.setNps(nps);
        return this;
    }

    public void setNps(Double nps) {
        this.nps = nps;
    }

    public Double getRating() {
        return this.rating;
    }

    public RvpApiLodgingScore rating(Double rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getCustomScore() {
        return this.customScore;
    }

    public RvpApiLodgingScore customScore(Double customScore) {
        this.setCustomScore(customScore);
        return this;
    }

    public void setCustomScore(Double customScore) {
        this.customScore = customScore;
    }

    public LocalDate getFd() {
        return this.fd;
    }

    public RvpApiLodgingScore fd(LocalDate fd) {
        this.setFd(fd);
        return this;
    }

    public void setFd(LocalDate fd) {
        this.fd = fd;
    }

    public LocalDate getTd() {
        return this.td;
    }

    public RvpApiLodgingScore td(LocalDate td) {
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
        if (!(o instanceof RvpApiLodgingScore)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpApiLodgingScore) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RvpApiLodgingScore{" +
            "id=" + getId() +
            ", lodgingId=" + getLodgingId() +
            ", surveyId='" + getSurveyId() + "'" +
            ", nps=" + getNps() +
            ", rating=" + getRating() +
            ", customScore=" + getCustomScore() +
            ", fd='" + getFd() + "'" +
            ", td='" + getTd() + "'" +
            "}";
    }
}
