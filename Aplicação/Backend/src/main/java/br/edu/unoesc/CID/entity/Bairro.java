package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bairro")
public class Bairro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbai", nullable = false)
    protected long idBairro;

    @Column(name = "nombai", nullable = false)
    protected String nomeBairro;

    @ManyToOne
    protected EnderecoPessoa enderecoPessoa;

}
