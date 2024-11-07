package br.edu.unoesc.CID.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "estado")
public class Estado extends Pais{

    protected String siglaEstado;
    protected String nomeEstado;

    @ManyToOne
    protected Cidade cidade;

}
