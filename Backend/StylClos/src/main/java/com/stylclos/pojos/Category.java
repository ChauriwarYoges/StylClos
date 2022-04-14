package com.stylclos.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

	@NotNull
	@Column(length = 10)
	private String category;
	
//	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Type> type = new HashSet<>();
	
//	void addType(Type t) {
//		type.add(t);
//	}
//	
//	void removeType(Type e) {
//		type.remove(e);
//		e.setCat(null);
//	}
	
}
