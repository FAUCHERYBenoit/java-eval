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
@Table(name = "type_adresse")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TypeAdresse {
	@Id
	@Column(name = "id_type_adresse")
	private Integer id;
	
	@Column(name = "libelle")
	private String libelle;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		TypeAdresse typeAdresse = (TypeAdresse) o;
		return id != null && Objects.equals(id, typeAdresse.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
