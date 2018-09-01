package testing.service;

import testing.model.entity.User;

public interface IUserService {
	User getById(User user);
	void add(User user);
	void update(User user);
	void delete(User user);
}
