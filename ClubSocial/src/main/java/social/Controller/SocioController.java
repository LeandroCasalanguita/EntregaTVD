package social.Controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.Service.DTO.SocioRequestDTO;
import social.Service.DTO.SocioResponseDTO;
import social.Service.SocioService;

import java.util.List;

@RestController
@RequestMapping("api/socios")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public ResponseEntity<List<SocioResponseDTO>> buscarSocios(
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) Integer edad) {
        List<SocioResponseDTO> socios = socioService.buscarSociosConFiltros(apellido, edad);
        return ResponseEntity.ok(socios); // 200 o
    }

    @PostMapping
    public ResponseEntity<SocioResponseDTO> altaSocio(@RequestBody @Valid SocioRequestDTO request) {
        SocioResponseDTO socio = socioService.altaSocio(request);

        if (socio != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(socio); // 201
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<SocioResponseDTO> buscarPorDni(@PathVariable String dni) {
        SocioResponseDTO socio = socioService.buscarPorDni(dni);
        if (socio != null) {
            return ResponseEntity.ok(socio); // 200 ok
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 nf
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> borrarSocio(@PathVariable String dni) {
        SocioResponseDTO socio = socioService.borrarSocio(dni);
        if (socio != null) {
            return ResponseEntity.noContent().build(); // 204 ncont
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 nf
        }
    }

}
