package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cidade")
public class Cidade extends Estado {

    protected String nomeCidade;

    @ManyToOne
    protected Bairro bairro;
}
