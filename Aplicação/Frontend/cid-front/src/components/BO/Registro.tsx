import { useState } from 'react';
import styles from './Registro.module.css';
import { useRouter } from 'next/router';
import jsPDF from 'jspdf';

interface RegistroProps {
  onHomeClick: () => void;
}

const RegistrarOcorrencia: React.FC<RegistroProps> = ({ onHomeClick }) => {
  const [tipoOcorrencia, setTipoOcorrencia] = useState('');
  const [houveViolencia, setHouveViolencia] = useState(false);
  const [tipoViolencia, setTipoViolencia] = useState(''); // Novo estado
  const [dataOcorrencia, setDataOcorrencia] = useState('');
  const [horaOcorrencia, setHoraOcorrencia] = useState('');
  const [cidade, setCidade] = useState('');
  const [tipoLocal, setTipoLocal] = useState('');
  const [nomeEnvolvido, setNomeEnvolvido] = useState('');
  const [descricaoEnvolvido, setDescricaoEnvolvido] = useState('');
  const [cpfEnvolvido, setCpfEnvolvido] = useState('');
  const [nascimentoEnvolvido, setNascimentoEnvolvido] = useState('');
  const [telefone, setTelefone] = useState("");
  const [email, setEmail] = useState("");
  const [descricaoOcorrencia, setDescricaoOcorrencia] = useState('');
  const [anexo, setAnexo] = useState<File | null>(null);

  const router = useRouter();

  const handleParticipationChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setParticipacao(event.target.value); // Atualiza diretamente o valor selecionado
  };

  const [participacao, setParticipacao] = useState("");

  const handleSalvarEnvolvido = () => {
    console.log('Dados do envolvido salvos:', {
      nomeEnvolvido,
      descricaoEnvolvido,
      cpfEnvolvido,
      nascimentoEnvolvido,
      telefone,
      email,
      participacao,
    });

    setNomeEnvolvido('');
    setDescricaoEnvolvido('');
    setCpfEnvolvido('');
    setNascimentoEnvolvido('');
    setTelefone("");
    setEmail("");
    setParticipacao("");
  };

  const gerarPDF = () => {
    const doc = new jsPDF();

    doc.setFont('helvetica', 'bold');
    doc.text('Boletim de Ocorrência', 10, 10);
    doc.setFont('helvetica', 'normal');

    doc.text(`Tipo de Ocorrência: ${tipoOcorrencia}`, 10, 20);
    doc.text(`Houve Violência: ${houveViolencia ? 'Sim' : 'Não'}`, 10, 30);
    if (houveViolencia) {
      doc.text(`Tipo de Violência: ${tipoViolencia}`, 10, 40);
    }
    doc.text(`Data: ${dataOcorrencia} Hora: ${horaOcorrencia}`, 10, 50);
    doc.text(`Cidade: ${cidade}`, 10, 60);
    doc.text(`Tipo de Local: ${tipoLocal}`, 10, 70);
    doc.text(`Descrição da Ocorrência: ${descricaoOcorrencia}`, 10, 80);

    if (nomeEnvolvido) {
      doc.text('Envolvidos:', 10, 100);
      doc.text(`- Nome: ${nomeEnvolvido}`, 10, 110);
      doc.text(`- CPF: ${cpfEnvolvido}`, 10, 120);
      doc.text(`- Data de Nascimento: ${nascimentoEnvolvido}`, 10, 130);
      doc.text(`- Telefone: ${telefone}`, 10, 140);
      doc.text(`- Email: ${email}`, 10, 150);
      doc.text(`- Participação: ${participacao}`, 10, 160);
    }

    doc.save('Boletim_de_Ocorrencia.pdf');
  };

  const [showModal, setShowModal] = useState(false);

  const handleRegistrarOcorrencia = (e: React.FormEvent) => {
    e.preventDefault();
    // Mensagem de confirmação
    setShowModal(true);
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
      {/* Modal de confirmação */}
      {showModal && (
        <div className={styles.modalOverlay}>
          <div className={styles.modal}>
            <h2>Sucesso!</h2>
            <p>Ocorrência registrada com sucesso! O PDF foi gerado.</p>
            <button
              onClick={() => {
                setShowModal(false); // Fecha o modal
                gerarPDF();
                onHomeClick(); // Redireciona para o menu
              }}
              className={styles.button}
            >
              OK
            </button>
          </div>
        </div>
      )}
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
              <option value="acidente">Acidente de Trânsito</option>
              <option value="ameaca">Ameaça</option>
              <option value="estelionato">Estelionato</option>
              <option value="danopat">Dano ao Patrimônio</option>
              <option value="violenciadom">Violência Doméstica</option>
              <option value="desaparecimento">Desaparecimento de Pessoa</option>
              <option value="possedrogas">Posse de Drogas</option>
              <option value="trafico">Tráfico de Drogas</option>
              <option value="agressao">Agressão Física</option>
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
                onChange={() => {
                  setHouveViolencia(false);
                  setTipoViolencia(''); // Limpa o tipo de violência se "Não" for selecionado
                }}
              />
              NÃO
            </label>
          </div>
          {houveViolencia && (
            <label>
              Tipo de Violência:*
              <select
                value={tipoViolencia}
                onChange={(e) => setTipoViolencia(e.target.value)}
                required
                className={styles.field}
              >
                <option value="">Selecione</option>
                <option value="fisica">Física</option>
                <option value="psicologica">Psicológica</option>
                <option value="sexual">Sexual</option>
                <option value="outros">Outros</option>
              </select>
            </label>
          )}
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
          <div className={styles.participation}>
            <span className={styles.participationTitle}>Participação do envolvido*</span>
            <label>
              <input
                type="radio"
                name="participacao" // Agrupa as opções
                value="Comunicante"
                checked={participacao === "Comunicante"}
                onChange={(e) => setParticipacao(e.target.value)}
                className={styles.radio}
              />
              Comunicante
            </label>
            <label>
              <input
                type="radio"
                name="participacao" // Agrupa as opções
                value="Vítima"
                checked={participacao === "Vítima"}
                onChange={(e) => setParticipacao(e.target.value)}
                className={styles.radio}
              />
              Vítima
            </label>
            <label>
              <input
                type="radio"
                name="participacao" // Agrupa as opções
                value="Testemunha"
                checked={participacao === "Testemunha"}
                onChange={(e) => setParticipacao(e.target.value)}
                className={styles.radio}
              />
              Testemunha
            </label>
            <label>
              <input
                type="radio"
                name="participacao" // Agrupa as opções
                value="Suspeito"
                checked={participacao === "Suspeito"}
                onChange={(e) => setParticipacao(e.target.value)}
                className={styles.radio}
              />
              Suspeito
            </label>
          </div>
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
        </div>
        <div className={styles.section}>
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
          <button type="button" onClick={handleSalvarEnvolvido} className={styles.button}>
            Salvar Envolvido
          </button>
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
          <button type="submit" className={styles.button}>
            Registrar
          </button>
        </div>
      </form>
    </div>
  );
};

export default RegistrarOcorrencia;

