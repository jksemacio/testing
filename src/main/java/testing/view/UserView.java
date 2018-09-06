package testing.view;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.xml.sax.SAXException;

import testing.model.entity.Contact;
import testing.model.entity.Country;
import testing.model.entity.User;
import testing.service.IContactService;
import testing.service.ICountryService;
import testing.service.IUserService;
import testing.service.ReportService;

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
	
	@Inject
	private ReportService reportService;

	@PostConstruct
	public void init() throws SQLException {
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

	public void addContact() {
		contactService.add(contact, selectedUser.getId(), country.getId());
		contacts.add(contact);
		contact = new Contact();
	}

	public void deleteContact() {
		contactService.delete(contact.getId());
		contacts.remove(contact);
	}

	public void updateContact() {
		contactService.update(contact);
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

	public void addContactButton() {
		if (selectedUser != null) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('contact').show();");
		}
	}
	
	public void addButton() {
		user = new User();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('add').show();");
	}
	
	public void addCountryButton() {
		country = new Country();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addCountry').show();");
	}

	public StreamedContent getFile() throws IOException {
		InputStream stream = reportService.getReport(selectedUser, contacts);
		return new DefaultStreamedContent(stream, "xls", "userContacts.xls"); 
	}
	
	public StreamedContent getReportXML() throws IOException, JAXBException, ParserConfigurationException, TransformerException {
		InputStream stream = reportService.getReportXML(selectedUser, contacts);
		return new DefaultStreamedContent(stream, "xml", "userContacts.xml"); 
	}
	
	public StreamedContent getReportPDF() throws ConfigurationException, SAXException, IOException, TransformerException, ParserConfigurationException {
		InputStream stream = reportService.getReportPDF(selectedUser, contacts);
		return new DefaultStreamedContent(stream, "pdf", "userContacts.pdf");
	}
}
