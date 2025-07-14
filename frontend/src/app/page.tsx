import { Button } from '@/components/ui/button'
import Link from 'next/link'
import {
  Shield,
  Paintbrush,
  Database,
  Instagram,
  Github,
  Linkedin,
  Globe,
} from 'lucide-react'

export default function LandingPage() {
  return (
    <div
      className="flex flex-col min-h-screen animate-scroll-bg"
      style={{
        backgroundImage: "url('/bg_controls.png')",
        backgroundSize: 'auto',
        backgroundRepeat: 'repeat-y',
      }}
    >
      <header className="px-4 lg:px-6 h-14 flex items-center">
        <Link className="flex items-center justify-center" href="#">
          <span className="sr-only">GuimScore</span>
        </Link>
        <nav className="ml-auto flex gap-4 sm:gap-6">
          <Link
            className="text-gray-200 text-sm font-poppins font-medium hover:underline underline-offset-4"
            href="/sign-in"
          >
            Login
          </Link>
          <Link
            className="text-gray-200 text-sm font-poppins font-medium hover:underline underline-offset-4"
            href="/sign-up"
          >
            Registrar
          </Link>
        </nav>
      </header>
      <main className="flex-1">
        <section className="h-screen w-full py-12 md:py-24 lg:py-32 xl:py-24">
          <div className="container px-4 md:px-6">
            <div className="flex flex-col items-center space-y-8 text-center">
              <div className="space-y-2">
                <img src="/logo_not_text.svg" alt="GuimScore Logo" className="h-44 w-auto mx-auto" />
                <h1 className="font-fugaz-one text-3xl font-bold tracking-tighter text-gray-100 sm:text-4xl md:text-5xl lg:text-6xl/none">
                  GuimScore
                </h1>
                <p className="text-gray-200 mx-auto max-w-[700px] font-poppins md:text-xl">
                  A sua plataforma para gerenciamento de dados de jogos. Crie, gerencie e analise os dados dos seus servidores de jogos com facilidade.
                </p>
              </div>
              <div className="space-x-4">
                <Link
                  className="text-gray-100 inline-flex h-9 items-center justify-center rounded-md bg-brand-purple-100 px-6 py-4 text-sm font-poppins font-medium shadow transition-colors hover:bg-brand-purple-200 focus-visible:outline-none focus-visible:ring-1 focus-visible:bg-brand-purple-100 disabled:pointer-events-none disabled:opacity-50 dark:bg-brand-purple-100 dark:text-gray-100 dark:hover:bg-brand-purple-200 dark:focus-visible:ring-gray-300"
                  href="/dashboard"
                >
                  <img src="/icons/icon_start.svg" alt="Start Icon" className="h-4 w-4 mr-2" />
                  Dashboard
                </Link>

                <Link
                  className="text-gray-100 inline-flex h-9 items-center justify-center rounded-md bg-brand-purple-100 px-6 py-4 text-sm font-poppins font-medium shadow transition-colors hover:bg-brand-purple-200 focus-visible:outline-none focus-visible:ring-1 focus-visible:bg-brand-purple-100 disabled:pointer-events-none disabled:opacity-50 dark:bg-brand-purple-100 dark:text-gray-100 dark:hover:bg-brand-purple-200 dark:focus-visible:ring-gray-300"
                  href="/docs"
                >
                  <img src="/icons/icon_docs.svg" alt="Start Icon" className="h-4 w-4 mr-2" />
                  Documentação
                </Link>
              </div>
            </div>
          </div>
        </section>
        <section className="h-screen w-full py-12 md:py-24 lg:py-32 bg-white dark:bg-gray-950">
          <div className="h-full container grid items-center justify-center gap-4 px-4 text-center md:px-6 lg:gap-10">
            <div className="grid w-full grid-cols-1 lg:grid-cols-3 gap-8">
              <div className="flex flex-col items-center justify-center space-y-4 rounded-lg bg-gray-50 p-6 shadow-sm dark:bg-gray-900/50">
                <Shield className="h-12 w-12 text-brand-purple-100" />
                <h3 className="text-xl font-bold font-fugaz-one text-black dark:text-white">Segurança</h3>
                <p className="text-gray-500 dark:text-gray-400 font-poppins text-center">
                  Usuários e jogadores serão totalmente protegidos.
                </p>
              </div>
              <div className="flex flex-col items-center justify-center space-y-4 rounded-lg bg-gray-50 p-6 shadow-sm dark:bg-gray-900/50">
                <Paintbrush className="h-12 w-12 text-brand-purple-100" />
                <h3 className="text-xl font-bold font-fugaz-one text-black dark:text-white">Fácil acesso</h3>
                <p className="text-gray-500 dark:text-gray-400 font-poppins text-center">
                  Interface amigável para o cliente, facilitando o uso.
                </p>
              </div>
              <div className="flex flex-col items-center justify-center space-y-4 rounded-lg bg-gray-50 p-6 shadow-sm dark:bg-gray-900/50">
                <Database className="h-12 w-12 text-brand-purple-100" />
                <h3 className="text-xl font-bold font-fugaz-one text-black dark:text-white">Armazenamento</h3>
                <p className="text-gray-500 dark:text-gray-400 font-poppins text-center">
                  Armazena os dados dos jogos, e cria backups.
                </p>
              </div>
            </div>
          </div>
        </section>
      </main>
      <footer className="bg-[#212121] text-white font-poppins">
        <div className="container mx-auto px-6 py-12">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
            <div className="flex flex-col items-start">
              <div className="flex items-center">
                <img src="/logo.svg" alt="GuimScore Logo" className="h-12 w-auto" />
                <span className="ml-2 text-xl font-bold font-fugaz-one">GuimScore</span>
              </div>
            </div>
            <div className="flex flex-col">
              <h3 className="font-bold text-lg mb-4 font-fugaz-one">Links</h3>
              <Link href="#" className="hover:underline">Início</Link>
              <Link href="/sign-up" className="hover:underline">Criar Conta</Link>
              <Link href="/sign-up" className="hover:underline">Cadastrar</Link>
              <Link href="#" className="hover:underline">Calculadora</Link>
            </div>
            <div></div>
            <div className="flex flex-col items-start md:items-end">
              <h3 className="font-bold text-lg mb-4 font-fugaz-one">Entre em contato</h3>
              <input
                type="text"
                placeholder="Text"
                className="bg-gray-700 border border-gray-600 rounded-md w-full p-2 mb-4 text-white placeholder-gray-400"
              />
              <div className="flex space-x-4">
                <a href="#" aria-label="Instagram">
                  <Instagram className="h-6 w-6" />
                </a>
                <a href="#" aria-label="GitHub">
                  <Github className="h-6 w-6" />
                </a>
                <a href="#" aria-label="LinkedIn">
                  <Linkedin className="h-6 w-6" />
                </a>
              </div>
            </div>
          </div>
          <div className="border-t border-gray-700 mt-12 pt-6 flex flex-col md:flex-row justify-between items-center text-sm">
            <p className="mb-4 md:mb-0">© 2025 - Faguim</p>
            <div className="flex items-center space-x-4">
              <Link href="#" className="hover:underline">Termos e condições</Link>
              <Link href="#" className="hover:underline">Política de privacidade</Link>
              <div className="flex items-center">
                <Globe className="h-5 w-5 mr-2" />
                <span>Português brasil</span>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}
