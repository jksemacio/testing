package testing.service;

import testing.model.entity.User;

import java.util.List;
import javax.inject.Inject;

import testing.model.dao.IUserDAO;


public class UserService implements IUserService {
	
	@Inject
	private IUserDAO userDAO;
	

	public User getById(int id) {
		return userDAO.getById(id);
	}

	public void add(User user) {
		userDAO.add(user);
	}

	public void update(User user) {
		userDAO.update(user);

	}

	public void delete(int id) {
		userDAO.delete(id);
	}

	public int getId(String email, String password) {
		return userDAO.getId(email, password);
	}
	
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

}
