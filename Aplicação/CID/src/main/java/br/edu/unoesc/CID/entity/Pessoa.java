package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpes", nullable = false)
    protected long idPessoa;

    @Column(name = "nompes", nullable = false)
    protected String nomePessoa;

    @Column(name = "estcivpes", nullable = false)
    protected String estadoCivPessoa;

    @Column(name = "datnaspes",nullable = false)
    protected String DatedataNascimento;

    @Column(name = "numtelpes", nullable = false)
    protected String telefonePessoa;

    @Column(name = "emailpes", nullable = false)
    protected String emailPessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_pessoa_id_endereco")
    protected EnderecoPessoa enderecoPessoa;

    @Column(name = "genpes", nullable = false)
    protected String generoPessoa;

    @OneToOne
    protected Usuario usuario;
}
