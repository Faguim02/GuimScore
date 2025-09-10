import { ArrowRight } from 'lucide-react';
import React from 'react';

const Auth = () => {
    return (
        <div className="max-w-4xl mx-auto px-6 py-12">

            <header className='text-center mb-12'>
                <h1 className="text-1xl md:text-5xl font-extrabold text-indigo-600 mb-4">Criação de Usuários/Players</h1>

                <p className="text-lg md:text-xl text-gray-600 max-w-3xl mx-auto">
                    Esta seção detalha como criar novos usuários ou players em seu servidor. A criação de usuários é um passo fundamental para gerenciar o acesso e a interação dentro da sua aplicação.
                </p>
            </header>

            <section>

            
                <h2 className="text-2xl font-semibold mb-4">Requisição de Criação de Usuário/Player</h2>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Para criar um novo usuário/player, você precisará enviar uma requisição <code className="bg-gray-200 p-1 rounded">POST</code> para o endpoint <code className="bg-gray-200 p-1 rounded">/auth/player/signUp</code>. Esta requisição deve incluir o nome de usuário e a senha no corpo da requisição.
                </p>

                <h3 className="text-lg font-semibold mb-4">Endpoint</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
    {`POST /auth/player/signUp`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Corpo da Requisição (Body)</h3>
                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    O corpo da requisição deve ser um objeto JSON contendo os seguintes campos:
                </p>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-json">
    {`{
    "name": "nome_do_usuario",
    "password": "senha_segura"
    }`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Autenticação</h3>
                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    É necessário fornecer um <code className="bg-gray-200 p-1 rounded">Bearer Token</code> do proprietário do servidor no cabeçalho <code className="bg-gray-200 p-1 rounded">Authorization</code> para autenticar a requisição.
                </p>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
    {`Authorization: Bearer SEU_BEARER_TOKEN_AQUI`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Exemplo de Requisição (cURL)</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-bash">
    {`curl -X POST \\
    http://localhost:3000/api/auth/player/signUp \\
    -H 'Content-Type: application/json' \\
    -H 'Authorization: Bearer SEU_BEARER_TOKEN_AQUI' \\
    -d '{
        "name": "novo_player",
        "password": "minha_senha_secreta"
    }'`}
                    </code>
                </pre>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Certifique-se de substituir <code className="bg-gray-200 p-1 rounded">SEU_BEARER_TOKEN_AQUI</code> pelo token real do proprietário do servidor e ajustar a URL do endpoint conforme necessário para o seu ambiente.
                </p>

            </section>
            <div className='h-12'></div>
            <section>

                <h2 className="text-2xl font-semibold mb-4">Requisição de Entrar na conta</h2>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Para logar o player, você precisará enviar uma requisição <code className="bg-gray-200 p-1 rounded">POST</code> para o endpoint <code className="bg-gray-200 p-1 rounded">/auth/player/signIn</code>. Esta requisição deve incluir o nome de usuário e a senha no corpo da requisição.
                </p>

                <h3 className="text-lg font-semibold mb-4">Endpoint</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
                {`POST /auth/player/signIn`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Corpo da Requisição (Body)</h3>
                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    O corpo da requisição deve ser um objeto JSON contendo os seguintes campos:
                </p>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-json">
{`{
"name": "nome_do_usuario",
"password": "senha_segura"
}`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Autenticação</h3>
                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    É necessário fornecer um <code className="bg-gray-200 p-1 rounded">Bearer Token</code> do proprietário do servidor no cabeçalho <code className="bg-gray-200 p-1 rounded">Authorization</code> para autenticar a requisição.
                </p>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
                {`Authorization: Bearer SEU_BEARER_TOKEN_AQUI`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Exemplo de Requisição (cURL)</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-bash">
{`curl -X POST \\
http://localhost:3000/api/auth/player/signUp \\
-H 'Content-Type: application/json' \\
-H 'Authorization: Bearer SEU_BEARER_TOKEN_AQUI' \\
-d '{
"name": "nome_do_player",
"password": "minha_senha_secreta"
}'`}
                    </code>
                </pre>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Certifique-se de substituir <code className="bg-gray-200 p-1 rounded">SEU_BEARER_TOKEN_AQUI</code> pelo token real do proprietário do servidor e ajustar a URL do endpoint conforme necessário para o seu ambiente.
                </p>

                <h3 className='className="text-lg font-semibold mb-4"'>Exemplo de Resposta</h3>
                <pre className='bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto'>
                    <code className='language-bash'>
                        {
                            '{"user_id": "uuid-do-usuario"}'
                        }
                    </code>
                </pre>

            </section>

            <section className="mb-16 bg-white rounded-xl p-8 md:p-10">
                <a href='/docs/pt-BR/gameserver' className="flex items-center text-indigo-600 font-semibold hover:underline mx-auto w-fit">
                    <p>Proximo</p>
                    <ArrowRight className="inline-block ml-2 h-4 w-4" />
                </a>
            </section>
        </div>
    );
};

export default Auth;
