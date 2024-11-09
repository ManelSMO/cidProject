package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcid", nullable = false)
    protected long idCidade;

    @Column(name = "nomcid", nullable = false)
    protected String nomeCidade;

    @ManyToOne
    protected Bairro bairro;
}
