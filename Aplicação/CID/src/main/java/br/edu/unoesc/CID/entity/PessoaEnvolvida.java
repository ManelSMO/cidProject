package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pessoa_envolvida")
public class PessoaEnvolvida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idPessoaEnvolvida;

    @Column(name = "nompesenv")
    protected String nomePessoaEnvolvida;

    @Column(name = "descpesenv")
    protected String descPessoaEnvolvida;

    @Column(name = "cpfenv")
    protected String cpfPessoaEnvolvida;

    @Column(name = "telenv")
    protected String telefonePessoaEnvolvida;

    @Column(name = "datnasenv")
    protected String dataNascPessoaEnvolvida;
}
