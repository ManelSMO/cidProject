package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    protected int idUsuario;

    @Column(name = "cpfusu", nullable = false, unique = true)
    @NotNull(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    private String cpfPessoa;

    @Column(name = "senusu", nullable = false)
    @NotNull(message = "Senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senhaUsuario;

    @OneToOne
    protected Pessoa pessoa;

    @Enumerated(EnumType.STRING) // Enum para diferenciar tipos de usuários
    private TipoUsuario tipoUsuario;

}

