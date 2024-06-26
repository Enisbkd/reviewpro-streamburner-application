package com.sbm.mc.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RvpApiLodgingScoreAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiLodgingScoreAllPropertiesEquals(RvpApiLodgingScore expected, RvpApiLodgingScore actual) {
        assertRvpApiLodgingScoreAutoGeneratedPropertiesEquals(expected, actual);
        assertRvpApiLodgingScoreAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiLodgingScoreAllUpdatablePropertiesEquals(RvpApiLodgingScore expected, RvpApiLodgingScore actual) {
        assertRvpApiLodgingScoreUpdatableFieldsEquals(expected, actual);
        assertRvpApiLodgingScoreUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiLodgingScoreAutoGeneratedPropertiesEquals(RvpApiLodgingScore expected, RvpApiLodgingScore actual) {
        assertThat(expected)
            .as("Verify RvpApiLodgingScore auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiLodgingScoreUpdatableFieldsEquals(RvpApiLodgingScore expected, RvpApiLodgingScore actual) {
        assertThat(expected)
            .as("Verify RvpApiLodgingScore relevant properties")
            .satisfies(e -> assertThat(e.getLodgingId()).as("check lodgingId").isEqualTo(actual.getLodgingId()))
            .satisfies(e -> assertThat(e.getSurveyId()).as("check surveyId").isEqualTo(actual.getSurveyId()))
            .satisfies(e -> assertThat(e.getNps()).as("check nps").isEqualTo(actual.getNps()))
            .satisfies(e -> assertThat(e.getRating()).as("check rating").isEqualTo(actual.getRating()))
            .satisfies(e -> assertThat(e.getCustomScore()).as("check customScore").isEqualTo(actual.getCustomScore()))
            .satisfies(e -> assertThat(e.getFd()).as("check fd").isEqualTo(actual.getFd()))
            .satisfies(e -> assertThat(e.getTd()).as("check td").isEqualTo(actual.getTd()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiLodgingScoreUpdatableRelationshipsEquals(RvpApiLodgingScore expected, RvpApiLodgingScore actual) {}
}
