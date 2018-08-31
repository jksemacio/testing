package testing.service;

import java.util.List;
import testing.model.entity.User;

public interface IUserService {
	List<User> getAll();
	User getById(User user);
	void add(User user);
	void update(User user);
	void delete(User user);
}
