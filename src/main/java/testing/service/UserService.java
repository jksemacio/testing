package testing.service;

import java.util.List;

import testing.model.entity.User;
import testing.model.dao.UserDAO;;

public class UserService implements IUserService {
	
	private UserDAO UserDAO = new UserDAO();
	
	@Override
	public List<User> getAll() {
		return UserDAO.getAll();
	}

	@Override
	public User getById(User user) {
		return UserDAO.getById(user);
	}

	@Override
	public void add(User user) {
		UserDAO.add(user);
	}

	@Override
	public void update(User user) {
		UserDAO.update(user);

	}

	@Override
	public void delete(User user) {
		UserDAO.delete(user);
	}

}
