import React from 'react';

const Data = () => {
    return (
        <div className="max-w-4xl mx-auto px-6 py-12">
            <header className="text-center mb-12">
                <h1 className="text-4xl md:text-5xl font-extrabold text-indigo-600 mb-4">Gerenciamento de Dados do Game Server</h1>
                <p className="text-lg md:text-xl text-gray-600 max-w-3xl mx-auto">Esta página explica como você pode gerenciar os dados associados aos seus game servers, permitindo salvar informações cruciais como vida do jogador, nível, pontos, stamina, entre outros, utilizando um sistema de chave (string) e valor (numérico).</p>
            </header>

            <section className='mb-16 bg-white rounded-xl shadow-md p-8 md:p-10'>
                <h2 className='text-2xl font-bold text-gray-800 mb-4'>Estrutura dos Dados</h2>
                <p className='text-gray-700 leading-relaxed mb-6'>Os dados do game server são armazenados como pares de chave-valor, onde:</p>
                <ul className='list-disc list-inside text-gray-700 mb-6'>
                    <li><strong>Chave (String):</strong> Um identificador único para o dado (ex: "player_health", "player_level", "player_score").</li>
                    <li><strong>Valor (Numérico):</strong> O valor associado à chave (ex: 100 para vida, 5 para nível, 1500 para pontos).</li>
                </ul>
                <p className='text-gray-700 pb-6'>Essa estrutura flexível permite que você armazene uma vasta gama de informações numéricas para seus jogadores e o estado do jogo.</p>

                <h2 className='text-2xl font-bold text-gray-800 mb-4'>Exemplos de Uso</h2>
                <p className='text-gray-700 leading-relaxed mb-6'>Você pode usar este sistema para:</p>
                <ul className='list-disc list-inside text-gray-700 mb-6'>
                    <li><strong>Vida do Jogador:</strong> <code>"player_health": 100</code></li>
                    <li><strong>Nível do Jogador:</strong> <code>"player_level": 5</code></li>
                    <li><strong>Pontuação:</strong> <code>"player_score": 1500</code></li>
                    <li><strong>Stamina:</strong> <code>"player_stamina": 75</code></li>
                    <li><strong>Moedas do Jogo:</strong> <code>"player_coins": 250</code></li>
                </ul>
            </section>

            <section className='mb-16 bg-white rounded-xl shadow-md p-4 md:p-10'>
                <h2 className='text-lg font-bold text-red-500 mb-4'>Importante</h2>
                <p className='text-base md:text-base text-gray-800 max-w-3xl pb-6'>Para fazer as requisições, será necessario que você tenha gerado uma <code className='bg-gray-200 p-1 rounded'>api-key</code> no painel de controle do GuimScore, o seu nome de usuario administrador <code className='bg-gray-200 p-1 rounded'>user-name</code> E também precisa do <code className='bg-gray-200 p-1 rounded'>game-server-id</code> para algumas rotas</p>
            </section>

            <section className='mb-16 bg-white rounded-xl shadow-md p-8 md:p-10'>
            
                <p>Para criar um dado para os jogadores de um servidor pelo painel de controle</p>

                <ol>
                    <li>Navegue ao painel de controle</li>
                    <li>Selecione o game server desejado</li>
                    <li>Adicione novos pares chave-valor conforme necessário</li>
                    <li>Salve as alterações</li>
                </ol>

                <p>Pronto, um novo dado foi criado, observe que na tabela dos dados, ele aparece junto com o indentificador do dado</p>

            </section>

            <section className="mb-16 bg-white rounded-xl p-8 md:p-10">

                <h2 className='text-2xl font-bold text-gray-800 mb-4'>Buscando pelos dados do jogador</h2>
                

            </section>

        </div>
    );
}

export default Data;
