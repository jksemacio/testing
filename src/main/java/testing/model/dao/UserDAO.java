package testing.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import testing.model.entity.User;

public class UserDAO implements IUserDAO {

	@PersistenceContext()
	EntityManager entityManager;

	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	public void add(User user) {
		entityManager.persist(user);
	}

	public void update(User user) {
		entityManager.merge(user);
	}


	public void delete(int id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}
}
