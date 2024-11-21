package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoUsuario;
import br.edu.unoesc.CID.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.cpfPessoa = :cpf")
    Optional<Usuario> findByCPF(@Param("cpf") String cpf);

    // Busca usuários por tipo (CIVIL ou POLICIAL)
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);

    // Consulta nativa de exemplo
    @Query(value = "SELECT * FROM usuario WHERE cpfusu = :cpf", nativeQuery = true)
    Optional<Usuario> findByCpfNative(@Param("cpf") String cpf);
}
