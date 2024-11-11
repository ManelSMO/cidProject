import { useState } from 'react';
import Image from 'next/image';
import styles from './Login.module.css';

interface LoginPolicialProps {
  onLoginSuccess: () => void;
}

export default function LoginPolicial({ onLoginSuccess }: LoginPolicialProps) {
  const [matricula, setMatricula] = useState('');
  const [senha, setSenha] = useState('');
  const [manterConectado, setManterConectado] = useState(false);

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    onLoginSuccess();
  };

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <Image src="/image/policial.png" alt="Policial" width={40} height={40} />
      </div>
      <div className={styles.logo}>
        <Image src="/image/logo.png" alt="CID Logo" width={200} height={100} />
      </div>
      <h2 className={styles.title}>Por favor informe seus dados de acesso.</h2>
      <form className={styles.form} onSubmit={handleLogin}>
        <label className={styles.formLabel}>
          <input
            type="text"
            placeholder="Matrícula"
            className={styles.inputField}
            value={matricula}
            onChange={(e) => setMatricula(e.target.value)}
            required
          />
        </label>
        <label className={styles.formLabel}>
          <input
            type="password"
            placeholder="Senha"
            className={styles.inputField}
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            required
          />
        </label>
        <div className={styles.options}>
          <a href="#">Esqueci minha senha</a>
          <label className={styles.checkbox}>
            <input
              type="checkbox"
              checked={manterConectado}
              onChange={(e) => setManterConectado(e.target.checked)}
            />
            Manter-me conectado
          </label>
        </div>
        <button type="submit" className={styles.submitButton}>Entrar</button>
      </form>
      <div className={styles.criarConta}>
        Não possui conta? <a href="#">Criar conta</a>
      </div>
    </div>
  );
}
