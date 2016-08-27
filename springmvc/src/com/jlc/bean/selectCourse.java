package com.jlc.bean;

import java.io.Serializable;
import java.util.Date;

public class selectCourse implements Serializable {
    private Long selectId;

    private Long selectStudentid;

    private Long selectCourseid;

    private String selectTeacher;

    private Date selectTime;
    
    private String selectYear;

    private static final long serialVersionUID = 1L;

    public Long getSelectId() {
        return selectId;
    }

    public void setSelectId(Long selectId) {
        this.selectId = selectId;
    }

    public Long getSelectStudentid() {
        return selectStudentid;
    }

    public void setSelectStudentid(Long selectStudentid) {
        this.selectStudentid = selectStudentid;
    }

    public Long getSelectCourseid() {
        return selectCourseid;
    }

    public void setSelectCourseid(Long selectCourseid) {
        this.selectCourseid = selectCourseid;
    }

    public String getSelectTeacher() {
        return selectTeacher;
    }

    public void setSelectTeacher(String selectTeacher) {
        this.selectTeacher = selectTeacher == null ? null : selectTeacher.trim();
    }

    public Date getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(Date selectTime) {
        this.selectTime = selectTime;
    }
    
    public String getselectYear() {
		return selectYear;
	}

	public void setselectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", selectId=").append(selectId);
        sb.append(", selectStudentid=").append(selectStudentid);
        sb.append(", selectCourseid=").append(selectCourseid);
        sb.append(", selectTeacher=").append(selectTeacher);
        sb.append(", selectTime=").append(selectTime);
        sb.append(", selectYear=").append(selectYear);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}