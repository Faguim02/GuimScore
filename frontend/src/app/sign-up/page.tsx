"use client";

import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { UserService } from '@/service/userService';
import Link from 'next/link'
import { useState } from 'react';

export default function SignUp() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string>('');

  const handleSignUp = async () => {

    if (!username || !password || !email) {
      setError('Por favor, preencha todos os campos.');
      return;
    }
    setError('');

    const user = {
      name: username,
      email: email,
      password: password,
    };

    try {
      const userService = new UserService();
      await userService.signUp(user);

      window.location.href = '/sign-in';
    } catch (error: any) {
      if (error.response && error.response.data && error.response.data.message) {
        setError(error.response.data.message);
      } else {
        setError('Erro ao criar usuário');
      }
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 animate-scroll-bg"
    style={{
      backgroundImage: "url('/bg_controls.png')",
      backgroundSize: 'auto',
      backgroundRepeat: 'repeat-y',
    }}
    >
      <Card className="bg-white w-full max-w-md dark:bg-gray-900 shadow-lg border dark:border-gray-700">
        <CardHeader className="text-center">
          <CardTitle className="text-2xl dark:text-white">Criar Conta</CardTitle>
          <CardDescription className="dark:text-gray-400">
            Crie sua conta para começar a usar o GuimScore.
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
            <div>
              <label className="text-sm font-medium dark:text-gray-400">User Name</label>
              <Input 
                placeholder="Seu username" 
                className='dark:bg-gray-800 border dark:border-gray-700 dark:text-gray-400'
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div>
              <label className="text-sm font-medium dark:text-gray-400">Email</label>
              <Input 
                type="email" 
                placeholder="seu@email.com" 
                className='dark:bg-gray-800 border dark:border-gray-700 dark:text-gray-400'
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>
            <div>
              <label className="text-sm font-medium dark:text-gray-400">Senha</label>
              <Input 
                type="password" 
                placeholder="Crie uma senha" 
                className='dark:bg-gray-800 border dark:border-gray-700 dark:text-gray-400'
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>
          {error && <p className="text-red-500 text-sm text-center mt-4">{error}</p>}
          <Button 
            onClick={handleSignUp}
            className="w-full mt-6 bg-brand-purple-100 hover:bg-brand-purple-200"
          >
            Criar Conta
          </Button>
          <div className="mt-4 text-center text-sm dark:text-gray-400">
            Já tem uma conta?{' '}
            <Link href="/sign-in" className="underline dark:text-brand-purple-100 hover:text-brand-purple-200">
              Faça login
            </Link>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
