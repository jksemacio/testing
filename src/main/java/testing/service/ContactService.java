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
	public void add(Contact contact, Integer userIdNo, Integer countryIdNo) {
		User owner = userService.getByIdNo(userIdNo);
		Country country = countryService.getByIdNo(countryIdNo);
		contact.setOwner(owner);
		contact.setCountry(country);
		entityManager.persist(contact);
	}
	
	public Contact getByIdNo(Integer idNo) {
		return entityManager.find(Contact.class, idNo);
	}

	public List<Contact> getContacts() {
		TypedQuery<Contact> query = entityManager.createNamedQuery("FIND ALL Contact", Contact.class);
		return query.getResultList();
	}
	
	public List<Contact> getContacts(Integer userIdNo) {
		TypedQuery<User> query = entityManager.createNamedQuery("GET Contacts", User.class);
		query.setParameter("idNo", userIdNo);
		return query.getSingleResult().getContacts();
	}

	@Transactional
	public void delete(Integer idNo) {
		Contact contact = getByIdNo(idNo);
		entityManager.remove(contact);
	}

	@Transactional
	public void update(Contact contact) {
		entityManager.merge(contact);
	}
	
	public List<Contact> getContactsByOwner(Integer userIdNo) {
		TypedQuery<Contact> query = entityManager.createQuery("SELECT c.name, c.number, d.country FROM Contact c, Country d WHERE c.userIdNo = :userIdNo", Contact.class);
		query.setParameter("userIdNo", userIdNo);
		return query.getResultList();
	}
}
