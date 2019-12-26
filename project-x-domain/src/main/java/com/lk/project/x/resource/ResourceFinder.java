/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.resource;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Interface ResourceFinder.
 * @author virtualpathum
 * @param <R> the generic type
 * @param <ID> the generic type
 */
public interface ResourceFinder <R extends AbstractResource<? extends Serializable>, ID extends Serializable> {
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the r
	 */
	R findOne(ID id);
}
