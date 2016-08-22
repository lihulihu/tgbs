package com.jlc.bean;

import java.io.Serializable;

public class SrItem implements Serializable {
    private Integer sSrId;

    private Integer sSrStudentid;

    private Integer sSrSrid;

    private String sSrStart;

    private String sSrZhong;

    private String sSrEnd;

    private String sSrSunmary;

    private static final long serialVersionUID = 1L;

    public Integer getsSrId() {
        return sSrId;
    }

    public void setsSrId(Integer sSrId) {
        this.sSrId = sSrId;
    }

    public Integer getsSrStudentid() {
        return sSrStudentid;
    }

    public void setsSrStudentid(Integer sSrStudentid) {
        this.sSrStudentid = sSrStudentid;
    }

    public Integer getsSrSrid() {
        return sSrSrid;
    }

    public void setsSrSrid(Integer sSrSrid) {
        this.sSrSrid = sSrSrid;
    }

    public String getsSrStart() {
        return sSrStart;
    }

    public void setsSrStart(String sSrStart) {
        this.sSrStart = sSrStart == null ? null : sSrStart.trim();
    }

    public String getsSrZhong() {
        return sSrZhong;
    }

    public void setsSrZhong(String sSrZhong) {
        this.sSrZhong = sSrZhong == null ? null : sSrZhong.trim();
    }

    public String getsSrEnd() {
        return sSrEnd;
    }

    public void setsSrEnd(String sSrEnd) {
        this.sSrEnd = sSrEnd == null ? null : sSrEnd.trim();
    }

    public String getsSrSunmary() {
        return sSrSunmary;
    }

    public void setsSrSunmary(String sSrSunmary) {
        this.sSrSunmary = sSrSunmary == null ? null : sSrSunmary.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sSrId=").append(sSrId);
        sb.append(", sSrStudentid=").append(sSrStudentid);
        sb.append(", sSrSrid=").append(sSrSrid);
        sb.append(", sSrStart=").append(sSrStart);
        sb.append(", sSrZhong=").append(sSrZhong);
        sb.append(", sSrEnd=").append(sSrEnd);
        sb.append(", sSrSunmary=").append(sSrSunmary);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}