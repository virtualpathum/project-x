/**
 * Created On : 17 Aug 2017
 */
package com.lk.project.x.mapper;


import com.lk.project.x.entity.StudentEntity;
import com.lk.project.x.resource.StudentResource;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentMapper.
 *
 * @author virtualpathum
 */
@Mapper(withIoC = IoC.SPRING)
public interface StudentMapper {
	

	/**
	 * As resource.
	 *
	 * @param entity the entity
	 * @return the student resource
	 */
	@Maps(withIgnoreMissing = IgnoreMissing.DESTINATION)
	StudentResource asResource(StudentEntity entity);
	

	/**
	 * As entity.
	 *
	 * @param resource the resource
	 * @return the student entity
	 */
	@Maps(withIgnoreMissing = IgnoreMissing.SOURCE)
	StudentEntity asEntity(StudentResource resource);
	

	/**
	 * Update resource.
	 *
	 * @param entity the entity
	 * @param resource the resource
	 * @return the student resource
	 */
	@Maps(withIgnoreMissing = IgnoreMissing.DESTINATION)
	StudentResource updateResource(StudentEntity entity, StudentResource resource);
	

	/**
	 * Update entity.
	 *
	 * @param resource the resource
	 * @param entity the entity
	 * @return the student entity
	 */
	@Maps(withIgnoreMissing = IgnoreMissing.SOURCE)
	StudentEntity updateEntity(StudentResource resource, StudentEntity entity);
}
