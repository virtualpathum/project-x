/**
 * Created On : 11 Aug 2017
 */
package com.lk.project.x.web.resource.finder;

import java.util.List;

import javax.inject.Inject;

import com.lk.project.x.entity.StudentEntity;
import com.lk.project.x.repo.StudentRepository;
import com.lk.project.x.resource.StudentResource;
import org.modelmapper.ModelMapper;


/**
 * The Class BookingResourceFinderImpl.
 * @author virtualpathum
 */
//@Named("studentResourceFinder")
public class StudentResourceFinderImpl extends AbstractResourceFinder<StudentResource, StudentEntity, StudentRepository, Long> implements StudentResourceFinder {
	/** The mapper. */
	private ModelMapper mapper;
	
	@Inject
	public StudentResourceFinderImpl(StudentRepository repo, ModelMapper mapper) {
		super(repo);
		this.mapper = mapper;
	}

	@Override
	protected StudentResource toResource(StudentEntity entity) {
		
		return mapper.map(entity, StudentResource.class);
	}

	@Override
	public StudentResource findById(Long id) {

		StudentEntity entity = repo.findById(id).orElse(null);
		return mapper.map(entity, StudentResource.class);
		
	}

	@Override
	public List<StudentResource> findAllStudents() {
		List<StudentEntity> list = repo.findAll();
		return toResources(list);

	}
	
}
