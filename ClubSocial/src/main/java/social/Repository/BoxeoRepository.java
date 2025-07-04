package social.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.Entidad.Boxeo;


@Repository
public interface BoxeoRepository extends JpaRepository<Boxeo, Long> {


}
