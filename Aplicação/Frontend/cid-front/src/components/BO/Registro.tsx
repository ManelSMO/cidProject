import { useState } from 'react';
import styles from './Registro.module.css';
import { useRouter } from 'next/router';

interface RegistroProps {
    onHomeClick: () => void;
  }

  const RegistrarOcorrencia: React.FC<RegistroProps> = ({ onHomeClick }) => {

  // Estados para os campos do formulário
  const [tipoOcorrencia, setTipoOcorrencia] = useState('');
  const [houveViolencia, setHouveViolencia] = useState(false);
  const [dataOcorrencia, setDataOcorrencia] = useState('');
  const [horaOcorrencia, setHoraOcorrencia] = useState('');
  const [cidade, setCidade] = useState('');
  const [tipoLocal, setTipoLocal] = useState('');
  const [nomeEnvolvido, setNomeEnvolvido] = useState('');
  const [descricaoEnvolvido, setDescricaoEnvolvido] = useState('');
  const [cpfEnvolvido, setCpfEnvolvido] = useState('');
  const [nascimentoEnvolvido, setNascimentoEnvolvido] = useState('');
  const [telefone, setTelefone] = useState('');
  const [email, setEmail] = useState('');
  const [descricaoOcorrencia, setDescricaoOcorrencia] = useState('');
  const [anexo, setAnexo] = useState<File | null>(null);

  // Função para salvar os dados do envolvido
  const handleSalvarEnvolvido = () => {
    console.log('Dados do envolvido salvos:', {
      nomeEnvolvido,
      descricaoEnvolvido,
      cpfEnvolvido,
      nascimentoEnvolvido,
    });

    // Limpa os campos após salvar
    setNomeEnvolvido('');
    setDescricaoEnvolvido('');
    setCpfEnvolvido('');
    setNascimentoEnvolvido('');
  };

  // Função para registrar a ocorrência
  const handleRegistrarOcorrencia = (e: React.FormEvent) => {

    const router = useRouter();
    
    e.preventDefault();
    console.log('Ocorrência registrada:', {
      tipoOcorrencia,
      houveViolencia,
      dataOcorrencia,
      horaOcorrencia,
      cidade,
      tipoLocal,
      descricaoOcorrencia,
      telefone,
      email,
      anexo,
    });
    router.push('/confirmacao'); // Redireciona para a tela de confirmação
  };


  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <img src="/image/logo2.png" alt="CID Logo" className={styles.logo} />
        <img
        src="/image/home-icon.png"
        alt="Home"
        className={styles.homeIcon}
        onClick={onHomeClick}
        />
      </div>
      <form className={styles.form} onSubmit={handleRegistrarOcorrencia}>
        <div className={styles.section}>
          <label>
            Selecione o Tipo de Ocorrência:*
            <select
              value={tipoOcorrencia}
              onChange={(e) => setTipoOcorrencia(e.target.value)}
              required
              className={styles.field}
            >
              <option value="">Selecione</option>
              <option value="roubo">Roubo</option>
              <option value="furto">Furto</option>
              <option value="agressao">Agressão</option>
            </select>
          </label>
          <div className={styles.radioGroup}>
            <span>Houve Violência?*</span>
            <label>
              <input
                type="radio"
                value="sim"
                checked={houveViolencia}
                onChange={() => setHouveViolencia(true)}
              />
              SIM
            </label>
            <label>
              <input
                type="radio"
                value="nao"
                checked={!houveViolencia}
                onChange={() => setHouveViolencia(false)}
              />
              NÃO
            </label>
          </div>
          <label>
            Data e Hora da Ocorrência:*
            <div className={styles.dateTimeGroup}>
              <input
                type="date"
                value={dataOcorrencia}
                onChange={(e) => setDataOcorrencia(e.target.value)}
                required
                className={styles.field}
              />
              <input
                type="time"
                value={horaOcorrencia}
                onChange={(e) => setHoraOcorrencia(e.target.value)}
                required
                className={styles.field}
              />
            </div>
          </label>
          <label>
            Local da Ocorrência:
            <input
              type="text"
              placeholder="Cidade"
              value={cidade}
              onChange={(e) => setCidade(e.target.value)}
              required
              className={styles.field}
            />
            <input
              type="text"
              placeholder="Tipo de Local"
              value={tipoLocal}
              onChange={(e) => setTipoLocal(e.target.value)}
              required
              className={styles.field}
            />
          </label>
        </div>
        <div className={styles.section}>
          <h3>Inserir um novo envolvido</h3>
          <label>
            Nome Completo:
            <input
              type="text"
              value={nomeEnvolvido}
              onChange={(e) => setNomeEnvolvido(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <label>
            Descrição:
            <input
              type="text"
              value={descricaoEnvolvido}
              onChange={(e) => setDescricaoEnvolvido(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <label>
            CPF:
            <input
              type="text"
              value={cpfEnvolvido}
              onChange={(e) => setCpfEnvolvido(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <label>
            Data de Nascimento:
            <input
              type="date"
              value={nascimentoEnvolvido}
              onChange={(e) => setNascimentoEnvolvido(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <button type="button" onClick={handleSalvarEnvolvido} className={styles.button}>
            Salvar Envolvido
          </button>
        </div>
        <div className={styles.section}>
          <label>
            Telefone:
            <input
              type="text"
              value={telefone}
              onChange={(e) => setTelefone(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <label>
            E-mail:
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <label>
            Descrição da Ocorrência:
            <textarea
              value={descricaoOcorrencia}
              onChange={(e) => setDescricaoOcorrencia(e.target.value)}
              required
              className={styles.field}
            />
          </label>
          <label>
            Anexar Arquivos:
            <input
              type="file"
              onChange={(e) => setAnexo(e.target.files?.[0] || null)}
              className={styles.field}
            />
          </label>
        </div>
        <button type="submit" className={styles.submitButton}>
          Registrar
        </button>
      </form>
    </div>
  );
};

export default RegistrarOcorrencia;

