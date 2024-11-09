package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_envolvimento")
public class TipoEnvolvimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idTipoEnvolvimento;

    @Column(name = "tipenv", nullable = false)
    protected String tipoEnvolvimento;

    @Column(name = "desctipenv", nullable = false)
    protected String descTipoEnvolvimento;
}
