package com.sbm.mc.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RvpApiResponse.
 */
@Entity
@Table(name = "rvp_api_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "survey_id")
    private String surveyId;

    @Column(name = "lodging_id")
    private Integer lodgingId;

    @Column(name = "jhi_date")
    private Instant date;

    @Column(name = "overallsatsifaction")
    private Double overallsatsifaction;

    @Column(name = "custom_score")
    private Double customScore;

    @Column(name = "plantorevisit")
    private Boolean plantorevisit;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public RvpApiResponse id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurveyId() {
        return this.surveyId;
    }

    public RvpApiResponse surveyId(String surveyId) {
        this.setSurveyId(surveyId);
        return this;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getLodgingId() {
        return this.lodgingId;
    }

    public RvpApiResponse lodgingId(Integer lodgingId) {
        this.setLodgingId(lodgingId);
        return this;
    }

    public void setLodgingId(Integer lodgingId) {
        this.lodgingId = lodgingId;
    }

    public Instant getDate() {
        return this.date;
    }

    public RvpApiResponse date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Double getOverallsatsifaction() {
        return this.overallsatsifaction;
    }

    public RvpApiResponse overallsatsifaction(Double overallsatsifaction) {
        this.setOverallsatsifaction(overallsatsifaction);
        return this;
    }

    public void setOverallsatsifaction(Double overallsatsifaction) {
        this.overallsatsifaction = overallsatsifaction;
    }

    public Double getCustomScore() {
        return this.customScore;
    }

    public RvpApiResponse customScore(Double customScore) {
        this.setCustomScore(customScore);
        return this;
    }

    public void setCustomScore(Double customScore) {
        this.customScore = customScore;
    }

    public Boolean getPlantorevisit() {
        return this.plantorevisit;
    }

    public RvpApiResponse plantorevisit(Boolean plantorevisit) {
        this.setPlantorevisit(plantorevisit);
        return this;
    }

    public void setPlantorevisit(Boolean plantorevisit) {
        this.plantorevisit = plantorevisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RvpApiResponse)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpApiResponse) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RvpApiResponse{" +
            "id=" + getId() +
            ", surveyId='" + getSurveyId() + "'" +
            ", lodgingId=" + getLodgingId() +
            ", date='" + getDate() + "'" +
            ", overallsatsifaction=" + getOverallsatsifaction() +
            ", customScore=" + getCustomScore() +
            ", plantorevisit='" + getPlantorevisit() + "'" +
            "}";
    }
}
