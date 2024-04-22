package org.ntt.api.cuenta.bancaria.cuenta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ntt.api.cuenta.bancaria.cuenta.controller.CuentaController;
import org.ntt.api.cuenta.bancaria.cuenta.model.ClienteModel;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.repository.CuentaRepository;
import org.ntt.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CuentaTest {
    @InjectMocks
    CuentaController cuentaController;

    @Mock
    private CuentaService service;

    @Mock
    private CuentaRepository cuentaRepository;

    @Test
    void whenObtenerCuentaPorClienteThenNotNull(){
        String identificacion = "1234567890";
        ResponseEntity<?> respuesta = cuentaController.obtenerCuentaPorCliente(identificacion);
        assertNotNull(respuesta);

    }

    @Test
    void whenObtenerCuentaPorClienteThenEqualsIdCliente(){
        String identificacion = "1234567890";
        Optional<ClienteModel> cliente = Optional.of(ClienteModel.builder().identificacion(identificacion).clienteId(1l).build());
        when(service.obtenerClientePorIdentificacion(identificacion)).thenReturn(cliente);

        List<Cuenta> cuentaList = new ArrayList<>();
        cuentaList.add(Cuenta.builder().idCliente(cliente.get().getClienteId()).idCuenta(1l).numeroCuenta(22002200).build());

        when(service.obtenerPorCliente(cliente.get().getClienteId())).thenReturn(cuentaList);

        ResponseEntity<List<Cuenta>> respuesta = (ResponseEntity<List<Cuenta>>) cuentaController.obtenerCuentaPorCliente(identificacion);
        assertEquals(respuesta.getBody().get(0).getIdCliente(), cliente.get().getClienteId());

    }
}
