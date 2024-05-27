package com.sbm.mc.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RvpApiSurvey.
 */
@Entity
@Table(name = "rvp_api_survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpApiSurvey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "overall_score_enabled")
    private Boolean overallScoreEnabled;

    @Column(name = "languages")
    private String languages;

    @Column(name = "out_of")
    private Integer outOf;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "pids")
    private String pids;

    @Column(name = "jhi_primary")
    private Boolean primary;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public RvpApiSurvey id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOverallScoreEnabled() {
        return this.overallScoreEnabled;
    }

    public RvpApiSurvey overallScoreEnabled(Boolean overallScoreEnabled) {
        this.setOverallScoreEnabled(overallScoreEnabled);
        return this;
    }

    public void setOverallScoreEnabled(Boolean overallScoreEnabled) {
        this.overallScoreEnabled = overallScoreEnabled;
    }

    public String getLanguages() {
        return this.languages;
    }

    public RvpApiSurvey languages(String languages) {
        this.setLanguages(languages);
        return this;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Integer getOutOf() {
        return this.outOf;
    }

    public RvpApiSurvey outOf(Integer outOf) {
        this.setOutOf(outOf);
        return this;
    }

    public void setOutOf(Integer outOf) {
        this.outOf = outOf;
    }

    public String getName() {
        return this.name;
    }

    public RvpApiSurvey name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return this.active;
    }

    public RvpApiSurvey active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPids() {
        return this.pids;
    }

    public RvpApiSurvey pids(String pids) {
        this.setPids(pids);
        return this;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public Boolean getPrimary() {
        return this.primary;
    }

    public RvpApiSurvey primary(Boolean primary) {
        this.setPrimary(primary);
        return this;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RvpApiSurvey)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpApiSurvey) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RvpApiSurvey{" +
            "id=" + getId() +
            ", overallScoreEnabled='" + getOverallScoreEnabled() + "'" +
            ", languages='" + getLanguages() + "'" +
            ", outOf=" + getOutOf() +
            ", name='" + getName() + "'" +
            ", active='" + getActive() + "'" +
            ", pids='" + getPids() + "'" +
            ", primary='" + getPrimary() + "'" +
            "}";
    }
}
