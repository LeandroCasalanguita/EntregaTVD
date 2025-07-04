package social.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.Entidad.Boxeo;
import social.Entidad.Socio;
import social.Repository.BoxeoRepository;
import social.Repository.SocioRepository;
import social.Service.DTO.SocioRequestDTO;

@Service
public class BoxeoService {

    @Autowired
    private BoxeoRepository boxeoRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Transactional
    public boolean agregarSocio(SocioRequestDTO s){
        if (s.getEdad() >= 18) {
            Socio socio = new Socio(s);
            socioRepository.save(socio);

            Boxeo boxeo = boxeoRepository.findAll().stream().findFirst().orElse(new Boxeo());

            boxeo.getSocios().add(socio);
            boxeoRepository.save(boxeo);

            return true;
        }

        return false;
    }
}
