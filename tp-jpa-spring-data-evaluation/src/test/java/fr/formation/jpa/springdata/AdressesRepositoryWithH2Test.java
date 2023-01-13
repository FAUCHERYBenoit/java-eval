package fr.formation.jpa.springdata;

import fr.formation.jpa.springdata.entities.Adresses;
import fr.formation.jpa.springdata.entities.Country;
import fr.formation.jpa.springdata.entities.TypeAdresse;
import fr.formation.jpa.springdata.repositories.AdressesRepository;
import fr.formation.jpa.springdata.repositories.CountryRepository;
import fr.formation.jpa.springdata.repositories.TypeAdresseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AdressesRepositoryWithH2Test {
	@Autowired
	private AdressesRepository repository;
	@Autowired
	private TypeAdresseRepository typeAdresseRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	TypeAdresse typeAdresse = new TypeAdresse();
	Country country = new Country();
	
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
	}
	
	@Test
	public void shouldInsertAdresseIntoDB() {
		final Integer ID = 1;
		final String rue = "Bois Levret";
		
		// Given
		Adresses adresses = new Adresses();
		adresses.setId(ID);
		adresses.setRue(rue);
		adresses.setCodePostal("38550");
		adresses.setVille("Saint Maurice l'Exil");
		adresses.setTypeAdresse(typeAdresse);
		adresses.setCountry(country);
		
		// When
		repository.save(adresses);
		
		// Then
		final Optional<Adresses> adresseFromDataBase = repository.findById(ID);
		Assertions.assertEquals(adresseFromDataBase.get().getRue(), rue);
		Assertions.assertEquals(adresseFromDataBase.get().getCountry(), country);
		Assertions.assertEquals(adresseFromDataBase.get().getTypeAdresse(), typeAdresse);
		Assertions.assertEquals(adresseFromDataBase.get().getId(), ID);
	}
}
