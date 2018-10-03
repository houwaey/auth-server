package com.authserver.abstracts;

import java.io.Serializable;
import java.util.List;

import com.authserver.interfaces.Operations;

public abstract class AbstractService<T extends Serializable> implements Operations<T> {

	protected abstract Operations<T> getDao();
	
	@Override
	public T findOneById(long id) {
		return getDao().findOneById(id);
	}

	@Override
	public T findOneByKeyVal(String key, String value) {
		return getDao().findOneByKeyVal(key, value);
	}

	@Override
	public List<T> findAllByKeyVal(String key, String value) {
		return getDao().findAllByKeyVal(key, value);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public boolean create(T entity) {
		return getDao().create(entity);
	}

	@Override
	public boolean update(T entity) {
		return getDao().update(entity);
	}
	
	@Override
	public boolean merge(T entity) {
		return getDao().merge(entity);
	}

	@Override
	public boolean delete(T entity) {
		return getDao().delete(entity);
	}

	@Override
	public boolean deleteById(long entityId) {
		return getDao().deleteById(entityId);
	}

	@Override
	public boolean exists(long id) {
		return getDao().exists(id);
	}
	
}