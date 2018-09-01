package testing.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import testing.model.entity.User;

public class UserDAO implements IUserDAO {

	private String persistenceUnit = "PostgreDS";
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);

	public User getById(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(User.class, user.getId());
	}

	public void add(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void update(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		entityManager.close();
	}


	public void delete(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
