package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ocorrencia")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idOcorrencia;

    @Column(name = "descoco")
    protected String descricaoOcorrencia;

    @Column(name = "datoco")
    protected String dataOcorrencia;

    @Column(name = "lococo",nullable = false)
    protected String localOcorrencia;

    private String cpfCivil; // Campo para associar ao cidadão que registrou

    @ManyToOne
    @JoinColumn(name = "idtipoco")
    private TipoOcorrencia tipoOcorrencia;

    @ManyToOne
    @JoinColumn(name = "idtipvio")
    private TipoViolencia tipoViolencia;

    @ManyToOne
    @JoinColumn(name = "pessoaidpes")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "idcid")
    private Cidade cidade;

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Anexos> anexos = new HashSet<>();

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PessoaEnvolvida> pessoasEnvolvidas = new HashSet<>();

}
