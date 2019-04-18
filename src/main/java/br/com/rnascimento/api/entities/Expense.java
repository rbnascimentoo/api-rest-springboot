package br.com.rnascimento.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "expense")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Expense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7604841254740375391L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private BigDecimal amount;
	
	@Column
	private String description;
	
	@JoinColumn(name = "idCategory")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
//	@JoinColumn(name = "idTag")
//	@OneToMany
//	private List<Tags> tags;
	
}