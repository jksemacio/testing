package testing.service;

import testing.model.entity.User;
import testing.model.dao.UserDAO;;

public class UserService implements IUserService {
	
	private UserDAO UserDAO = new UserDAO();

	public User getById(User user) {
		return UserDAO.getById(user);
	}

	public void add(User user) {
		UserDAO.add(user);
	}

	public void update(User user) {
		UserDAO.update(user);

	}

	public void delete(User user) {
		UserDAO.delete(user);
	}

}
