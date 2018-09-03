package testing.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import testing.model.entity.User;

public class UserDAO implements IUserDAO {

	@PersistenceContext()
	EntityManager entityManager;

	public User getById(int id) {
		return entityManager.find(User.class, id);
	}
	
	@Transactional
	public void add(User user) {
		entityManager.persist(user);
	}

	@Transactional
	public void update(User user) {
		entityManager.merge(user);
	}

	@Transactional
	public void delete(int id) {
		User user = getById(id);
		if(user != null)
		entityManager.remove(user);
	}
}
