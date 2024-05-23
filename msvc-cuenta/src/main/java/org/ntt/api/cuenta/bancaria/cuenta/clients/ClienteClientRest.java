package org.ntt.api.cuenta.bancaria.cuenta.clients;

import org.ntt.api.cuenta.bancaria.cliente.controller.dto.salida.BaseResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente", url = "${msvc.cliente.url}")
public interface ClienteClientRest {

    @GetMapping("/{identificacion}")
    ResponseEntity<BaseResponseDto> obtenerClientePorIdentificacion(@PathVariable String identificacion);


}
