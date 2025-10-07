"use client";

import { useState } from 'react';

const Data = () => {

    const [lenguageProgramming, setLenguageProgramming] = useState('bash');
    const lenguages = ['bash', 'gdscript'];

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

                <h2 className='text-2xl font-bold text-gray-800 mb-4'>Criando um novo dado</h2>
            
                <p>Para criar um dado para os jogadores de um servidor pelo painel de controle</p>

                <ul className='list-disc list-inside text-gray-700 mb-6'>
                    <li>Navegue ao painel de controle</li>
                    <li>Selecione o game server desejado</li>
                    <li>Adicione novos pares chave-valor conforme necessário</li>
                    <li>Salve as alterações</li>
                </ul>

                <p>Pronto, um novo dado foi criado, observe que na tabela dos dados, ele aparece junto com o indentificador do dado</p>

            </section>

            <section className="mb-16 bg-white rounded-xl p-8 md:p-10">

                <h2 className='text-2xl font-bold text-gray-800 mb-4'>Buscando pelos dados do jogador</h2>
                <p>Para buscar os dados de um jogador, utilize a rota <code className="bg-gray-200 p-1 rounded">GET</code> o endpoint <code className="bg-gray-200 p-1 rounded">/api/player?player-id=<span className='text-red-500 p-1 rounded'>playerId</span>&api-key=<span className='text-red-500 p-1 rounded'>API_KEY</span>&user-name=<span className='text-red-500 p-1 rounded'>USER_NAME</span></code>, onde playerId representa o ID do jogador e key representa a chave do dado que vocé deseja buscar.</p>

                <h3 className="text-lg font-semibold mb-4">Endepoint</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-http">
    <code className=" p-1 rounded"> <code className='bg-indigo-500 p-1 rounded'>GET</code> /api/player?player-id=<span className='text-yellow-400 p-1 rounded'>playerId</span>&api-key=<span className='text-yellow-400 p-1 rounded'>API_KEY</span>&user-name=<span className='text-yellow-400 p-1 rounded'>USER_NAME</span></code>
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
{`curl -X GET "https://api.guimscore.com/api/player?player-id=ID_DO_JOGADOR&api-key=SUA_API_KEY&user-name=SEU_NOME_DE_ADMINISTRADOR" \\`}
                     </code>
                    }

                    {lenguageProgramming === 'gdscript' && 
                    <code>
{`
    var playerId = "7851c27f-4447-4f9a-a34e-dbefc1500a9d" # ID do jogador que você deseja buscar
    var apiKey = "SUA_API_KEY"
    var userName = "SEU_NOME_DE_USUARIO"

    func _on_button_pressed():
        var url = "localhost:3000/api/player?player-id=" + playerId + "&api-key=" + apiKey + "&user-name=" + userName
        $HTTPRequest.request(url)

    func _on_HTTPRequest_request_completed(result, response_code, headers, body):
        if response_code == 200:
            var response = JSON.parse(body.get_string_from_utf8())
            print("Dados do jogador:", response)
    
`}
                    </code>
                    }

                </pre>

                <h3>Resposta</h3>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-json">
{`{
	"id": "7851c27f-4447-4f9a-a34e-dbefc1500a9d",
	"name": "gabi",
	"dateOfBirth": null,
	"gameServerId": "26768b76-bf4a-4bec-ba5a-397a68032359",
	"items": [],
	"data": [
		{
			"uuid": "9077a057-2ce2-412e-9ef1-ca98fcdd0b14",
			"nameData": "level",
			"value": 1,
			"maxValue": 100,
			"minValue": 1,
			"player": null,
			"gameServerModel": null
		},
		{
			"uuid": "1dce2d41-6b13-4d11-97ef-658b04496731",
			"nameData": "coin",
			"value": 0,
			"maxValue": 100,
			"minValue": 0,
			"player": null,
			"gameServerModel": null
		}
	]
}`}
                    </code>
                </pre>
            </section>

            <section className='mb-16 bg-white rounded-xl md:p-10'>

                <h2 className="text-2xl font-semibold mb-4">Adicionando um novo valor ao dado</h2>
                <p>Para adicionar um novo valor ao dado, temos duas alternativas de rotas:</p>
                <ol className='text-lg md:text-xl text-gray-800 max-w-3xl pb-6'>
                    <li>
                        <p>Esta rota permite almentar o valor do dado:</p>
                        <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                            <code className=" p-1 rounded"> <code className='bg-green-500 p-1 rounded'>POST</code> /api/player/addValue?api-key=<span className='text-yellow-400 p-1 rounded'>API_KEY</span>&user-name=<span className='text-yellow-400 p-1 rounded'>USER_NAME</span></code>
                        </pre>
                    </li>
                    <li>
                        <p>Esta rota permite diminuir o valor do dado:</p>
                        <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                            <code className=" p-1 rounded"> <code className='bg-green-500 p-1 rounded'>POST</code> /api/player/subtractValue?api-key=<span className='text-yellow-400 p-1 rounded'>API_KEY</span>&user-name=<span className='text-yellow-400 p-1 rounded'>USER_NAME</span></code>
                        </pre>
                    </li>
                </ol>

                <h3 className="text-lg font-semibold mb-4">Corpo da Requisição (Body)</h3>
                <p className="text-lg md:text-xl text-gray-800 max-w-3xl pb-6">
                    O corpo da requisição deve ser um objeto JSON contendo os seguintes campos:
                </p>
                <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
                    <code className="language-json">
{
`
{
    "playerId": "id do jogador",
    "dataId": "id do dado",
    "gameId": "id do game server",
    "value": 10 # valor a ser adicionado ou subtraiido
}    
`
}
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
                    <code>
{`curl -X POST \\
    http://localhost:3000/api/player/addValue?api-key=API_KEY&user-name=USER_NAME \\
    -H 'Content-Type: application/json' \\
    -d '{
        "playerId": "id do jogador",
        "dataId": "id do dado",
        "gameId": "id do game server",
        "value": 10 # valor a ser adicionado ou subtraiido
    }'`}
                    </code>
                    }

                    {lenguageProgramming === 'gdscript' && (
                        <code>
{
`
var url = "http://localhost:3000/api/player/addValue"
var api_key = "SUA_API_KEY"
var user_name = "SEU_USER_NAME"
var full_url = "%s?api-key=%s&user-name=%s" % [url, api_key, user_name]
var body = {
    "playerId": "id do jogador",
    "dataId": "id do dado",
    "gameId": "id do game server",
    "value": 10 # valor a ser adicionado ou subtraiido
}
var json_body = json.print(body)

var headers = {
    "Content-Type": "application/json"
}

func _on_button_pressed():
    $HTTPRequest.request(full_url, headers, false, HTTPClient.METHOD_POST, json_body)

func _on_HTTPRequest_request_completed(result, response_code, headers, body):
    if response_code == 200:
        var response = JSON.parse(body.get_string_from_utf8())
        print("Resposta:", response)
`
}
                        </code>
                    )}

                </pre>
            </section>

        </div>
    );
}

export default Data;
