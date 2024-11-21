package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acesso_cidadao")
public class AcessoCidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcessoCidadao;

    @Column(name = "cpfusu", nullable = false, unique = true)
    private String cpfUsuario;

    @Column(name = "senusu", nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "usuarioidusu", nullable = false)
    private Usuario usuario;
}

