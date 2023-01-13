package fr.formation.jpa.springdata.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "adresses")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Adresses {
	@Id
	@Column(name = "id_adresse")
	private Integer id;
	
	@Column(name = "rue")
	private String rue;
	
	@Column(name = "code_postal")
	private String codePostal;
	
	@Column(name = "ville")
	private String ville;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_type_adresse", nullable = false)
	private TypeAdresse typeAdresse;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_pays", nullable = false)
	private Country country;
	
	@ManyToMany
	@JoinTable(name = "commande_adresses",
		joinColumns= @JoinColumn(name = "id_adresse" ),
		inverseJoinColumns= @JoinColumn( name = "id_commande"))
	private Set<Order> orders;
	
	@ManyToMany
	@JoinTable(name = "adresses_client",
			joinColumns= @JoinColumn(name = "id_adresse" ),
			inverseJoinColumns= @JoinColumn( name = "id_client"))
	private Set<Customer> customers;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Adresses adresses = (Adresses) o;
		return id != null && Objects.equals(id, adresses.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
