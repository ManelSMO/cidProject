package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_violencia")
public class TipoViolencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idTipoViolencia;

    @Column(name = "desctipvio")
    protected String descTipoViolencia;
}
