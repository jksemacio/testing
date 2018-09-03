package testing.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import testing.model.entity.User;
import testing.service.IUserService;

@ManagedBean
@RequestScoped
public class UserView {
	
	@Inject
	private User user;
	
	private List<User> users;
	
	private User selectedUser;
	
	@Inject
	private IUserService userService;
	
	@PostConstruct
	public void init() {
		users = userService.getUsers();
	}
	
	public void add() {
		userService.add(user);
		users.add(user);
	}
	
    public List<User> getUsers() {
        return users;
    }

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
