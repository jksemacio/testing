package testing.service;

import java.util.List;

import testing.model.entity.Contact;

public interface IContactService {
	void add(Contact contact, int userId, int countryId);
	List<Contact> getContacts();
	List<Contact> getContacts(int id);
}
