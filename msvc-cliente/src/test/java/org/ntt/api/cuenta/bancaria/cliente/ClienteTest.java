package org.ntt.api.cuenta.bancaria.cliente;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ntt.api.cuenta.bancaria.cliente.controller.ClienteController;
import org.ntt.api.cuenta.bancaria.cliente.model.entity.Cliente;
import org.ntt.api.cuenta.bancaria.cliente.repository.ClienteRepository;
import org.ntt.api.cuenta.bancaria.cliente.service.ClienteService;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    private ClienteService service;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void whenObtenerClientePorIdentificacionThenNotNull(){
        ResponseEntity<?> respuesta = clienteController.obtenerClientePorIdentificacion("1234567890");
        assertNotNull(respuesta);

    }

    @Test
    void whenObtenerClientePorIdentificacionThenEqualsIdentificacion(){
        String identificacion = "1234567890";
        Optional<Cliente> cliente = Optional.of(Cliente.builder().identificacion(identificacion).build());

        when(service.obtenerPorIdentificacion(identificacion)).thenReturn(cliente);
        ResponseEntity<Cliente> respuesta = (ResponseEntity<Cliente>) clienteController.obtenerClientePorIdentificacion(identificacion);
        assertEquals(respuesta.getBody().getIdentificacion(), identificacion);

    }
}
