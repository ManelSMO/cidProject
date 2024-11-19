package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "funcionario")
public class Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idFuncionario;

    @Column(name = "carfun",nullable = false)
    protected String cargoFuncionario;

    @OneToOne(mappedBy = "funcionario")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "iddep")
    private DepartamentoPolicial departamentoPolicial;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ocorrencia> ocorrencias = new HashSet<>();
}
