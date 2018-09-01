package testing.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import testing.model.dao.UserDAO;
import testing.model.entity.User;

@ManagedBean(name="testing", eager=true)
@SessionScoped
public class TestingController implements Serializable	{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private User user;
	
	@Inject
	private UserDAO userDAO;
	
	public void add() {
		userDAO.add(user);
		user = new User();
	}
	
	public void update() {
		userDAO.update(user);
	}
	
	public void remove() {
		System.out.println(user.getId());
	    userDAO.delete(user.getId());
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
