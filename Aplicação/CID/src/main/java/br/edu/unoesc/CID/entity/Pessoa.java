package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pessoa")
public class Pessoa extends Usuario {


    protected String nomePessoa;
    protected String estadoCivPessoa;
    protected String DatedataNascimento;
    protected String telefonePessoa;
    protected String emailPessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_pessoa_id_endereco")
    protected EnderecoPessoa enderecoPessoa;

    protected String generoPessoa;

    @OneToOne
    protected Usuario usuario;
}
