package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipo_envolvimento")
public class TipoEnvolvimento {

    @Id
    @Column(name = "tipenv", nullable = false)
    protected String tipoEnvolvimento;

    @Column(name = "desctipenv", nullable = false)
    protected String descTipoEnvolvimento;

    @OneToMany(mappedBy = "tipoEnvolvimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaEnvolvida> pessoasEnvolvidas = new ArrayList<>();

}
