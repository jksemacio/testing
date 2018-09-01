package testing.service;

import testing.model.entity.User;

public interface IUserService {
	User getById(int id);
	void add(User user);
	void update(User user);
	void delete(int id);
}
