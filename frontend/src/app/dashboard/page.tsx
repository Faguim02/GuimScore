"use client"

import Link from 'next/link'
import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { Server, MoreVertical, Loader2 } from "lucide-react"
import { GameServerService } from '@/service/gameServerService'
import { GameServer } from '@/types'

// Instanciar o serviço fora do componente para evitar recriação a cada render
const gameServerService = new GameServerService();

export default function Dashboard() {
  const queryClient = useQueryClient();

  const [openDropdownId, setOpenDropdownId] = useState<string | null>(null)
  const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false)
  const [formError, setFormError] = useState<string | null>(null)
  const [deletingId, setDeletingId] = useState<string | null>(null);
  const [newServer, setNewServer] = useState({
    nameServer: '',
    description: '',
  })

  // Busca os dados usando React Query
  const { data: gameServers, isLoading, isError, error } = useQuery<GameServer[], Error>({
    queryKey: ['gameServers'],
    queryFn: () => gameServerService.getGameServers(),
  });

  // Mutação para criar o servidor
  const createMutation = useMutation({
    mutationFn: (newServerData: { nameServer: string; description?: string }) => 
      gameServerService.createGameServer(newServerData),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['gameServers'] });
      setIsCreateDialogOpen(false);
      setNewServer({ nameServer: '', description: '' });
      setFormError(null);
    },
    onError: (err) => {
      setFormError('Erro ao criar o servidor. Tente novamente.');
      console.error(err);
    }
  });

  // Mutação para apagar o servidor
  const deleteMutation = useMutation({
    mutationFn: (id: string) => gameServerService.deleteGameServer(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['gameServers'] });
    },
    onSettled: () => {
      setDeletingId(null); // Limpa o ID de deleção independentemente do resultado
    }
  });

  const handleCreateServer = () => {
    if (!newServer.nameServer) {
      setFormError('O nome do servidor é obrigatório.');
      return;
    }
    createMutation.mutate(newServer);
  }

  const handleDeleteServer = (id: string) => {
        
    setDeletingId(id);
    deleteMutation.mutate(id);
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

                {formError && <p className="text-red-500 text-sm text-center mt-4">{formError}</p>}
              </div>
            </div>
            <DialogFooter>
              <Button variant="outline" onClick={() => setIsCreateDialogOpen(false)}>
                Cancelar
              </Button>
              <Button onClick={handleCreateServer} disabled={createMutation.isPending || !newServer.nameServer}>
                {createMutation.isPending ? <Loader2 className="mr-2 h-4 w-4 animate-spin" /> : 'Criar'}
              </Button>
            </DialogFooter>
          </DialogContent>
        </Dialog>
      </div>

      {isLoading && (
        <div className="flex justify-center items-center py-12 col-span-full">
          <Loader2 className="h-8 w-8 animate-spin text-brand-purple-100" />
          <p className="ml-4 text-gray-600">Carregando servidores...</p>
        </div>
      )}

      {isError && (
        <div className="text-center py-12 col-span-full bg-red-50 text-red-700 rounded-lg">
          <h3 className="text-lg font-medium mb-2">Ocorreu um erro</h3>
          <p>{error?.message || 'Não foi possível carregar os servidores de jogo.'}</p>
        </div>
      )}

      {!isLoading && !isError && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {gameServers?.map((server) => (
            <Card key={server.id} className="hover:shadow-lg transition-shadow">
              <CardHeader className="flex flex-row items-center justify-between">
                <div className="flex items-center gap-2">
                  <Server className="h-6 w-6 text-gray-500" />
                  <CardTitle className="text-xl">{server.nameServer}</CardTitle>
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
                        className="block w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-gray-100 disabled:opacity-50"
                        onClick={() => handleDeleteServer(server.id)}
                        disabled={deletingId === server.id}
                      >
                        {deletingId === server.id ? (
                          <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                        ) : (
                          'Apagar'
                        )}
                      </button>
                    </div>
                  )}
                </div>
              </CardHeader>
              <CardContent>
                <CardDescription>{server.description || 'Sem descrição'}</CardDescription>
                <div className="space-y-2 text-sm text-gray-600 mt-4">
                  <p>ID: {server.id}</p>
                  {server.createdAt && <p>Criado em: {new Date(server.createdAt).toLocaleDateString('pt-BR')}</p>}
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
          
          {gameServers?.length === 0 && (
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
      )}
    </>
  )
}
