package fr.formation.jpa.springdata.repositories;

import fr.formation.jpa.springdata.entities.Customer;
import fr.formation.jpa.springdata.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
