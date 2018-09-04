package testing.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import testing.model.entity.User;
import testing.service.IUserService;

@ManagedBean
@ViewScoped
public class UserView {
	
	@Inject
	private User user;
	
	private List<User> users;
	
	private User selectedUser;
	
	@Inject
	private IUserService userService;
	
	@Produces
	@PostConstruct
	public void init() {
		users = userService.getUsers();
	}
	
	public void add() {
		userService.add(user);
		users.add(user);
		user = new User();
	}
	
	public void update() {
		userService.update(selectedUser);
	}
	
	public void delete() {
		userService.delete(selectedUser.getId());
		users.remove(selectedUser);
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
