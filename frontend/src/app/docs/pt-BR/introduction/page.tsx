import { ArrowRight } from 'lucide-react'

const Introducao = () => {
  return (
    <div className="max-w-4xl mx-auto px-6 py-12">

    <header className="text-center mb-12">
      <h1 className="text-4xl md:text-5xl font-extrabold text-indigo-600 mb-4">
        GuimScore
      </h1>
      <p className="text-lg md:text-xl text-gray-600 max-w-3xl mx-auto">
        A plataforma simples e poderosa para gerenciar seu servidor de game online — sem complicações, com resultados reais.
      </p>
    </header>

    <section className="mb-16 bg-white rounded-xl shadow-md p-8 md:p-10">
      <h2 className="text-2xl font-bold text-gray-800 mb-4">Introdução ao GuimScore</h2>
      <p className="text-gray-700 leading-relaxed mb-6">
        Bem-vindo ao <strong className="text-indigo-600">GuimScore</strong>, a solução inteligente e intuitiva para desenvolvedores e administradores de servidores de jogos online que desejam oferecer uma experiência contínua e personalizada aos seus jogadores — sem a complexidade de sistemas tradicionais.
      </p>
      <p className="text-gray-700 leading-relaxed mb-6">
        Projetado com foco na simplicidade e eficiência, o GuimScore permite que você gerencie dados dos jogadores de forma rápida, segura e totalmente integrada ao seu servidor. Com uma interface amigável e uma API de fácil implementação, você pode começar a salvar e recuperar informações dos usuários em poucos minutos, sem precisar de conhecimentos avançados em banco de dados ou backend.
      </p>
    </section>

    <section className="mb-16">
      <h2 className="text-3xl font-bold text-gray-800 mb-8 text-center">Principais Funcionalidades</h2>

      <div className="grid md:grid-cols-3 gap-8">

        <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
          <div className="w-12 h-12 bg-indigo-100 rounded-full flex items-center justify-center mb-4">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-indigo-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
            </svg>
          </div>
          <h3 className="text-xl font-semibold text-gray-800 mb-2">Criação de Contas de Jogadores</h3>
          <p className="text-gray-600">
            Permita que seus jogadores se cadastrem com facilidade, criando perfis únicos vinculados ao seu servidor. Ideal para manter o progresso individual e fidelizar sua comunidade.
          </p>
        </div>

        <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
          <div className="w-12 h-12 bg-indigo-100 rounded-full flex items-center justify-center mb-4">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-indigo-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 7h6m0 10v-3m-3 3h.01M9 17h.01M9 14h.01M12 14h.01M15 11h.01M12 11h.01M9 11h.01M7 21h10a2 2 0 002-2V5a2 2 0 00-2-2H7a2 2 0 00-2 2v14a2 2 0 002 2z" />
            </svg>
          </div>
          <h3 className="text-xl font-semibold text-gray-800 mb-2">Sistema de Chave-Valor</h3>
          <p className="text-gray-600">
            Salve qualquer tipo de dado personalizado no formato chave-valor — desde pontuação e moedas até configurações de jogo e preferências do usuário. Flexível o suficiente para atender qualquer necessidade do seu jogo.
          </p>
        </div>

        <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
          <div className="w-12 h-12 bg-indigo-100 rounded-full flex items-center justify-center mb-4">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-indigo-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 10h16M4 14h16M4 18h16" />
            </svg>
          </div>
          <h3 className="text-xl font-semibold text-gray-800 mb-2">Gerenciamento de Itens em Listas</h3>
          <p className="text-gray-600">
            Armazene e gerencie coleções de itens, como inventários, missões concluídas, conquistas ou equipamentos. Adicione, remova ou atualize itens dinamicamente, mantendo a integridade dos dados dos jogadores.
          </p>
        </div>

      </div>
    </section>

    <section className="mb-16 bg-white rounded-xl p-8 md:p-10">
      <a href='/docs/pt-BR/auth' className="flex items-center text-indigo-600 font-semibold hover:underline mx-auto w-fit">
        <p>Proximo</p>
        <ArrowRight className="inline-block ml-2 h-4 w-4" />
      </a>
    </section>

    <section className="bg-indigo-600 text-white rounded-xl shadow-lg p-8 md:p-10 text-center">
      <h2 className="text-2xl md:text-3xl font-bold mb-4">Comece hoje mesmo.</h2>
      <p className="text-lg mb-6 max-w-3xl mx-auto">
        Simplifique. Gerencie. Evolua. Com GuimScore.
      </p>
      <p className="text-xl font-medium italic">
        🎮 <em>Guarde o progresso. Conecte os jogadores. Domine o jogo.</em>
      </p>
    </section>

  </div>
  );
}

export default Introducao;
