package com.jlc.bean;

import java.io.Serializable;

public class Score implements Serializable {
    private Integer scoreId;

    private String selectId;

    private String score;

    private String isAgain;

    private String timesScore;

    private String testScore;

    private static final long serialVersionUID = 1L;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public String getSelectId() {
        return selectId;
    }

    public void setSelectId(String selectId) {
        this.selectId = selectId == null ? null : selectId.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(String isAgain) {
        this.isAgain = isAgain == null ? null : isAgain.trim();
    }

    public String getTimesScore() {
        return timesScore;
    }

    public void setTimesScore(String timesScore) {
        this.timesScore = timesScore == null ? null : timesScore.trim();
    }

    public String getTestScore() {
        return testScore;
    }

    public void setTestScore(String testScore) {
        this.testScore = testScore == null ? null : testScore.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scoreId=").append(scoreId);
        sb.append(", selectId=").append(selectId);
        sb.append(", score=").append(score);
        sb.append(", isAgain=").append(isAgain);
        sb.append(", timesScore=").append(timesScore);
        sb.append(", testScore=").append(testScore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}