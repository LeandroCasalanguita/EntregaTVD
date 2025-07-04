package social.Service.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SocioResponseDTO {

    private String dni;

    private String apellido;

    private String nombre;

    private Integer edad;

    private LocalDate fechaNacimiento;

    private String direccion;

    private String telefono;

}
