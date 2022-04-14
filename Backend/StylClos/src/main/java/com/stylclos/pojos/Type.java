package com.stylclos.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
	TSHIRTS, CASUALS, FORMAL, JACKETS, BLAZZERS, SUITS, JEANS, SHORTS, TRACK,
	KURTAS, SKIRTS, LEHENGA, LEGGINS, SALWAR, TOPS, SAREES
 */

@Entity
@Table(name = "type")
@Getter
@Setter
@ToString
public class Type extends BaseEntity {

	@NotEmpty(message = "provide type name")
	@Column(name = "type_name")
	private String typeName;

	@NotEmpty(message = "provide image")
	@NotNull
	private String image;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category cat;
	
//	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
////@LazyCollection(LazyCollectionOption.TRUE)
//	private Set<Product> products = new HashSet<>();
	
//	void addProduct(Product p) {
//		products.add(p);
//	}
//	
//	void removeProduct(Product p) {
//		products.remove(p);
//		p.setType(null);
//	}

}
