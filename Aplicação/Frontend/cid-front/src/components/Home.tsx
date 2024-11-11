// src/components/Home.tsx
import Image from 'next/image';
import styles from './Home.module.css';

// Define as propriedades esperadas pelo componente Home
interface HomeProps {
  onCitizenClick: () => void;
  onPoliceClick: () => void;
}

export default function Home({ onCitizenClick, onPoliceClick }: HomeProps) {
  return (
    <div className={styles.container}>
      <div className={styles.logo}>
        <img src="/image/logo.png" alt="CID Logo" className={styles.logo} />
      </div>
      <p className={styles.titleCustom}>Selecione uma opção:</p>
      <div className={styles.options}>
        <div className={styles.option} onClick={onCitizenClick} style={{ cursor: 'pointer' }}>
          <Image
            src="/image/cidadao.png"
            alt="Cidadão"
            className={styles.cidadaoIcon}
            width={212}
            height={224}
          />
          <p>Cidadão</p>
        </div>
        <div className={styles.option} onClick={onPoliceClick} style={{ cursor: 'pointer' }}>
          <Image
            src="/image/policial.png"
            alt="Policial"
            className={styles.policialIcon}
            width={212}
            height={224}
          />
          <p>Policial</p>
        </div>
      </div>
    </div>
  );
}
