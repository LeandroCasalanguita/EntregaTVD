package social.Service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.Entidad.Socio;
import social.Repository.SocioRepository;
import social.Service.DTO.SocioRequestDTO;
import social.Service.DTO.SocioResponseDTO;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;


    @Transactional
    public List<SocioResponseDTO> buscarSociosConFiltros(String apellido, Integer edad) {

        if(apellido!=null && edad!=null){
            return socioRepository.busSocioPorApellidoEdad(apellido,edad);
        }
        else if(apellido != null){
            return socioRepository.busSocioPorApellido(apellido);
        }
        else if(edad != null){
            return socioRepository.busSocioPorEdad(edad);
        }
        else{
            return socioRepository.todSocios();

        }

    }

    @Transactional
    public SocioResponseDTO altaSocio(@Valid SocioRequestDTO request) {

        if (buscarPorDni(request.getDni()) == null) {
            Socio s = new Socio(request);
            socioRepository.save(s);
            return socioRepository.buscarPorDni(request.getDni());
        }

        return null;
    }

    @Transactional
    public SocioResponseDTO buscarPorDni(String dni) {
        return socioRepository.buscarPorDni(dni);
    }

    @Transactional
    public SocioResponseDTO borrarSocio(String dni) {
        SocioResponseDTO sr = buscarPorDni(dni);
        if(sr!=null){
            socioRepository.borrarSocio(dni);
        }
        return sr;
    }



}
