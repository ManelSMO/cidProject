import { useState } from 'react';
import styles from './Consultar.module.css';

const Consultar = ({ onHomeClick, perfil }) => { // Adicionando a prop `perfil`
  const [cpf, setCpf] = useState("");
  const [dadosBO, setDadosBO] = useState([]);

  const handleBuscar = async () => {
    if (!cpf) {
      alert("Por favor, insira um CPF.");
      return;
    }

    try {
      // Substitua a URL pela URL da sua API
      const response = await fetch(`/api/bo?cpf=${cpf}`);
      if (response.ok) {
        const data = await response.json();
        setDadosBO(data); // Atualiza o estado com os dados retornados
      } else {
        alert("Nenhum registro encontrado para este CPF.");
        setDadosBO([]);
      }
    } catch (error) {
      console.error("Erro ao buscar BO:", error);
      alert("Ocorreu um erro ao buscar os dados.");
    }
  };

  const handleEditar = (boId) => {
    // Implementar a lógica de edição/confirmar aqui
    console.log(`Editar BO ${boId}`);
    alert(`Função de edição para BO ${boId} ainda não implementada.`);
  };

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <img src="/image/logo2.png" alt="CID Logo" className={styles.logo} />
        <img
          src="/image/home-icon.png"
          alt="Home"
          className={styles.homeIcon}
          onClick={onHomeClick} // Chamando a função passada como prop
        />
      </div>

      <div className={styles.search}>
        <label htmlFor="cpf">CPF:</label>
        <input
          type="text"
          id="cpf"
          value={cpf}
          onChange={(e) => setCpf(e.target.value)}
          className={styles.input}
        />
        <button onClick={handleBuscar} className={styles.button}>
          Buscar
        </button>
      </div>

      <div className={styles.resultados}>
        {dadosBO.length === 0 ? (
          <p className={styles.mensagem}>Nenhum registro encontrado.</p>
        ) : (
          dadosBO.map((bo, index) => (
            <div key={index} className={styles.card}>
              <h3>BO #{bo.id}</h3>
              <p><strong>Tipo:</strong> {bo.tipoOcorrencia}</p>
              <p><strong>Data:</strong> {bo.dataOcorrencia}</p>
              <p><strong>Local:</strong> {bo.localOcorrencia}</p>
              <p><strong>Descrição:</strong> {bo.descricao}</p>

              {/* Botão visível apenas para policiais */}
              {perfil === "policial" && (
                <button
                  className={styles.editarButton}
                  onClick={() => handleEditar(bo.id)}
                >
                  Editar/Confirmar
                </button>
              )}
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Consultar;


      


