package com.jlc.bean;

import java.io.Serializable;

/**
 * @description：课程
 * @author：Kurt Deng
 * @date：2016/08/6 14:51
 */
public class Course implements Serializable {

	private static final long serialVersionUID = 7977551787908310217L;

	private long id;
	
	private String courseName;
	
	private String description;
	
	private String capacity;
	
	private long major;
	
	private long grade;
	
	private long credit;
	
	private long occupied;
	
	private String courseclass;
	
	private Integer organizationId;
	
	private String organizationName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public long getMajor() {
		return major;
	}

	public void setMajor(long major) {
		this.major = major;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	public long getOccupied() {
		return occupied;
	}

	public void setOccupied(long occupied) {
		this.occupied = occupied;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}
	
	public String getCourseclass() {
		return courseclass;
	}

	public void setCourseclass(String courseclass) {
		this.courseclass = courseclass;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
	
}
