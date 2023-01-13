package fr.formation.jpa.springdata;

import fr.formation.jpa.springdata.entities.*;
import fr.formation.jpa.springdata.repositories.*;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryWithH2Test {
	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AdressesRepository adressesRepository;
	@Autowired
	private TypeAdresseRepository typeAdresseRepository;
	
	TypeAdresse typeAdresse1 = new TypeAdresse();
	TypeAdresse typeAdresse2 = new TypeAdresse();
	Country country = new Country();
	Customer customer = new Customer();
	Adresses adresse1 = new Adresses();
	Adresses adresse2 = new Adresses();
	
	@BeforeEach
	public void setup() {
		// initialisation de la base avant chaque test.
		typeAdresse1.setId(1);
		typeAdresse1.setLibelle("Expédition");
		typeAdresseRepository.save(typeAdresse1);
		typeAdresse2.setId(2);
		typeAdresse2.setLibelle("Livraison");
		typeAdresseRepository.save(typeAdresse2);
		
		country.setActif(true);
		country.setLabel("France");
		country.setTva("15.00");
		country.setId("FR");
		countryRepository.save(country);
		
		customer.setLastName("FAUCHERY");
		customer.setFirstName("Benoît");
		customer.setCountry(country);
		customer.setId(1L);
		customerRepository.save(customer);
		
		adresse1.setId(1);
		adresse1.setRue("Bois Levret");
		adresse1.setCodePostal("38550");
		adresse1.setVille("Saint Maurice l'Exil");
		adresse1.setTypeAdresse(typeAdresse1);
		adresse1.setCountry(country);
		adressesRepository.save(adresse1);
		
		adresse2.setId(2);
		adresse2.setRue("Louis Becker");
		adresse2.setCodePostal("69100");
		adresse2.setVille("Villeurbanne");
		adresse2.setTypeAdresse(typeAdresse2);
		adresse2.setCountry(country);
		adressesRepository.save(adresse2);
	}
	
	@Test
	public void shouldInsertOrderIntoDB() {
		final Long ID = 1L;
		final Set<Adresses> adresses = Set.of(adresse1,adresse2);
		final Date dateOrder = Date.valueOf(LocalDate.of(2022,12,25));
		final Date dateDelivery = Date.valueOf(LocalDate.now());
		
		
		// Given
		Order order = new Order();
		order.setId(ID);
		order.setReference("123");
		order.setDateOrder(dateOrder);
		order.setCustomer(customer);
		order.setCountry(country);
		order.setDateDelivery(dateDelivery);
		order.setAdresses(adresses);
		
		// When
		repository.save(order);
		
		// Then
		final Optional<Order> orderFromDataBase = repository.findById(ID);
		Assertions.assertEquals(orderFromDataBase.get().getAdresses(), adresses);
		Assertions.assertEquals(orderFromDataBase.get().getDateOrder(), dateOrder);
		
		//Infos client
		Assertions.assertEquals(orderFromDataBase.get().getCustomer(), customer);
		//Adresse de livraison uniquement
		Set<Adresses> adressesSet = orderFromDataBase.get().getAdresses();
		adressesSet.removeIf(adresse -> !Objects.equals(adresse.getTypeAdresse().getLibelle(), "Livraison"));
		Assertions.assertEquals(adressesSet.size(), 1);
		
	}
}
