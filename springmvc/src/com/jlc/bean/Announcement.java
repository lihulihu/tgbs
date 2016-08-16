package com.jlc.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Announcement implements Serializable {
    private Integer announcementId;

    private String announcementTitle;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date announcementDate;

    private Integer announcementTotal;

    private String announcementAbstract;

    private String announcementText;

    private static final long serialVersionUID = 1L;

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle == null ? null : announcementTitle.trim();
    }

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(Date announcementDate) {
        this.announcementDate = announcementDate;
    }

    public Integer getAnnouncementTotal() {
        return announcementTotal;
    }

    public void setAnnouncementTotal(Integer announcementTotal) {
        this.announcementTotal = announcementTotal;
    }

    public String getAnnouncementAbstract() {
        return announcementAbstract;
    }

    public void setAnnouncementAbstract(String announcementAbstract) {
        this.announcementAbstract = announcementAbstract == null ? null : announcementAbstract.trim();
    }

    public String getAnnouncementText() {
        return announcementText;
    }

    public void setAnnouncementText(String announcementText) {
        this.announcementText = announcementText == null ? null : announcementText.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", announcementId=").append(announcementId);
        sb.append(", announcementTitle=").append(announcementTitle);
        sb.append(", announcementDate=").append(announcementDate);
        sb.append(", announcementTotal=").append(announcementTotal);
        sb.append(", announcementAbstract=").append(announcementAbstract);
        sb.append(", announcementText=").append(announcementText);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}