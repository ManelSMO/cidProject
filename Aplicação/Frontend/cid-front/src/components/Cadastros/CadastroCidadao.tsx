import { useState } from 'react';
import styles from './Cadastro.module.css';

interface CadastroCidadaoProps {
  onAccountCreated: () => void;
  onBackToLogin: () => void;
}

export default function CadastroCidadao({ onAccountCreated, onBackToLogin }: CadastroCidadaoProps) {
  const [nome, setNome] = useState('');
  const [cpf, setCpf] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Lógica de cadastro do cidadão
    onAccountCreated(); // Chamando a função para redirecionar
  };

  return (
    <div className={styles.container}>
      <img src="/image/logo.png" alt="CID Logo" className={styles.logo} />
      <h2 className={styles.title}>Preencha seus dados e receba o e-mail de confirmação.</h2>
      <form onSubmit={handleSubmit} className={styles.form}>
        <label className={styles.formLabel}>
          Nome*
          <input
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            className={styles.input}
            required
          />
        </label>
        <label className={styles.formLabel}>
          CPF*
          <input
            type="text"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
            className={styles.input}
            required
          />
        </label>
        <label className={styles.formLabel}>
          Email*
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className={styles.input}
            required
          />
        </label>
        <label className={styles.formLabel}>
          Senha*
          <input
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            className={styles.input}
            required
          />
        </label>
        <button type="submit" className={styles.submitButton}>Criar conta</button>
      </form>
      <p className={styles.backToLoginText}>
        Já possui uma conta?{' '}
        <a onClick={onBackToLogin} style={{ cursor: 'pointer', color: '#b22222' }}>
          Voltar para o Login
        </a>
      </p>
    </div>
  );
}



