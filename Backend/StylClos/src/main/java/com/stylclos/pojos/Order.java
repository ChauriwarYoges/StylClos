package com.stylclos.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*@Entity
@Table(name = "order")*/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
	
	@Column(name = "order_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	@Column(name = "shipping_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate shippingDate;
	
	@Column(name = "order_status", length = 20)
	private String orderStatus;
	
	@Column(name = "order_total")
	private BigDecimal orderTotal;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cart> cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
