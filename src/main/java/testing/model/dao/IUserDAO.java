package testing.model.dao;

import testing.model.entity.User;

public interface IUserDAO {
	User getById(User user);
	void add(User user);
	void update(User user);
	void delete(User user);
}
