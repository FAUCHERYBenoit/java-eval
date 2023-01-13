package fr.formation.jpa.springdata;

import fr.formation.jpa.springdata.entities.TypeAdresse;
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
public class TypeAdresseRepositoryWithH2Test {
	@Autowired
	private TypeAdresseRepository repository;
	
	
	//@Autowired
	// Injecter un autre DAO si besoin
	
	@BeforeEach
	public void setup() {
		// initialisation de la base avant chaque test.
		
	}
	
	@Test
	public void shouldInsertTypeAdresseIntoDB() {
		final Integer ID = 1;
		final String label = "Avenue";
		
		// Given
		TypeAdresse typeAdresse = new TypeAdresse();
		typeAdresse.setId(ID);
		typeAdresse.setLibelle(label);
		
		// When
		repository.save(typeAdresse);
		
		// Then
		final Optional<TypeAdresse> typeAdresseFromDataBase = repository.findById(ID);
		Assertions.assertEquals(typeAdresseFromDataBase.get().getLibelle(), label);
		Assertions.assertEquals(typeAdresseFromDataBase.get().getId(), ID);
	}
}
