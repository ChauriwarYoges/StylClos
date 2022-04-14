package com.stylclos.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCart {

	@NotEmpty(message = "provide size of product to add in cart")
	private String size;

	@Min(value = 1)
	private int qty;

	@NotNull
	private int userId;

	@NotNull
	private int proId;

}
