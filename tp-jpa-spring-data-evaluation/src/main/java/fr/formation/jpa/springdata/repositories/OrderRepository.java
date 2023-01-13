package fr.formation.jpa.springdata.repositories;

import fr.formation.jpa.springdata.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
