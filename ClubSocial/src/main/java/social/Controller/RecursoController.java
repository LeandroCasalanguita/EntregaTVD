package social.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/recursos")
public class RecursoController {

    @GetMapping
    public ResponseEntity<?> recursos(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionalidad no implementada o recurso no encontrado");
    }


}
