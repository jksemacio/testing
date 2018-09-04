package testing.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import testing.model.entity.Contact;
import testing.model.entity.Country;
import testing.model.entity.User;
import testing.service.IContactService;
import testing.service.ICountryService;
import testing.service.IUserService;

@ManagedBean
@ViewScoped
public class UserView {
	
	@Inject
	private User user;
	
	@Inject
	private Contact contact;
	
	@Inject
	private Country country;
	
	private List<User> users;
	
	private List<Contact> contacts;
	
	private List<Country> countries;
	
	private User selectedUser;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IContactService contactService;
	
	@Inject
	private ICountryService countryService;
	
	@PostConstruct
	public void init() {
		users = userService.getUsers();
		countries = countryService.getCountries();
		initContacts();
	}
	
	public void initContacts() {
		contacts = new ArrayList<Contact>();
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
	
	public void addcontact() {
		contactService.add(contact, selectedUser.getId(), country.getId());
		contacts.add(contact);
		contact = new Contact();
	}
	
	public void addcountry() {
		countryService.add(country);
		countries.add(country);
		country = new Country();
	}
	
	public void initUserContacts() {
		contacts = contactService.getContacts(selectedUser.getId());
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	
	
}
