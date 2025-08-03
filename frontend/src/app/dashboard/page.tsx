"use client"

import Link from 'next/link'
import { useState } from 'react'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { Server, MoreVertical } from "lucide-react"
import { GameServerService } from '@/service/gameServerService'

const fakeGameServers = [
  {
    id: '1',
    name: 'Servidor de Teste 1',
    description: 'Um servidor para testar o jogo X.',
    createdAt: new Date().toISOString(),
    userId: 'user-1',
  },
  {
    id: '2',
    name: 'Arena Competitiva',
    description: 'Servidor para partidas 5v5.',
    createdAt: new Date().toISOString(),
    userId: 'user-1',
  },
  {
    id: '3',
    name: 'Mundo Aberto',
    description: '',
    createdAt: new Date().toISOString(),
    userId: 'user-1',
  },
]

export default function Dashboard() {
  const [gameServers, setGameServers] = useState(fakeGameServers)
  const [openDropdownId, setOpenDropdownId] = useState<string | null>(null)
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false)
  const [error, setError] = useState<string | null>(null)
  const [newServer, setNewServer] = useState({
    nameServer: '',
    description: '',
  })

  const handleCreateServer = async () => {
    
    if (!newServer.nameServer) {
      setError('O nome do servidor é obrigatório.')
      return
    }

    try {

      let gameServerService = new GameServerService();

      await gameServerService.createGameServer(newServer)

      setGameServers([...gameServers, {
        id: "1",
        name: newServer.nameServer,
        description: newServer.description,
        createdAt: new Date().toISOString(),
        userId: 'user-1',
      }])

    } catch (err) {
      setError('Erro ao criar o servidor. Tente novamente.')
      console.error(err)
      return
    }

    setIsCreateDialogOpen(false)
  }

  const handleDeleteServer = (id: string) => {
    // Lógica para apagar um servidor (no futuro)
    console.log("Apagar servidor com ID:", id)
  }

  return (
    <>
      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Game Servers</h1>
          <p className="text-gray-600 mt-2">Gerencie seus game servers e dados de jogos</p>
        </div>
        
        <Dialog open={isCreateDialogOpen} onOpenChange={setIsCreateDialogOpen}>
          <DialogTrigger asChild>
            <Button className='bg-brand-purple-100 hover:bg-brand-purple-200'>Criar Game Server</Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Criar Novo Game Server</DialogTitle>
              <DialogDescription>
                Crie um novo servidor para armazenar dados dos seus jogos.
              </DialogDescription>
            </DialogHeader>
            <div className="space-y-4">
              <div>
                <label className="text-sm font-medium">Nome</label>
                <Input
                  placeholder="Nome do game server"
                  value={newServer.nameServer}
                  onChange={(e) => setNewServer({ ...newServer, nameServer: e.target.value })}
                />
              </div>
              <div>
                <label className="text-sm font-medium">Descrição</label>
                <Input
                  placeholder="Descrição do game server"
                  value={newServer.description}
                  onChange={(e) => setNewServer({ ...newServer, description: e.target.value })}
                />

                {error && <p className="text-red-500 text-sm text-center mt-4">{error}</p>}
              </div>
            </div>
            <DialogFooter>
              <Button variant="outline" onClick={() => setIsCreateDialogOpen(false)}>
                Cancelar
              </Button>
              <Button onClick={handleCreateServer} disabled={!newServer.nameServer}>
                Criar
              </Button>
            </DialogFooter>
          </DialogContent>
        </Dialog>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {gameServers.map((server) => (
          <Card key={server.id} className="hover:shadow-lg transition-shadow">
            <CardHeader className="flex flex-row items-center justify-between">
              <div className="flex items-center gap-2">
                <Server className="h-6 w-6 text-gray-500" />
                <CardTitle className="text-xl">{server.name}</CardTitle>
              </div>
              <div className="relative">
                <button onClick={() => setOpenDropdownId(openDropdownId === server.id ? null : server.id)}>
                  <MoreVertical className="h-5 w-5" />
                </button>
                {openDropdownId === server.id && (
                  <div className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg z-10">
                    <button className="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                      Editar
                    </button>
                    <button 
                      className="block w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-gray-100"
                      onClick={() => handleDeleteServer(server.id)}
                    >
                      Apagar
                    </button>
                  </div>
                )}
              </div>
            </CardHeader>
            <CardContent>
              <CardDescription>{server.description || 'Sem descrição'}</CardDescription>
              <div className="space-y-2 text-sm text-gray-600 mt-4">
                <p>ID: {server.id}</p>
                <p>Criado em: {new Date(server.createdAt).toLocaleDateString('pt-BR')}</p>
              </div>
              <div className="flex gap-2 mt-4">
                <Link href={`/dashboard/game-server/${server.id}`}>
                  <Button size="sm" variant="outline">
                    Gerenciar Items
                  </Button>
                </Link>
              </div>
            </CardContent>
          </Card>
        ))}
        
        {gameServers.length === 0 && (
          <div className="text-center py-12 col-span-full">
            <h3 className="text-lg font-medium text-gray-900 mb-2">
              Nenhum game server encontrado
            </h3>
            <p className="text-gray-600 mb-4">
              Crie seu primeiro game server para começar a armazenar dados dos seus jogos.
            </p>
            <Button onClick={() => setIsCreateDialogOpen(true)} className='bg-brand-purple-100 hover:bg-brand-purple-200'>
              Criar Primeiro Game Server
            </Button>
          </div>
        )}
      </div>
    </>
  )
}
