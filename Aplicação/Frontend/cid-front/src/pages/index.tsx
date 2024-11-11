// src/pages/index.tsx
import { useState } from 'react';
import Home from '../components/Home';
import LoginCidadao from '../components/LoginCidadao';
import LoginPolicial from '../components/LoginPolicial';

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
  const [activePage, setActivePage] = useState<'home' | 'loginCidadao' | 'loginPolicial' | 'menu'>('home');

  // Funções para alterar o estado e mostrar o login ou o menu
  const showLoginCidadao = () => setActivePage('loginCidadao');
  const showLoginPolicial = () => setActivePage('loginPolicial');
  const showMenu = () => setActivePage('menu'); // Função para exibir o menu

  return (
    <>
      {activePage === 'home' && (
        <Home onCitizenClick={showLoginCidadao} onPoliceClick={showLoginPolicial} />
      )}
      {activePage === 'loginCidadao' && <LoginCidadao onLoginSuccess={showMenu} />}
      {activePage === 'loginPolicial' && <LoginPolicial onLoginSuccess={showMenu} />}
      {activePage === 'menu' && <Menu />}
    </>
  );
};

export default Index;
