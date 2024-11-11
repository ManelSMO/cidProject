import styles from './Menu.module.css';
import { useRouter } from 'next/router';

interface MenuProps {
  onRegisterClick: () => void;
  onConsultClick: () => void;
}

export default function Menu({ onRegisterClick, onConsultClick }: MenuProps) {
  return (
    <div className={styles.container}>
      <img src="/image/logo.png" alt="CID Logo" className={styles.logo} />
      <div className={styles.buttonsContainer}>
        <button onClick={onRegisterClick} className={styles.button}>
          <strong>Registrar</strong><br />Boletim de Ocorrência
        </button>
        <button onClick={onConsultClick} className={styles.button}>
          <strong>Consultar</strong><br />Meus boletins de ocorrências
        </button>
      </div>
    </div>
  );
}

