package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idPais;

    @Column(name = "nompais", nullable = false)
    protected String nomePais;

    @OneToMany(mappedBy = "Estado")
    protected Set<Estado> estado;
}
