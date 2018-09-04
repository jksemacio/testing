package testing.service;

import java.util.List;

import testing.model.entity.Country;

public interface ICountryService {
	void add(Country country);
	Country getById(int id);
	List<Country> getCountries();
}
