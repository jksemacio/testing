package testing.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import testing.model.entity.User;

public class UserDAO implements IUserDAO {

	@PersistenceContext
	EntityManager entityManager;

	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	public void add(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}


	public void delete(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}
}
