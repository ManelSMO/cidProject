package br.edu.unoesc.CID.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "funcionario")
public class Funcionario extends Pessoa{

    protected String cargoFuncionario;

    @OneToOne
    protected Pessoa pessoa;
}
