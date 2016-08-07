package com.jlc.bean;

import java.io.Serializable;

public class PublicParam implements Serializable {
    private Integer publicId;

    private String publicCode;

    private String publicValueId;

    private String publicValueName;

    private String publicName;

    private String status;
    
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getPublicId() {
        return publicId;
    }

    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode == null ? null : publicCode.trim();
    }

    public String getPublicValueId() {
        return publicValueId;
    }

    public void setPublicValueId(String publicValueId) {
        this.publicValueId = publicValueId == null ? null : publicValueId.trim();
    }

    public String getPublicValueName() {
        return publicValueName;
    }

    public void setPublicValueName(String publicValueName) {
        this.publicValueName = publicValueName == null ? null : publicValueName.trim();
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName == null ? null : publicName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", publicId=").append(publicId);
        sb.append(", publicCode=").append(publicCode);
        sb.append(", publicValueId=").append(publicValueId);
        sb.append(", publicValueName=").append(publicValueName);
        sb.append(", publicName=").append(publicName);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}