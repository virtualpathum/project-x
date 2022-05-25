/**
 * Created On : 17 Aug 2017
 */
/*package com.lk.student.manager.web.resource.finder.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.lk.student.manager.entity.StudentEntity;
import com.lk.student.manager.mapper.StudentMapper;
import com.lk.student.manager.repo.StudentRepository;
import StudentResource;
import com.lk.student.manager.web.resource.finder.AbstractResourceFinder;
import com.lk.student.manager.web.resource.finder.StudentResourceFinder;

import java.util.List;

// TODO: Auto-generated Javadoc
*//**
 * The Class StudentResourceFinderImpl.
 * @author virtualpathum
 *//*
@Named("studentResourceFinder")
public class StudentResourceFinderImpl extends AbstractResourceFinder<StudentResource, StudentEntity, StudentRepository, Long> implements StudentResourceFinder {
	*//** The mapper. *//*
	private StudentMapper mapper;
	
	*//**
	 * Instantiates a new student resource finder impl.
	 *
	 * @param repo the repo
	 * @param mapper the mapper
	 *//*
	@Inject
	public StudentResourceFinderImpl(StudentRepository repo, StudentMapper mapper) {
		super(repo);
		this.mapper = mapper;
	}

	*//* (non-Javadoc)
	 * @see com.lk.student.manager.web.resource.finder.AbstractResourceFinder#toResource(java.lang.Object)
	 *//*
	@Override
	protected StudentResource toResource(StudentEntity entity) {
		return mapper.asResource(entity);
	}

	@Override
	public List<StudentResource> findAllStudents() {
		return null;
	}

	@Override
	public StudentResource findById(Long id) {
		return null;
	}
}*/
