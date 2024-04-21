package org.ntt.api.cuenta.bancaria.cuenta.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * <b> Clase Dto para la entrada de la cuenta. </b>
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
public class CuentaResponseDto {

    private Long idCuenta;
    private String identificacion;
    private String tipoCuenta;
    private double saldoInicial;
    private String estado;
    private Long idCliente;
    private int numeroCuenta;

}
