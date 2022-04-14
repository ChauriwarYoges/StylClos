package com.stylclos.pojos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
	
	@NotNull(message = "provide your email")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "Provide your name")
	private String name;
	
	@Column(name = "register_date")
	@NotNull(message = "please provide register date")
	private LocalDate registerDate;
	
	@NotNull(message = "Provide password to login")
	@JsonIgnore
	private String password;
	
	@ElementCollection
	@CollectionTable(
				name = "rolestab",
				joinColumns = @JoinColumn(name = "id")
			)
	@Column(name = "role", length = 10)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<String> role = new HashSet<>();
	
	@JsonIgnore
	private transient String confirmPassword;

	
	
}
