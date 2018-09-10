package testing.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import testing.model.dao.IUserDAO;
import testing.model.entity.User;

@ManagedBean(name="testing", eager=true)
@RequestScoped
public class TestingController implements Serializable	{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private User user;
	
	@Inject
	private IUserDAO userDAO;
	
	public void search() {
		user = userDAO.getByIdNo(user.getIdNo());
	}
	
	public void add() {
		userDAO.add(user);
	}
	
	public void update() {
		userDAO.update(user);
	}
	
	public void remove() {
	    userDAO.delete(user.getIdNo());
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
