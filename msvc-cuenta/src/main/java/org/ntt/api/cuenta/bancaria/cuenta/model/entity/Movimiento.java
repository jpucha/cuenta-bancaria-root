package org.ntt.api.cuenta.bancaria.cuenta.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * <b> Clase entidad de la tabla movimiento. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *          </p>
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMovimiento;

	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private BigDecimal saldo;

	@NotBlank
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;

	@NotBlank
	private BigDecimal valor;

	@Column(name = "saldo_anterior")
	private BigDecimal saldoAnterior;

	@NotBlank
	@Column(name = "id_cuenta")
	private Long idCuenta;

	@Transient
	private List<Cuenta> cuentaList;

	/* bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente", insertable = false, updatable = false)
	@JsonIgnore
	private Cliente cliente;

	@Column
	private Long idCliente;*/

	// bi-directional many-to-one association to Cuenta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta", insertable = false, updatable = false)
	@JsonIgnore
	private Cuenta cuenta;



}