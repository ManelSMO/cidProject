package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "acesso_policial")
public class AcessoPolicial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcessoPolicial;

    @Column(name = "matfun", nullable = false, unique = true)
    private String matricula;

    @Column(name = "senhafun", nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "usuarioidusu", nullable = false)
    private Usuario usuario;
}

