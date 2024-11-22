package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoUsuario;
import br.edu.unoesc.CID.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório de Usuario.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link Usuario}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir usuários.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo CPF.
     *
     * @param cpf O CPF do usuário a ser encontrado.
     * @return Um {@link Optional} contendo o usuário encontrado, ou vazio caso o usuário não exista.
     */
    @Query("SELECT u FROM Usuario u WHERE u.cpfPessoa = :cpf")
    Optional<Usuario> findByCPF(@Param("cpf") String cpf);

    /**
     * Busca todos os usuários de um tipo específico (CIVIL ou POLICIAL).
     *
     * @param tipoUsuario O tipo de usuário a ser encontrado (CIVIL ou POLICIAL).
     * @return Uma lista de usuários com o tipo especificado.
     */
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);

    /**
     * Consulta nativa para buscar um usuário pelo CPF.
     *
     * @param cpf O CPF do usuário a ser encontrado.
     * @return Um {@link Optional} contendo o usuário encontrado, ou vazio caso o usuário não exista.
     */
    @Query(value = "SELECT * FROM usuario WHERE cpfusu = :cpf", nativeQuery = true)
    Optional<Usuario> findByCpfNative(@Param("cpf") String cpf);
}
