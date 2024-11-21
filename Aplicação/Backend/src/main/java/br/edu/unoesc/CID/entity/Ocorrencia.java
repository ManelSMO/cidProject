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
    protected Long idOcorrencia;

    @Column(name = "descoco")
    protected String descricaoOcorrencia;

    @Column(name = "datoco")
    protected String dataOcorrencia;

    @Column(name = "lococo",nullable = false)
    protected String localOcorrencia;

    @Column(name = "staoco", nullable = false)
    private String status;

    @Column(name = "validaoco", nullable = false)
    private Boolean validada;

    private String cpfCivil; // Campo para associar ao cidadão que registrou

    @ManyToOne
    @JoinColumn(name = "idtipoco", nullable = false)
    private TipoOcorrencia tipoOcorrencia;

    @ManyToOne
    @JoinColumn(name = "idtipvio",  nullable = false)
    private TipoViolencia tipoViolencia;

    @ManyToOne
    @JoinColumn(name = "funcionariopessoaidpes", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "cidadeidcid", nullable = false)
    private Cidade cidade;

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Anexos> anexos = new HashSet<>();

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PessoaEnvolvida> pessoasEnvolvidas;

}
