package br.edu.unoesc.CID.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "departamento_policial")
public class DepartamentoPolicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idDepartamento;

    @Column(name = "disdep", nullable = false)
    protected String distritoDepartamento;

    @Column(name = "emaildep", nullable = false)
    protected String emailDepartamento;

    @Column(name = "telatedep", nullable = false)
    protected String telAtendiDepartamento;

    @Column(name = "logrdp", nullable = false)
    protected String logradouroDepartamento;

    @Column(name = "numdp", nullable = false)
    protected String numeroDepartamento;
}
