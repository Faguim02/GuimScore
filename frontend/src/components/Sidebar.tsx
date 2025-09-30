import React from 'react';

const Sidebar = () => {
  return (
    <aside className="fixed left-0 top-0 h-full w-64 bg-gray-800 text-white p-4">
      <div className="text-2xl font-bold mb-6">GuimScore docs</div>
      <nav>
        <ul>
          <li className="mb-2">
            <a href="/docs/pt-BR/introduction" className="block hover:bg-gray-700 p-2 rounded">
              Introdução
            </a>
          </li>
          <li className="mb-2">
            <a href="/docs/pt-BR/auth" className="block hover:bg-gray-700 p-2 rounded">
              Authentencição
            </a>
          </li>
          <li className="mb-2">
            <a href="/docs/pt-BR/gameserver" className="block hover:bg-gray-700 p-2 rounded">
              GameServer
            </a>
          </li>
          <li className="mb-2">
            <a href="/docs/pt-BR/data" className="block hover:bg-gray-700 p-2 rounded">
              Gerenciar dados
            </a>
          </li>
          <li className="mb-2">
            <a href="/docs/pt-BR/list" className="block hover:bg-gray-700 p-2 rounded">
              Gerenciar listas
            </a>
          </li>
        </ul>
      </nav>
    </aside>
  );
};

export default Sidebar;
