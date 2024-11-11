package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "anexo")
public class Anexos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idAnexo;

    @Column(name = "camane", nullable = false)
    protected String camane;

    @Column(name = "tipoanexo",nullable = false)
    protected String tipoAnexo;

    @Column(name = "descanexo")
    protected String descricaoAnexo;

    @Column(name = "datanexo", nullable = false)
    protected Timestamp dataAnexo;
}
