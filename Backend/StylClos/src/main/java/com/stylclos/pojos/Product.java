package com.stylclos.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
	
	@Column(name = "title")
	@NotNull(message = "provide title of product")
	private String title;
	
	@Column(name = "image")
	@NotNull (message = "provide image path of product")
	private String image;
	
	@JoinColumn(name = "category_id", nullable = false)
	@ManyToOne
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private Type type;
	
	@Column(length = 3)
	@NotNull
	private String size;
	
	@Min(value = 1)
	private int qauntity;
	
	@Min(value = 49)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private User seller;
	
}
