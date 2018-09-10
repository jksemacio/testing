package testing.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import testing.model.entity.Country;

public class CountryService implements ICountryService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void add(Country country) {
		entityManager.persist(country);
	}
	
	public Country getByIdNo(Integer idNo) {
		return entityManager.find(Country.class, idNo);
	}

	public List<Country> getCountries() {
		TypedQuery<Country> query = entityManager.createNamedQuery("FIND ALL Country", Country.class);
		return query.getResultList();
	}
}
