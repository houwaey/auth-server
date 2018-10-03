package com.authserver.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.authserver.interfaces.Operations;

public abstract class AbstractHibernateDao<T extends Serializable> extends AbstractDao<T> implements Operations<T>{
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractHibernateDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public T findOneById(long id) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		T newClazz = null;
		try {
			newClazz = session.get(clazz, id);
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		} finally {
			session.close();
		}
		return (T) newClazz;
	}

	@Override
	public T findOneByKeyVal(String key, String value) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		T newClazz = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> cQuery = builder.createQuery(clazz);
			Root<T> root = cQuery.from(clazz);
			cQuery.select(root)
					.where(
							builder.equal(root.get(key), value)
							);
			Query<T> q = session.createQuery(cQuery);
			newClazz = q.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		} finally {
			session.close();
		}
		return (T) newClazz;
	}

	@Override
	public List<T> findAllByKeyVal(String key, String value) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<T> list = new ArrayList<T>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> cQuery = builder.createQuery(clazz);
			Root<T> root = cQuery.from(clazz);
			cQuery.select(root)
				.where(
						builder.equal(root.get(key), value));
			Query<T> q = session.createQuery(cQuery);
			list = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<T> findAll() {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<T> list = new ArrayList<T>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
			Root<T> root = criteriaQuery.from(clazz);
			criteriaQuery.select(root);
			Query<T> query = session.createQuery(criteriaQuery);
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public boolean create(T entity) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		if (entity != null) {
			try {
				session.persist(entity);
				tx.commit();
				return true;
			} catch (Exception e) {
				System.out.println(e);
				logger.error(e.getMessage());
				tx.rollback();
				return false;
			} finally {
				session.close();
			}
		}
		return false;
	}
	
	@Override
	public boolean update(T entity) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		if (entity != null) {
			try {
				session.update(entity);
				tx.commit();
				return true;
			} catch (Exception e) {
				logger.error(e.getMessage());
				tx.rollback();
				return false;
			} finally {
				session.close();
			}
		}
		return false;
	}
	
	@Override
	public boolean merge(T entity) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		if (entity != null) {
			try {
				session.merge(entity);
				tx.commit();
				return true;
			} catch (Exception e) {
				logger.error(e.getMessage());
				tx.rollback();
			} finally {
				session.close();
			}
		}
		return false;
	}

	@Override
	public boolean delete(T entity) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		if (entity != null) {
			try {
				session.delete(entity);
				tx.commit();
				return true;
			} catch (Exception e) {
				logger.error(e.getMessage());
				tx.rollback();
				return false;
			} finally {
				session.close();
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteById(long entityId) {
		Session session = getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaDelete<T> cDelete = builder.createCriteriaDelete(clazz);
			Root<T> root = cDelete.from(clazz);
			cDelete.where(builder.equal(root.get("id"), entityId));
			session.createQuery(cDelete).executeUpdate();
			tx.commit();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	@Override
	public boolean exists(long id) {
		return findOneById(id) != null ? true : false;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
}