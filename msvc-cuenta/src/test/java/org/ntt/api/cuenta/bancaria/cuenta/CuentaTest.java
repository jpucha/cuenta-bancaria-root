package org.ntt.api.cuenta.bancaria.cuenta;

import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ntt.api.cuenta.bancaria.cuenta.clients.ClienteClientRest;
import org.ntt.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.ntt.api.cuenta.bancaria.cuenta.repository.CuentaRepository;
import org.ntt.api.cuenta.bancaria.cuenta.service.impl.CuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class CuentaTest {

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Mock
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Test
    void whenObtenerCuentaPorNumeroCunetaThenNotNull() {
        int datosEntrada = 456789;

        Cuenta cuentaEsperada = Cuenta.builder().numeroCuenta(456789).idCuenta(1L).idCliente(1L).
            tipoCuenta("Ahorros").saldoInicial(BigDecimal.valueOf(1000L)).estado("True").build();

        Optional<Cuenta> respuestaActual;
        when(this.cuentaRepository.findByNumeroCuenta(datosEntrada)).thenReturn(
            Optional.ofNullable(cuentaEsperada));
        respuestaActual = this.cuentaService.obtenerPorNumeroCuenta(datosEntrada);
        Assert.assertNotNull(respuestaActual);

    }
}
