package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bairro")
public class Bairro extends Cidade{
    @Column()
    protected String nomeBairro;

    @ManyToOne
    protected EnderecoPessoa enderecoPessoa;
}
