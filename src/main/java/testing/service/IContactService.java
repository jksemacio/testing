package testing.service;

import java.util.List;

import testing.model.entity.Contact;

public interface IContactService {
	void add(Contact contact, Integer userIdNo, Integer countryIdNo);
	List<Contact> getContacts();
	List<Contact> getContacts(Integer idNo);
	void delete(Integer idNo);
	void update(Contact contact);
}
