package fr.formation.jpa.springdata;

import fr.formation.jpa.springdata.entities.Adresses;
import fr.formation.jpa.springdata.entities.Country;
import fr.formation.jpa.springdata.entities.Customer;
import fr.formation.jpa.springdata.entities.TypeAdresse;
import fr.formation.jpa.springdata.repositories.AdressesRepository;
import fr.formation.jpa.springdata.repositories.CountryRepository;
import fr.formation.jpa.springdata.repositories.CustomerRepository;
import fr.formation.jpa.springdata.repositories.TypeAdresseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryWithH2Test {
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private AdressesRepository adressesRepository;
	@Autowired
	private TypeAdresseRepository typeAdresseRepository;
	
	TypeAdresse typeAdresse = new TypeAdresse();
	Country country = new Country();
	Adresses adresse1 = new Adresses();
	Adresses adresse2 = new Adresses();
	
	@BeforeEach
	public void setup() {
		// initialisation de la base avant chaque test.
		typeAdresse.setId(1);
		typeAdresse.setLibelle("Avenue");
		typeAdresseRepository.save(typeAdresse);
		
		country.setActif(true);
		country.setLabel("France");
		country.setTva("15.00");
		country.setId("FR");
		countryRepository.save(country);
		
		adresse1.setId(1);
		adresse1.setRue("Bois Levret");
		adresse1.setCodePostal("38550");
		adresse1.setVille("Saint Maurice l'Exil");
		adresse1.setTypeAdresse(typeAdresse);
		adresse1.setCountry(country);
		adressesRepository.save(adresse1);
		
		adresse2.setId(2);
		adresse2.setRue("Louis Becker");
		adresse2.setCodePostal("69100");
		adresse2.setVille("Villeurbanne");
		adresse2.setTypeAdresse(typeAdresse);
		adresse2.setCountry(country);
		adressesRepository.save(adresse2);
	}
	
	@Test
	public void shouldInsertCustomerFromDB() {
		final Long ID = 1L;
		final String lastName = "FAUCHERY";
		final String firstName = "Benoît";
		final Set<Adresses> adresses = Set.of(adresse1,adresse2);
		
		// Given
		Customer customer = new Customer();
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		customer.setCountry(country);
		customer.setAdresses(adresses);
		customer.setId(ID);
		
		// When
		repository.save(customer);
		
		// Then
		final Optional<Customer> customerFromDataBase = repository.findById(ID);
		Assertions.assertEquals(customerFromDataBase.get().getLastName(), lastName);
		Assertions.assertEquals(customerFromDataBase.get().getFirstName(), firstName);
		Assertions.assertEquals(customerFromDataBase.get().getAdresses(), adresses);
	}
}
