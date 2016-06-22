package bg.carsmarket.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import bg.carsmarket.model.Car;
import bg.carsmarket.dao.base.DAOBase;

public class CarsDAO extends DAOBase<Car> {

	private EntityManager em;
	
	public List<Car> getAllCarsFilteredByYear(int maxPrice) throws NamingException {
		em = getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Car> q = em.createQuery("select c from Car c where c.price < :maxPrice", Car.class)
							  .setParameter("maxPrice", maxPrice);

		List<Car> cars = q.getResultList();

		em.getTransaction().commit();
		em.close();

		return cars;
	}
	
	public List<Car> getAllCarsFilteredByPrice(int maxYear) throws NamingException {
		em = getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Car> q = em.createQuery("select c from Car c where c.year < :maxYear", Car.class)
							  .setParameter("maxYear", maxYear);

		List<Car> cars = q.getResultList();

		em.getTransaction().commit();
		em.close();

		return cars;
	}

	@Override
	protected List<Car> getAllEntities() throws NamingException {

		em = getEntityManager();
		em.getTransaction().begin();

		TypedQuery<Car> q = em.createQuery("select c from Car c", Car.class);

		List<Car> cars = q.getResultList();

		em.getTransaction().commit();
		em.close();

		return cars;
	}

	@Override
	protected Car getEntityById(Long id) throws NamingException {

		em = getEntityManager();
		em.getTransaction().begin();

		Car car = em.find(Car.class, id);

		em.getTransaction().commit();
		em.close();

		return car;
	}

	@Override
	protected void insertQuery(Car car) throws NamingException, SQLException {

		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	protected void updateQuery(Car newCar, Long id) throws NamingException, SQLException {

		em = getEntityManager();
		em.getTransaction().begin();

		Car car = em.find(Car.class, id);
		car.setMan(newCar.getMan());
		car.setImgUrl(newCar.getImgUrl());
		car.setVideoUrl(newCar.getVideoUrl());
		car.setModel(newCar.getModel());
		car.setPrice(newCar.getPrice());
		car.setYear(newCar.getYear());
		car.setHp(newCar.getHp());

		em.persist(car);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	protected void deleteQuery(Long id) throws NamingException, SQLException {
		
		em = getEntityManager();
		em.getTransaction().begin();

		Car car = em.find(Car.class, id);
		em.remove(car);

		em.getTransaction().commit();
		em.close();
	}

}
