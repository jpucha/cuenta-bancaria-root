package org.ntt.api.cuenta.bancaria.cuenta;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ntt.api.cuenta.bancaria.cuenta.controller.MovimientoController;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Movimiento;
import org.ntt.api.cuenta.bancaria.cuenta.repository.MovimientoRepository;
import org.ntt.api.cuenta.bancaria.cuenta.service.MovimientoService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovimientoTest {

    @InjectMocks
    MovimientoController movimientoController;

    @Mock
    MovimientoService service;

    @Mock
    MovimientoRepository movimientoRepository;

    @SneakyThrows
    @Test
    void whenObtenerPorNumeroCuentaThenNotNull(){
        int numeroCuenta = 2200220;
        ResponseEntity<?> respuesta = movimientoController.obtenerPorNumeroCuentaPorFecha(numeroCuenta);
        assertNotNull(respuesta);

    }

    @SneakyThrows
    @Test
    void whenObtenerPorNumeroCuentaThenEqualNumeroCuenta(){
        int numeroCuenta = 2200220;
        List<Movimiento> lista = new ArrayList<>();
        lista.add(Movimiento.builder().cuenta(Cuenta.builder().idCuenta(1l).numeroCuenta(numeroCuenta).build()).build());
        when(service.obtenerPorNumeroCuenta(numeroCuenta)).thenReturn(lista);
        ResponseEntity<List<Movimiento>> respuesta = (ResponseEntity<List<Movimiento>>) movimientoController.obtenerPorNumeroCuentaPorFecha(numeroCuenta);
        assertEquals(respuesta.getBody().get(0).getCuenta().getNumeroCuenta(),numeroCuenta);

    }
}
