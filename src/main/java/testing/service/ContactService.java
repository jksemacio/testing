package testing.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import testing.model.entity.Contact;
import testing.model.entity.Country;
import testing.model.entity.User;

public class ContactService implements IContactService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Inject
	IUserService userService;
	
	@Inject
	ICountryService countryService;
	
	@Transactional
	public void add(Contact contact, int userId, int countryId) {
		User owner = userService.getById(userId);
		Country country = countryService.getById(countryId);
		contact.setOwner(owner);
		contact.setCountry(country);
		entityManager.persist(contact);
	}

	public List<Contact> getContacts() {
		TypedQuery<Contact> query = entityManager.createNamedQuery("FIND ALL Contact", Contact.class);
		return query.getResultList();
	}
	
	public List<Contact> getContacts(int userId) {
		User owner = userService.getById(userId);
		owner.getContacts().size();
		return owner.getContacts();
	}
}
