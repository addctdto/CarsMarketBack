package bg.carsmarket.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import bg.carsmarket.dao.base.DAOBase;
import bg.carsmarket.model.Manufacturer;

public class MansDAO extends DAOBase<Manufacturer> {

	private EntityManager em;
	
	@Override
	protected List<Manufacturer> getAllEntities() throws NamingException {
		
		em = getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Manufacturer> q = em.createQuery("select m from Manufacturer m", Manufacturer.class);
		List<Manufacturer> mans = q.getResultList();

		em.getTransaction().commit();
		em.close();

		return mans;
	}

	@Override
	protected Manufacturer getEntityById(Long id) throws NamingException {
		
		em = getEntityManager();
		em.getTransaction().begin();

		Manufacturer man = em.find(Manufacturer.class, id);

		em.getTransaction().commit();
		em.close();

		return man;
	}

	@Override
	protected void insertQuery(Manufacturer entity) throws NamingException, SQLException {
		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	protected void updateQuery(Manufacturer newEntity, Long id) throws NamingException, SQLException {
		
		em = getEntityManager();
		em.getTransaction().begin();

		Manufacturer man = em.find(Manufacturer.class, id);
		man.setName(newEntity.getName());
		man.setImgUrl(newEntity.getImgUrl());
		man.setLat(newEntity.getLat());
		man.setLon(newEntity.getLon());

		em.persist(man);
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	protected void deleteQuery(Long id) throws NamingException, SQLException {
		
		em = getEntityManager();
		em.getTransaction().begin();

		Manufacturer man = em.find(Manufacturer.class, id);
		em.remove(man);

		em.getTransaction().commit();
		em.close();
	}

}
