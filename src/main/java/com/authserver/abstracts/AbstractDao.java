package com.authserver.abstracts;

import java.io.Serializable;

import com.authserver.interfaces.Operations;
import com.google.common.base.Preconditions;

public abstract class AbstractDao<T extends Serializable> implements Operations<T> {

	protected Class<T> clazz;
	
	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = Preconditions.checkNotNull(clazzToSet);
	}
	
}