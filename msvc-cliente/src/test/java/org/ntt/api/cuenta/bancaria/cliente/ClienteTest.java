package org.ntt.api.cuenta.bancaria.cliente;

import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ntt.api.cuenta.bancaria.cliente.exception.ClienteException;
import org.ntt.api.cuenta.bancaria.cliente.model.entity.Cliente;
import org.ntt.api.cuenta.bancaria.cliente.repository.ClienteRepository;
import org.ntt.api.cuenta.bancaria.cliente.service.ClienteService;
import org.ntt.api.cuenta.bancaria.cliente.service.impl.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void whenObtenerClientePorIdentificacionThenNotNull() throws ClienteException {
        String identificacionEntrada = "1720693500";
        Cliente clienteSalidaEsperada = Cliente.builder().nombre("Jose Lema ")
            .direccion("Otavalo sn y principal ")
            .telefono("098254785").contrasena("1234").estado("True").build();
        Optional<Cliente> actualRespuesta;
        try {
            when(this.clienteRepository.obtenerPorIdentificacion(identificacionEntrada)).thenReturn(
                Optional.ofNullable(clienteSalidaEsperada));
            actualRespuesta = this.clienteService.obtenerPorIdentificacion(identificacionEntrada);
        } catch (ClienteException e) {
            throw new ClienteException(e);
        }
        Assert.assertEquals(Optional.ofNullable(clienteSalidaEsperada), actualRespuesta);
    }

    @Ignore
    void whenObtenerClientePorIdentificacionThenEqualsIdentificacion() throws ClienteException {
        String identificacion = "1234567890";
        Optional<Cliente> cliente = Optional.of(
            Cliente.builder().identificacion(identificacion).build());

        //when(service.obtenerPorIdentificacion(identificacion)).thenReturn(cliente);
        //ResponseEntity<Cliente> respuesta = (ResponseEntity<Cliente>) clienteController.obtenerClientePorIdentificacion(
        //identificacion);
        //assertEquals(respuesta.getBody().getIdentificacion(), identificacion);

    }
}
