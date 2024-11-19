package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tipo_ocorrencia")
public class TipoOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idTipoOcorrencia;

    @Column(name = "desctipoco")
    protected String descTipoOcorrencia;

    @OneToMany(mappedBy = "Ocorrencia")
    protected Set<Ocorrencia> ocorrencia;
}
