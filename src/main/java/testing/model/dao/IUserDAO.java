package testing.model.dao;

import java.util.List;

import testing.model.entity.User;

public interface IUserDAO {
	User getById(int id);
	void add(User user);
	void update(User user);
	void delete(int id);
	int getId(String email, String password);
	List<User> getUsers();
}
