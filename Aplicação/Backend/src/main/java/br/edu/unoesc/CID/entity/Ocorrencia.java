package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "ocorrencia")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idOcorrencia;

    @Column(name = "descoco")
    protected String descricaoOcorrencia;

    @Column(name = "datoco")
    protected Date dataOcorrencia;

    @Column(name = "lococo",nullable = false)
    protected String localOcorrencia;
}
