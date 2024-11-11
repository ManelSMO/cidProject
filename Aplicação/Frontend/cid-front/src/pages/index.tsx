// src/pages/index.tsx
import { useState } from 'react';
import Home from '../components/Home';
import LoginCidadao from '../components/LoginCidadao';
import LoginPolicial from '../components/LoginPolicial';
import CadastroCidadao from '../components/CadastroCidadao';
import CadastroPolicial from '../components/CadastroPolicial';

// Componente do Menu principal
function Menu() {
  return (
    <div>
      <h1>Bem-vindo ao Menu Principal</h1>
      <p>Esta é a página de menu. Você pode adicionar os componentes de navegação aqui.</p>
    </div>
  );
}

const Index: React.FC = () => {
  // Estado para controlar qual página exibir
  const [activePage, setActivePage] = useState<
    'home' | 'loginCidadao' | 'loginPolicial' | 'cadastroCidadao' | 'cadastroPolicial' | 'menu'
  >('home');

  // Funções para alterar o estado e mostrar as páginas corretas
  const showLoginCidadao = () => setActivePage('loginCidadao');
  const showLoginPolicial = () => setActivePage('loginPolicial');
  const showCadastroCidadao = () => setActivePage('cadastroCidadao');
  const showCadastroPolicial = () => setActivePage('cadastroPolicial');
  const showMenu = () => setActivePage('menu'); // Função para exibir o menu
  const goHome = () => setActivePage('home'); // Função para retornar ao home

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
      {activePage === 'menu' && <Menu />}
    </>
  );
};

export default Index;

