package social.Repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import social.Entidad.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import social.Service.DTO.SocioResponseDTO;

import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer>{

    @Query("SELECT new social.Service.DTO.SocioResponseDTO" +
            "(s.dni,s.apellido,s.nombre,s.edad,s.fechaNacimiento,s.direccion,s.telefono) " +
            "FROM Socio s WHERE LOWER(s.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    List<SocioResponseDTO> busSocioPorApellido(@Param("apellido") String apellido);

    @Query ("SELECT new social.Service.DTO.SocioResponseDTO" +
            "(s.dni,s.apellido,s.nombre,s.edad,s.fechaNacimiento,s.direccion,s.telefono) " +
            "FROM Socio s WHERE s.edad=:edad")
    List<SocioResponseDTO> busSocioPorEdad(@Param("edad")Integer edad);


    @Query ("SELECT new social.Service.DTO.SocioResponseDTO" +
            "(s.dni,s.apellido,s.nombre,s.edad,s.fechaNacimiento,s.direccion,s.telefono) " +
            "FROM Socio s WHERE s.edad = :edad AND LOWER(s.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    List<SocioResponseDTO> busSocioPorApellidoEdad(@Param("apellido") String apellido, @Param("edad") Integer edad);

    @Query ("SELECT new social.Service.DTO.SocioResponseDTO" +
            "(s.dni,s.apellido,s.nombre,s.edad,s.fechaNacimiento,s.direccion,s.telefono) " +
            "FROM Socio s")
    List<SocioResponseDTO> todSocios();

    @Query ("SELECT new social.Service.DTO.SocioResponseDTO" +
            "(s.dni,s.apellido,s.nombre,s.edad,s.fechaNacimiento,s.direccion,s.telefono) " +
            "FROM Socio s WHERE s.dni=:dni")
    SocioResponseDTO buscarPorDni(@Param("dni") String dni);

    @Query("DELETE FROM Socio s WHERE s.dni = :dni")
    void borrarSocio(@Param("dni") String dni);
}
