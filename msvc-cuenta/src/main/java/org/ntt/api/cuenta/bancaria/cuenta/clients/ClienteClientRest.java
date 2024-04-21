package org.ntt.api.cuenta.bancaria.cuenta.clients;

import java.util.Optional;
import org.ntt.api.cuenta.bancaria.cuenta.model.ClienteModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-cliente", url = "${msvc.cliente.url}")
public interface ClienteClientRest {

    @GetMapping("/{identificacion}")
    Optional<ClienteModel> obtenerClientePorIdentificacion(@PathVariable String identificacion);

    @PostMapping("/")
    ClienteModel crear(@RequestBody ClienteModel usuarioModel);

    /**
     * Obtiene los datos del cliente dado el cuentaId.
     * @param ids
     * @return
     *
     @GetMapping("usuarios-por-curso") List<ClienteModel> obtenerAlumnosPorCurso(@RequestParam Long cuentaId);*/
}
