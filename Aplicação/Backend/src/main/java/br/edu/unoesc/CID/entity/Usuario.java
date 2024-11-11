package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusu",nullable = false)
    protected long idUsuario;

    @Column(name = "cpfusu", nullable = false)
    protected String cpfPessoa;

    @Column(name = "senusu", nullable = false)
    protected String senhaUsuario;

}
