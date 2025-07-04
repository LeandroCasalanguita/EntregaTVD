package social.Entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import social.Service.DTO.SocioRequestDTO;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter


public class Socio {

    @Id
    private String dni;

    private String apellido;

    private String nombre;

    private Integer edad;

    private LocalDate fechaNacimiento;

    private String direccion;

    private String telefono;

    public Socio(SocioRequestDTO r){
        this.dni = r.getDni();
        this.apellido = r.getApellido();
        this.nombre = r.getNombre();
        this.edad = r.getEdad();
        this.fechaNacimiento = r.getFechaNacimiento();
        this.direccion = r.getDireccion();
        this.telefono = r.getTelefono();
    }
}
