/**
 *
 */
package org.ntt.api.cuenta.bancaria.cliente.service;

import java.util.List;
import java.util.Optional;
import org.ntt.api.cuenta.bancaria.cliente.controller.dto.entrada.ClienteEntradaDto;
import org.ntt.api.cuenta.bancaria.cliente.exception.ClienteException;
import org.ntt.api.cuenta.bancaria.cliente.model.entity.Cliente;

/**
 *
 * <b> Interfaz del servicio para el cliente. </b>
 *
 * @author Jenny Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Pucha $, $Date: 19 abr. 2024 $]
 *          </p>
 */
public interface ClienteService {

    /**
     * <b> Metodo que crea un cliente. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param clienteEntradaDto parametro de entrada
     * @return Cliente objeto o mensaje de error
     * @throws ClienteException signals that an CdcApiException has occurred.
     */
    Cliente create(ClienteEntradaDto clienteEntradaDto) throws ClienteException;

    /**
     * <b> Metodo para obtiene todos los clientes </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @return List<Cliente> lista o mensaje de error
     */
    List<Cliente> read();

    /**
     * <b> Metodo que actualiza un cliente. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param clienteEntradaDto parametro de entrada
     * @return Cliente objeto o mensaje de error
     * @throws ClienteException signals that an CdcApiException has occurred.
     */
    Cliente update(ClienteEntradaDto clienteEntradaDto) throws ClienteException;

    /**
     * <b> Metodo que elimina un registro por su id. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @throws ClienteException signals that an CdcApiException has occurred.
     */
    void delete(String identificacion) throws ClienteException;

    /**
     * <b> Metodo para obtiene un cliente por su identificacion. </b>
     * <p>
     * [Author: Jenny Pucha, Date: 19 abr. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return Optional<Cliente> objeto o mensaje de error
     */
    Optional<Cliente> obtenerPorIdentificacion(String identificacion) throws ClienteException;

}
