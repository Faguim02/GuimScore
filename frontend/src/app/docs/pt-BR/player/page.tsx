"use client";

import { ArrowRight } from 'lucide-react';
import { useState } from 'react';

const Auth = () => {

    const [lenguageProgramming, setLenguageProgramming] = useState('bash');
    const lenguages = ['bash', 'gdscript'];

    return (
        <div className="max-w-4xl mx-auto px-6 py-12">

            <header className='text-center mb-12'>
                <h1 className="text-1xl md:text-5xl font-extrabold text-indigo-600 mb-4">Criação de Usuários/Players</h1>

                <p className="text-lg md:text-xl text-gray-600 max-w-3xl mx-auto">
                    Esta seção detalha como criar novos jogadores em seu servidor e como fazer login dos jogadores. A criação de jogadores é um passo fundamental para gerenciar o acesso e a interação com dados que você definiu para seus jogadores.
                </p>
            </header>

            <section className='mb-16 bg-white rounded-xl shadow-md p-4 md:p-10'>
                <h2 className='text-lg font-bold text-red-500 mb-4'>Importante</h2>
                <p className='text-base md:text-base text-gray-800 max-w-3xl pb-6'>Para fazer as requisições, será necessario que você tenha gerado uma <code className='bg-gray-200 p-1 rounded'>api-key</code> no painel de controle do GuimScore, o seu nome de usuario administrador <code className='bg-gray-200 p-1 rounded'>user-name</code> E também precisa do <code className='bg-gray-200 p-1 rounded'>game-server-id</code> para algumas rotas</p>
            </section>

            <section>

            
                <h2 className="text-2xl font-semibold mb-4">Criando uma conta para o jogador</h2>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Para criar um novo jogador, você precisará enviar uma requisição <code className="bg-gray-200 p-1 rounded">POST</code> para o endpoint <code className="bg-gray-200 p-1 rounded">/api/player/signUp?api-key=<span className='text-red-500 p-1 rounded'>API_KEY</span>&user-name=<span className='text-red-500 p-1 rounded'>USER_NAME</span></code>. Esta requisição deve incluir o nome de usuário e a senha no corpo da requisição.
                </p>

                <h3 className="text-lg font-semibold mb-4">Endpoint</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
    {/* {`POST /auth/player/signUp`} */}
    <code className=" p-1 rounded"> <code className='bg-green-500 p-1 rounded'>POST</code> /api/player/signUp?api-key=<span className='text-yellow-400 p-1 rounded'>API_KEY</span>&user-name=<span className='text-yellow-400 p-1 rounded'>USER_NAME</span></code>
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
    "password": "senha_segura",
    "gameServerId": "game server id"
}`}
                    </code>
                </pre>

                <h3 className="text-lg font-semibold mb-4">Exemplo de Requisição {lenguageProgramming}</h3>
                <div className='bg-white flex p-0 mb-4 rounded'>
                        <select name="lenguage" onChange={(e) => setLenguageProgramming(e.target.value)} className="bg-slate-300 text-gray-900 p-2 rounded" id="">
                            {lenguages.map((lang) => (
                                <option key={lang} value={lang} selected={lenguageProgramming === lang}>{lang}</option>
                            ))}
                        </select>
                </div>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    
                    {lenguageProgramming === 'bash' && 
                     <code className="language-bash">
{`curl -X POST \\
    http://localhost:3000/api/player/signUp?api-key=API_KEY&user-name=USER_NAME \\
    -H 'Content-Type: application/json' \\
    -H 'Authorization: Bearer SEU_BEARER_TOKEN_AQUI' \\
    -d '{
        "name": "novo_player",
        "password": "minha_senha_secreta",
        "gameServerId": "game server id"
    }'`}
                    </code>
}

                    {lenguageProgramming === 'gdscript' && 
                    <code className='language-gd'>
{`
var url = "http://localhost:3000/api/player/signUp"
var api_key = "SUA_API_KEY"
var user_name = "SEU_USER_NAME"
var game_server_id = "SEU_GAME_SERVER_ID"
var full_url = "%s?api-key=%s&user-name=%s" % [url, api_key, user_name]
var body = {
    "name": "novo_player",
    "password": "minha_senha_secreta",
    "gameServerId": game_server_id
}
var json_body = json.print(body)

var headers = {
    "Content-Type": "application/json"
}

$HTTPRequest.request(full_url, headers, false, HTTPClient.METHOD_POST, json_body)
`}
                    </code>
}
                </pre>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Certifique-se de substituir pelas suas credenciais reais e ajustar a URL do endpoint conforme necessário para o seu ambiente.
                </p>

            </section>
            <div className='h-12'></div>
            <section>

                <h2 className="text-2xl font-semibold mb-4">Logando com a conta do jogador</h2>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Para logar o player, você precisará enviar uma requisição <code className="bg-gray-200 p-1 rounded">/api/player/signIn?api-key=<span className='text-red-500 p-1 rounded'>API_KEY</span>&user-name=<span className='text-red-500 p-1 rounded'>USER_NAME</span></code>. Esta requisição deve incluir o nome de usuário e a senha no corpo da requisição.
                </p>

                <h3 className="text-lg font-semibold mb-4">Endpoint</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
                    <code className=" p-1 rounded"> <code className='bg-green-500 p-1 rounded'>POST</code> /api/player/signIn?api-key=<span className='text-yellow-400 p-1 rounded'>API_KEY</span>&user-name=<span className='text-yellow-400 p-1 rounded'>USER_NAME</span></code>
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

                <h3 className="text-lg font-semibold mb-4">Exemplo de Requisição {lenguageProgramming}</h3>
                <div className='bg-white flex p-0 mb-4 rounded'>
                        <select name="lenguage" onChange={(e) => setLenguageProgramming(e.target.value)} className="bg-slate-300 text-gray-900 p-2 rounded" id="">
                            {lenguages.map((lang) => (
                                <option key={lang} value={lang} selected={lenguageProgramming === lang}>{lang}</option>
                            ))}
                        </select>
                </div>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    
                    {lenguageProgramming === 'bash' && 
                     <code className="language-bash">
{`curl -X POST \\
    http://localhost:3000/api/player/signIn?api-key=API_KEY&user-name=USER_NAME \\
    -H 'Content-Type: application/json' \\
    -H 'Authorization: Bearer SEU_BEARER_TOKEN_AQUI' \\
    -d '{
        "name": "novo_player",
        "password": "minha_senha_secreta",
    }'`}
                    </code>
}

                    {lenguageProgramming === 'gdscript' && 
                    <code className='language-gd'>
{`
var url = "http://localhost:3000/api/player/signIn"
var api_key = "SUA_API_KEY"
var user_name = "SEU_USER_NAME"
var full_url = "%s?api-key=%s&user-name=%s" % [url, api_key, user_name]
var body = {
    "name": "novo_player",
    "password": "minha_senha_secreta",
}
var json_body = json.print(body)

var headers = {
    "Content-Type": "application/json"
}

$HTTPRequest.request(full_url, headers, false, HTTPClient.METHOD_POST, json_body)
`}
                    </code>
}
                </pre>

                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    Certifique-se de substituir pelas suas credenciais reais e ajustar a URL do endpoint conforme necessário para o seu ambiente.
                </p>

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
