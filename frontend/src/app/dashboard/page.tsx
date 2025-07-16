"use client"

import { useState, useEffect } from 'react'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { gameServerApi } from '@/lib/api'
import { GameServer } from '@/types'

export default function Dashboard() {
  const [gameServers, setGameServers] = useState<GameServer[]>([])
  const [loading, setLoading] = useState(true)
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false)
  const [newServer, setNewServer] = useState({
    name: '',
    description: '',
    userId: 'user-1' // Por enquanto hardcoded, depois você pode implementar autenticação
  })

  useEffect(() => {
    loadGameServers()
  }, [])

  const loadGameServers = async () => {
    try {
      setLoading(true)
      const response = await gameServerApi.getAll()
      setGameServers(response.data.data)
    } catch (error) {
      console.error('Erro ao carregar game servers:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleCreateServer = async () => {
    try {
      await gameServerApi.create(newServer)
      setIsCreateDialogOpen(false)
      setNewServer({ name: '', description: '', userId: 'user-1' })
      loadGameServers()
    } catch (error) {
      console.error('Erro ao criar game server:', error)
    }
  }

  const handleDeleteServer = async (id: string) => {
    try {
      await gameServerApi.delete(id)
      loadGameServers()
    } catch (error) {
      console.error('Erro ao deletar game server:', error)
    }
  }

  if (loading) {
    return (
      <div className="flex items-center justify-center h-full">
        <div className="text-lg">Carregando...</div>
      </div>
    )
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
                  value={newServer.name}
                  onChange={(e) => setNewServer({ ...newServer, name: e.target.value })}
                />
              </div>
              <div>
                <label className="text-sm font-medium">Descrição</label>
                <Input
                  placeholder="Descrição do game server"
                  value={newServer.description}
                  onChange={(e) => setNewServer({ ...newServer, description: e.target.value })}
                />
              </div>
            </div>
            <DialogFooter>
              <Button variant="outline" onClick={() => setIsCreateDialogOpen(false)}>
                Cancelar
              </Button>
              <Button onClick={handleCreateServer} disabled={!newServer.name}>
                Criar
              </Button>
            </DialogFooter>
          </DialogContent>
        </Dialog>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {gameServers.map((server) => (
          <Card key={server.id} className="hover:shadow-lg transition-shadow">
            <CardHeader>
              <CardTitle className="text-xl">{server.name}</CardTitle>
              <CardDescription>{server.description || 'Sem descrição'}</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-2 text-sm text-gray-600">
                <p>ID: {server.id}</p>
                <p>Criado em: {new Date(server.createdAt).toLocaleDateString('pt-BR')}</p>
              </div>
              <div className="flex gap-2 mt-4">
                <Button size="sm" variant="outline">
                  Gerenciar Items
                </Button>
                <Button 
                  size="sm" 
                  variant="destructive"
                  onClick={() => handleDeleteServer(server.id)}
                >
                  Deletar
                </Button>
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
