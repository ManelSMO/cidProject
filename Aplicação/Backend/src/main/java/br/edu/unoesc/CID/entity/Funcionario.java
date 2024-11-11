package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToOne
    protected Pessoa pessoa;
}
