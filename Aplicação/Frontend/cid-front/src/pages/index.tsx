// src/pages/index.tsx
import { useState } from 'react';
import Home from '../components/Home';
import LoginCidadao from '../components/Login/LoginCidadao';
import LoginPolicial from '../components/Login/LoginPolicial';
import CadastroCidadao from '../components/Cadastros/CadastroCidadao';
import CadastroPolicial from '../components/Cadastros/CadastroPolicial';
import Menu from '../components/Menu';
import Registro from '../components/BO/Registro'; // Importando o componente Registro

const Index: React.FC = () => {
  // Estado para controlar qual página exibir
  const [activePage, setActivePage] = useState<
    'home' | 'loginCidadao' | 'loginPolicial' | 'cadastroCidadao' | 'cadastroPolicial' | 'menu' | 'registrarBoletim' | 'consultarBoletins'
  >('home');

  // Funções para alterar o estado e mostrar as páginas corretas
  const showLoginCidadao = () => setActivePage('loginCidadao');
  const showLoginPolicial = () => setActivePage('loginPolicial');
  const showCadastroCidadao = () => setActivePage('cadastroCidadao');
  const showCadastroPolicial = () => setActivePage('cadastroPolicial');
  const showMenu = () => setActivePage('menu');
  const goHome = () => setActivePage('home');
  const showRegistrarBoletim = () => setActivePage('registrarBoletim'); // Função para ir ao Registrar Boletim
  const showConsultarBoletins = () => setActivePage('consultarBoletins'); // Função para ir ao Consultar Boletins

  // Funções para redirecionamento após criação de conta
  const onCitizenAccountCreated = () => setActivePage('loginCidadao');
  const onPoliceAccountCreated = () => setActivePage('loginPolicial');

  return (
    <>
      {activePage === 'home' && (
        <Home onCitizenClick={showLoginCidadao} onPoliceClick={showLoginPolicial} />
      )}
      {activePage === 'loginCidadao' && (
        <LoginCidadao onLoginSuccess={showMenu} onCreateAccountClick={showCadastroCidadao} />
      )}
      {activePage === 'loginPolicial' && (
        <LoginPolicial onLoginSuccess={showMenu} onCreateAccountClick={showCadastroPolicial} />
      )}
      {activePage === 'cadastroCidadao' && (
        <CadastroCidadao onAccountCreated={onCitizenAccountCreated} onBackToLogin={showLoginCidadao} />
      )}
      {activePage === 'cadastroPolicial' && (
        <CadastroPolicial onAccountCreated={onPoliceAccountCreated} onBackToLogin={showLoginPolicial} />
      )}
      {activePage === 'menu' && (
        <Menu onRegisterClick={showRegistrarBoletim} onConsultClick={showConsultarBoletins} />
      )}
      {activePage === 'registrarBoletim' && (
        <Registro onHomeClick={() => setActivePage('menu')} />
      )}
      {activePage === 'consultarBoletins' && (
        <div>Consultar Meus Boletins de Ocorrência (Placeholder)</div> /* Placeholder para futura tela */
      )}
    </>
  );
};

export default Index;
