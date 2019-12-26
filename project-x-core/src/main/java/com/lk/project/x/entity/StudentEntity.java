/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
// TODO : use lombok for generate getters and setters
/**
 * @author virtualpathum
 * The Class StudentEntity.
 */
@Entity
@Table(name="tbl_student")
public class StudentEntity extends AbstractEntity{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9157969392013803110L;

	/** The id. */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	//@NotEmpty
	@Column(name="student_name", nullable=false)
	private String name;

	/** The age. */
	//@NotNull
	@Column(name="student_age", nullable=false)
	private Integer age;

	/** The grade. */
	//@NotNull
	@Column(name="student_grade", nullable=false)
	private String grade;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}


	/**
	 * Gets the grade.
	 *
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * Sets the grade.
	 *
	 * @param grade the new grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
			return Objects.hash(this.id);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (obj instanceof StudentEntity) {
			StudentEntity other = (StudentEntity) obj;
			return Objects.equals(this.hashCode(), other.hashCode());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return sb.append("id = " + this.getId())
				.append(" , name = " + this.getName())
				.append(" , age = " + this.getAge())
				.append(" , grade = " + this.getGrade()).toString();
				
	}


}
