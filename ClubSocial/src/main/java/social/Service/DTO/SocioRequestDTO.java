package social.Service.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties( ignoreUnknown = true)
@AllArgsConstructor
public class SocioRequestDTO {

    @NotBlank( message = "El DNI es un campo obligatorio.")
    @Pattern(regexp = "\\d+", message = "El DNI solo debe contener números sin puntos.")
    private String dni;

    @NotBlank( message = "El apellido es un campo obligatorio.")
    private String apellido;

    @NotBlank( message = "El nombre es un campo obligatorio.")
    private String nombre;

    @NotNull(message = "La edad es un campo obligatorio.")
    @Min(value = 0, message = "La edad no puede ser menor a 0.")
    @Max(value = 99, message = "La edad no puede ser mayor a 99.")
    private Integer edad;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fechaNacimiento es obligatoria.")
    private LocalDate fechaNacimiento;

    @NotBlank( message = "La direccion es un campo obligatorio.")
    private String direccion;

    @NotBlank( message = "El telefono es un campo obligatorio.")
    private String telefono;

}
