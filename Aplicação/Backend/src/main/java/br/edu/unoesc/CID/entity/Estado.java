package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idest", nullable = false)
    protected long idEstado;

    @Column(name = "sigest", nullable = false)
    protected String siglaEstado;

    @Column(name = "nomest", nullable = false)
    protected String nomeEstado;

    @ManyToOne
    @JoinColumn(name = "idpais")
    private Pais pais;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cidade> cidades = new HashSet<>();

}
