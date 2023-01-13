package fr.formation.jpa.springdata.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "articles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Article {
	@Id
	@Column(name = "id_article")
	private Integer id;
	
	@Column(name = "libelle")
	private String label;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "prix_HT")
	private float amount;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Article country = (Article) o;
		return id != null && Objects.equals(id, country.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
