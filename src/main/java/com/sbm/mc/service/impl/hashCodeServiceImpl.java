package com.sbm.mc.service.impl;

public class hashCodeServiceImpl {

    public int hashGlobalReviewId(int productId, String fromDate, String toDate) {
        return (productId + fromDate + toDate).hashCode();
    }

    public int hashLodgingCqiId(int productId, String fromDate, String toDate) {
        return (productId + fromDate + toDate).hashCode();
    }

    public int hashLodgingScore(String surveyId, int pid, String fromDate, String toDate) {
        return (surveyId + pid + fromDate + toDate).hashCode();
    }

    public int hashResponseId(int productId, String surveyId, String fromDate, String toDate, String label) {
        return (productId + surveyId + fromDate + toDate + label).hashCode();
    }
}
