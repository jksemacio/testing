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

	public User getByIdNo(Integer idNo) {
		return entityManager.find(User.class, idNo);
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
	public void delete(Integer idNo) {
		User user = getByIdNo(idNo);
		if(user != null)
		entityManager.remove(user);
	}

	public Integer getIdNo(String email, String password) {
		Query query = entityManager.createNamedQuery("FIND User BY Email AND Password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		User user = (User) query.getSingleResult();
		return user.getIdNo();
	}

	public List<User> getUsers() {
		TypedQuery<User> query = entityManager.createNamedQuery("FIND ALL User", User.class);
		return query.getResultList();
	}
}
