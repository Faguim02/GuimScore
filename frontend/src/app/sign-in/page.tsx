import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import Link from 'next/link'

export default function SignIn() {
  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 animate-scroll-bg"
    style={{
      backgroundImage: "url('/bg_controls.png')",
      backgroundSize: 'auto',
      backgroundRepeat: 'repeat-y',
    }}>
      <Card className="bg-white w-full max-w-md dark:bg-gray-900 shadow-lg border dark:border-gray-700">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl dark:text-white">Login</CardTitle>
          <CardDescription className='dark:text-gray-400'>
            Acesse sua conta para gerenciar seus game servers.
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
            <div>
              <label className="text-sm font-medium dark:text-gray-400">User Name</label>
              <Input type="text" placeholder="seu username" className='dark:bg-gray-800 border dark:border-gray-700'/>
            </div>
            <div>
              <label className="text-sm font-medium dark:text-gray-400">Senha</label>
              <Input type="password" placeholder="Sua senha" className='dark:bg-gray-800 border dark:border-gray-700'/>
            </div>
          </div>
          <Button className="w-full mt-6 bg-brand-purple-100 hover:bg-brand-purple-200">Entrar</Button>
          <div className="mt-4 text-center text-sm dark:text-gray-400">
            Não tem uma conta?{' '}
            <Link href="/sign-up" className="underline dark:text-brand-purple-100 hover:text-brand-purple-200">
              Registre-se
            </Link>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
