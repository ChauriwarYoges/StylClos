package com.stylclos.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

	@Column(name = "area_name", length = 30)
	@NotNull(message = "please enter area name")
	private String areaName;
	
	@Column(length = 20, nullable = false)
	@NotNull(message = "please enter city")
	private String city;
	
	@Column(length = 20, nullable = false)
	@NotNull(message = "please enter state")
	private String state;
	
	@Column(length = 10, nullable = false)
	@NotNull
	private String country;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Address(@NotBlank(message = "please enter area name") String areaName,
			@NotBlank(message = "please enter city") String city,
			@NotBlank(message = "please enter state") String state, String country) {
		super();
		this.areaName = areaName;
		this.city = city;
		this.state = state;
		this.country = country;
	}
}
