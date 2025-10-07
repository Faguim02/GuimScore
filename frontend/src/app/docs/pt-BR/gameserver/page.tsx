import { ArrowRight } from 'lucide-react';
import React from 'react';

const GameServer = () => {
    return (
        <div className="max-w-4xl mx-auto px-6 py-12">

            <header className="text-center mb-12">
                <h1 className="text-4xl md:text-5xl font-extrabold text-indigo-600 mb-4">Gerenciamento de Game Servers</h1>
                <p className="text-lg md:text-xl text-gray-600 max-w-3xl mx-auto">Esta página detalha como você pode criar, editar e remover seus game servers diretamente pela interface do GuimScore.</p>
            </header>

            <section className="mb-16 bg-white rounded-xl shadow-md p-8 md:p-10">
                <h2 className="text-2xl font-bold text-gray-800 mb-4">O que é um Game Server</h2>
                <p className="text-gray-700 leading-relaxed mb-6">
                    O game server será a base do seu servidor de jogo online, permitindo que você gerencie conexões, sessões e dados dos jogadores de forma eficiente.
                </p>
                <p className="text-gray-700 leading-relaxed mb-6">
                    Cada game server é associado a um servidor específico e pode ser configurado para atender às necessidades do seu jogo, incluindo parâmetros como tipo de jogo, região, capacidade máxima de jogadores e outras configurações personalizadas.
                </p>
                <p className="text-gray-700 leading-relaxed mb-6">
                    Cada GameServer tem um ID exclusivo, é importante guardar o ID do game server, pois ele será necessário para fazer chamadas á API para interagir com o seu servidor servidor.
                </p>
            </section>

            <section className="mb-16 bg-white rounded-xl shadow-md p-8 md:p-10">
                <h2 className="text-2xl font-bold text-gray-800 mb-4">Criando um GameServer</h2>
                <p className="text-gray-700 leading-relaxed mb-6">Para criar um novo game server, siga os passos abaixo:</p>

                <ol className='list-decimal list-inside text-gray-700 leading-relaxed mb-6'>
                    <li>Navegue até a seção "Game Servers" no painel de controle. <code className="bg-gray-200 p-1 rounded">/dashboard</code> </li>
                    <li>Clique no botão "Criar Game Server".</li>
                    <li>Preencha os campos necessários, como nome do servidor, descrição (opcional)</li>
                    <li>Confirme as informações e clique em "Salvar" para provisionar seu novo servidor.</li>
                </ol>
                <p className='text-gray-700 leading-relaxed'>Após a criação, o servidor aparecerá na sua lista de game servers junto com o id do game server.</p>
            </section>

            <section className="mb-16 bg-white rounded-xl shadow-md p-8 md:p-10">
                <h2 className="text-2xl font-bold text-gray-800 mb-4">Editar game server</h2>
                <p className="text-gray-700 leading-relaxed mb-6">Para editar o game server, siga os passos abaixo:</p>

                <ol className='list-decimal list-inside text-gray-700 leading-relaxed mb-6'>
                    <li>Aperte nos três pontinhos ao lado do game server que deseja editar e em seguida 'editar'</li>
                    <li>ALtere os dados e aperte em salvar</li>
                </ol>
            </section>

            <section className="mb-16 bg-white rounded-xl shadow-md p-8 md:p-10">
                <h2 className="text-2xl font-bold text-gray-800 mb-4">Deletar game server</h2>
                <p className="text-gray-700 leading-relaxed mb-6">Para deletar o game server, siga os passos abaixo:</p>

                <ol className='list-decimal list-inside text-gray-700 leading-relaxed mb-6'>
                    <li>Aperte nos três pontinhos ao lado do game server que deseja editar e em seguida 'deletar'</li>
                </ol>
            </section>
            <section className="mb-16 bg-white rounded-xl p-8 md:p-10">
                <a href='/docs/pt-BR/player' className="flex items-center text-indigo-600 font-semibold hover:underline mx-auto w-fit">
                    <p>Proximo</p>
                    <ArrowRight className="inline-block ml-2 h-4 w-4" />
                </a>
            </section>
        </div>
    );
}

export default GameServer;
