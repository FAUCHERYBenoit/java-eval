package fr.formation.jpa.springdata.repositories;

import fr.formation.jpa.springdata.entities.Article;
import fr.formation.jpa.springdata.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
