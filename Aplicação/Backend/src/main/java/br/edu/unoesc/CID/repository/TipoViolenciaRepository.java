package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoViolencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoViolenciaRepository extends JpaRepository<TipoViolencia, String> {
    
}
