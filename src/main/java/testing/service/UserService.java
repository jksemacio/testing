package testing.service;

import testing.model.entity.User;

import java.util.List;
import javax.inject.Inject;

import testing.model.dao.IUserDAO;


public class UserService implements IUserService {
	
	@Inject
	private IUserDAO userDAO;
	

	public User getByIdNo(Integer idNo) {
		return userDAO.getByIdNo(idNo);
	}

	public void add(User user) {
		userDAO.add(user);
	}

	public void update(User user) {
		userDAO.update(user);

	}

	public void delete(Integer idNo) {
		userDAO.delete(idNo);
	}

	public int getIdNo(String email, String password) {
		return userDAO.getIdNo(email, password);
	}
	
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

}
