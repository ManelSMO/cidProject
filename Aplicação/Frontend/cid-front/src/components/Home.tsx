// src/components/Home.tsx
import Image from 'next/image';
import styles from './Home.module.css';

export default function Home() {
  return (
    <div className={styles.container}>
      <div className={styles.logo}>
        <Image src="/image/logo.png" alt="CID Logo" width={150} height={100} />
        <h1>Criminal Incident Database</h1>
      </div>
      <p className={styles.titleCustom}>Selecione uma opção:</p> {/* Alterado para titleCustom */}
      <div className={styles.options}>
        <div className={styles.option}>
          <Image
            src="/image/cidadao.png"
            alt="Cidadão"
            className={styles.cidadaoIcon}
            width={212}
            height={224}
          />
          <p>Cidadão</p>
        </div>
        <div className={styles.option}>
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





