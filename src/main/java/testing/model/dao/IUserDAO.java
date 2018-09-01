package testing.model.dao;

import testing.model.entity.User;

public interface IUserDAO {
	User getById(int id);
	void add(User user);
	void update(User user);
	void delete(int id);
}
