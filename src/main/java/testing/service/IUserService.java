package testing.service;

import java.util.List;

import testing.model.entity.User;

public interface IUserService {
	User getByIdNo(Integer idNo);
	void add(User user);
	void update(User user);
	void delete(Integer idNo);
	int getIdNo(String email, String password);
	List<User> getUsers();
}
