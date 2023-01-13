package fr.formation.jpa.springdata.repositories;

import fr.formation.jpa.springdata.entities.Adresses;
import fr.formation.jpa.springdata.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AdressesRepository extends CrudRepository<Adresses, Integer> {
	/**
	 * Recherche les adresses liées à la commande donnée
	 *
	 * @param order la commande
	 * @return
	 */
	@Query(value = "SELECT a FROM Adresses a WHERE a.orders =:order")
	Set<Adresses> findAdressesByOrder(@Param("order") Set<Order> order);
}
