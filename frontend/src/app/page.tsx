import { Button } from '@/components/ui/button'
import Link from 'next/link'

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
        <section className="w-full py-12 md:py-24 lg:py-32 xl:py-24">
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
                  className="inline-flex h-9 items-center justify-center rounded-md bg-brand-purple-100 px-6 py-4 text-sm font-poppins font-medium shadow transition-colors hover:bg-brand-purple-200 focus-visible:outline-none focus-visible:ring-1 focus-visible:bg-brand-purple-100 disabled:pointer-events-none disabled:opacity-50 dark:bg-brand-purple-100 dark:text-gray-100 dark:hover:bg-brand-purple-200 dark:focus-visible:ring-gray-300"
                  href="/dashboard"
                >
                  <img src="/icons/icon_start.svg" alt="Start Icon" className="h-4 w-4 mr-2" />
                  Dashboard
                </Link>

                <Link
                  className="inline-flex h-9 items-center justify-center rounded-md bg-brand-purple-100 px-6 py-4 text-sm font-poppins font-medium shadow transition-colors hover:bg-brand-purple-200 focus-visible:outline-none focus-visible:ring-1 focus-visible:bg-brand-purple-100 disabled:pointer-events-none disabled:opacity-50 dark:bg-brand-purple-100 dark:text-gray-100 dark:hover:bg-brand-purple-200 dark:focus-visible:ring-gray-300"
                  href="/docs"
                >
                  <img src="/icons/icon_docs.svg" alt="Start Icon" className="h-4 w-4 mr-2" />
                  Documentação
                </Link>
              </div>
            </div>
          </div>
        </section>
      </main>
      <footer className="flex flex-col gap-2 sm:flex-row py-6 w-full shrink-0 items-center px-4 md:px-6 border-t">
        <p className="text-xs font-poppins text-gray-500 dark:text-gray-400">
          © 2024 GuimScore. Todos os direitos reservados.
        </p>
      </footer>
    </div>
  )
}
