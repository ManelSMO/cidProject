package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "bairro")
public class Bairro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    protected long idBairro;

    @Column(name = "nombai", nullable = false)
    protected String nomeBairro;

    @OneToMany(mappedBy = "bairro", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<EnderecoPessoa> enderecoPessoa = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cidadeidcid", nullable = false)
    protected Cidade cidade;

    @OneToOne(mappedBy = "bairro")
    protected DepartamentoPolicial departamentoPolicial;
}
