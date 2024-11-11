package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCPF(String cpf);
}
