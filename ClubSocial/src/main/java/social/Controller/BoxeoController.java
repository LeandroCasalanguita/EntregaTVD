package social.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.Service.BoxeoService;
import social.Service.DTO.SocioRequestDTO;

@RestController
@RequestMapping("api/boxeo")
public class BoxeoController {

    @Autowired
    private BoxeoService boxeoService;

    @PostMapping("/inscribir")
    public ResponseEntity<?> inscribirSocio(@RequestBody @Valid SocioRequestDTO request) {
        boolean agregado = boxeoService.agregarSocio(request);
        if (agregado) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El socio no cumple con los requisitos de edad.");
        }
    }
}
