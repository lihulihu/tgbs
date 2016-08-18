package com.jlc.bean;

import java.io.Serializable;

public class Sr implements Serializable {
    private Integer srId;

    private String srName;

    private String srFrom;

    private String srType;

    private String srGrade;

    private Double srFunds;

    private String srStatus;

    private String srRemark;

    private static final long serialVersionUID = 1L;

    public Integer getSrId() {
        return srId;
    }

    public void setSrId(Integer srId) {
        this.srId = srId;
    }

    public String getSrName() {
        return srName;
    }

    public void setSrName(String srName) {
        this.srName = srName == null ? null : srName.trim();
    }

    public String getSrFrom() {
        return srFrom;
    }

    public void setSrFrom(String srFrom) {
        this.srFrom = srFrom == null ? null : srFrom.trim();
    }

    public String getSrType() {
        return srType;
    }

    public void setSrType(String srType) {
        this.srType = srType == null ? null : srType.trim();
    }

    public String getSrGrade() {
        return srGrade;
    }

    public void setSrGrade(String srGrade) {
        this.srGrade = srGrade == null ? null : srGrade.trim();
    }

    public Double getSrFunds() {
        return srFunds;
    }

    public void setSrFunds(Double srFunds) {
        this.srFunds = srFunds;
    }

    public String getSrStatus() {
        return srStatus;
    }

    public void setSrStatus(String srStatus) {
        this.srStatus = srStatus == null ? null : srStatus.trim();
    }

    public String getSrRemark() {
        return srRemark;
    }

    public void setSrRemark(String srRemark) {
        this.srRemark = srRemark == null ? null : srRemark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", srId=").append(srId);
        sb.append(", srName=").append(srName);
        sb.append(", srFrom=").append(srFrom);
        sb.append(", srType=").append(srType);
        sb.append(", srGrade=").append(srGrade);
        sb.append(", srFunds=").append(srFunds);
        sb.append(", srStatus=").append(srStatus);
        sb.append(", srRemark=").append(srRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}