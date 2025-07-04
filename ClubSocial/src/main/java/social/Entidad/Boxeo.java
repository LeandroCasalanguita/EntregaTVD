package social.Entidad;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Boxeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int edadMin = 18;

    @OneToMany
    private List<Socio> socios = new ArrayList<>();


}
