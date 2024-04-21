package org.ntt.api.cuenta.bancaria.cliente.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 * <b> Clase entidad de la tabla cliente. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *     </p>
 */
@DynamicUpdate
@Entity
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder
    public Cliente(String direccion, int edad, String genero, String identificacion, String nombre,
        String telefono, String contrasena, String estado) {
        super(direccion, edad, genero, identificacion, nombre, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_Id")
    private Long clienteId;

    @NotNull
    private String contrasena;

    private String estado;

	/*@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Cuenta> cuentas;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Movimiento> movimientos;*/


}