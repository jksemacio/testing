package testing.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

	public int getId(String email, String password) {
		Query query = entityManager.createNamedQuery("FIND User BY Email AND Password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		User user = (User) query.getSingleResult();
		return user.getId();
	}

	public List<User> getUsers() {
		TypedQuery<User> query = entityManager.createNamedQuery("FIND ALL User", User.class);
		return query.getResultList();
	}
}
