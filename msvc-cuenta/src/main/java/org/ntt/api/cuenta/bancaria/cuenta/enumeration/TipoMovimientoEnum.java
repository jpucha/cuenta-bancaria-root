/**
 * 
 */
package org.ntt.api.cuenta.bancaria.cuenta.enumeration;

/**
 * 
 * <b> Enumeracion del tipo de movimiento. </b>
 * 
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 20 abr. 2024 $]
 *          </p>
 */
public enum TipoMovimientoEnum {

	CREDITO("CRE", "credito"), DEBITO("DEB", "debito");

	private String codigo;
	private String descripcion;

	private TipoMovimientoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
