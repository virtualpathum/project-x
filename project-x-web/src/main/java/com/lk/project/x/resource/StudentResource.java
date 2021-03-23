/**
 * Created On : 17 Aug 2017
 */
package com.lk.project.x.resource;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentResource.
 * @author virtualpathum
 */
public class StudentResource extends AbstractResource<Long> {
	
	/**
	 * Instantiates a new student resource.
	 *
	 * @param id the id
	 */
	public StudentResource(Long id) {
		super(id);
		
	}
	
	/**
	 * Instantiates a new student resource.
	 */
	// For testing purpose only
	public StudentResource(){
		super(null);
	}

	/** The name. */
	private String name;


	/** The age. */
	private Integer age;


	/** The grade. */
	private String grade;
	
	
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return sb.append("id = " + this.getId())
				.append("name = " + this.getName())
				.append("age = " + this.getAge())
				.append("grade = " + this.getGrade())
				.toString();
				
	}
	
}
