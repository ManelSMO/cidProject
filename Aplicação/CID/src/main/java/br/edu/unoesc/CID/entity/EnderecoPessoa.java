package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "endereco_pessoa")
public class EnderecoPessoa extends Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idEndereco;

    protected String logradouroPessoa;
    protected String numEnderecoPessoa;
    protected String complementoPessoa;

    @ManyToOne
    protected Cidade cidade;

}
