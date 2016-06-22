package bg.carsmarket.dao.base;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOBase<T> {

	public List<T> getAll() throws NamingException {
		List<T> entities = getAllEntities();
		return entities;
	}

	public T getById(Long id) throws NamingException {
		T entity = getEntityById(id);
		return entity;
	}

	public void insert(T entity) throws NamingException, SQLException {
		insertQuery(entity);
	}
	
	public void update(T newEntity, Long id) throws NamingException, SQLException {
		updateQuery(newEntity, id);
	}
	
	public void delete(Long id) throws NamingException, SQLException {
		deleteQuery(id);
	}
	
	protected EntityManager getEntityManager() throws NamingException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarsMarketBack");
		return emf.createEntityManager();
	}
	
	protected abstract List<T> getAllEntities() throws NamingException;
	protected abstract T getEntityById(Long id) throws NamingException;
	protected abstract void insertQuery(T entity) throws NamingException, SQLException;
	protected abstract void updateQuery(T newEntity, Long id) throws NamingException, SQLException;
	protected abstract void deleteQuery(Long id) throws NamingException, SQLException;
}
