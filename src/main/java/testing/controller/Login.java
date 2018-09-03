package testing.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import testing.model.entity.User;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private User user;
	

	
	public Login() {
	}
	
	public void getPermission() {
		FacesContext faceContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) faceContext.getExternalContext().getSession(true);
		Object id = session.getAttribute("id");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if(id == null) {
			try {
				context.redirect("login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String validate() {
	
		if(user.getEmail().equals("admin") && user.getPassword().equals("password")) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.setAttribute("id", "0");
			return "welcome.xhtml?faces-redirect=true";
		} else {
			return "index.xhtml";
		}
	}
	
	public String logout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		return "index.xhtml?faces-redirect=true";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	

}
