package testing.model.dao;

import java.util.List;

import testing.model.entity.User;

public interface IUserDAO {
	User getByIdNo(Integer idNo);
	void add(User user);
	void update(User user);
	void delete(Integer idNo);
	Integer getIdNo(String email, String password);
	List<User> getUsers();
}
