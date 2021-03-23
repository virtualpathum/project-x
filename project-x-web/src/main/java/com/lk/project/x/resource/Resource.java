/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.resource;

// TODO: Auto-generated Javadoc
/**
 * The Interface Resource.
 * @author virtualpathum
 * @param <T> the generic type
 */
public interface Resource<T> {
	
	/**
	 * Gets the resource id.
	 *
	 * @return the resource id
	 */
	T getId();
	
	/**
	 * Sets the resource id.
	 *
	 * @param id the new resource id
	 */
	void setId(T id);
	
}
