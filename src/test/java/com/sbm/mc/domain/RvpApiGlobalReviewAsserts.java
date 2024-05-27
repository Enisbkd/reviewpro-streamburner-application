package com.sbm.mc.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RvpApiGlobalReviewAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiGlobalReviewAllPropertiesEquals(RvpApiGlobalReview expected, RvpApiGlobalReview actual) {
        assertRvpApiGlobalReviewAutoGeneratedPropertiesEquals(expected, actual);
        assertRvpApiGlobalReviewAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiGlobalReviewAllUpdatablePropertiesEquals(RvpApiGlobalReview expected, RvpApiGlobalReview actual) {
        assertRvpApiGlobalReviewUpdatableFieldsEquals(expected, actual);
        assertRvpApiGlobalReviewUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiGlobalReviewAutoGeneratedPropertiesEquals(RvpApiGlobalReview expected, RvpApiGlobalReview actual) {
        assertThat(expected)
            .as("Verify RvpApiGlobalReview auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiGlobalReviewUpdatableFieldsEquals(RvpApiGlobalReview expected, RvpApiGlobalReview actual) {
        assertThat(expected)
            .as("Verify RvpApiGlobalReview relevant properties")
            .satisfies(e -> assertThat(e.getLodgingid()).as("check lodgingid").isEqualTo(actual.getLodgingid()))
            .satisfies(e -> assertThat(e.getPrevGri()).as("check prevGri").isEqualTo(actual.getPrevGri()))
            .satisfies(e -> assertThat(e.getDistribution()).as("check distribution").isEqualTo(actual.getDistribution()))
            .satisfies(e -> assertThat(e.getGri()).as("check gri").isEqualTo(actual.getGri()))
            .satisfies(e -> assertThat(e.getFd()).as("check fd").isEqualTo(actual.getFd()))
            .satisfies(e -> assertThat(e.getTd()).as("check td").isEqualTo(actual.getTd()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpApiGlobalReviewUpdatableRelationshipsEquals(RvpApiGlobalReview expected, RvpApiGlobalReview actual) {}
}