package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcid", nullable = false)
    protected long idCidade;

    @Column(name = "nomcid", nullable = false)
    protected String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "estadoidest") // FK no banco
    private Estado estado;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EnderecoPessoa> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ocorrencia> ocorrencias = new HashSet<>();

    @OneToOne(mappedBy = "cidade")
    private DepartamentoPolicial departamentoPolicial;
}
