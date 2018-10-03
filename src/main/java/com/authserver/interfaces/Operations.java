package com.authserver.interfaces;

import java.io.Serializable;
import java.util.List;

public interface Operations<T extends Serializable> {

	T findOneById(final long id);
	
	T findOneByKeyVal(final String key, final String value);
	
	List<T> findAllByKeyVal(final String key, final String value);
	
	List<T> findAll();
	
	boolean create(final T entity);

	boolean update(final T entity);
	
	boolean merge(final T entity);
	
	boolean delete(final T entity);
	
	boolean deleteById(final long entityId);
	
	boolean exists(final long id);
	
}