package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "tipoOcorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();
}
