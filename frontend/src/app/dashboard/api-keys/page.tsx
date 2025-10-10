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
//import { ApiKey, GameServer } from '@/types'
import { UserService } from '@/service/userService'

const AuthService = new UserService();

export default function ApiKeys() {

    const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false)
    const [isCreatedKey, setIsCreatedKey] = useState('')
    const [nameKey, setNameKey] = useState('')
    const [copyMessage, setCopyMessage] = useState('Copiar')

    const queryClient = useQueryClient()

    const { data: apiKeys, isLoading, isError, error } = useQuery({
        queryKey: ['apiKeys'],
        queryFn: () => AuthService.getApiKeys(),
    })

    const createdMutation = useMutation({
        mutationFn: (name: string) => AuthService.generateApiKey(name),
        onSuccess: (response) => {
            queryClient.invalidateQueries({ queryKey: ['apiKeys'] })
            setIsCreatedKey(response.apiKey)
        }
    })

    const deleteMutation = useMutation({
        mutationFn: (id: string) => AuthService.deleteApiKey(id),
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['apiKeys'] })
        }
    })

    const handleGenerateKey = () => {
        createdMutation.mutate(nameKey)
    }

    const handleDeleteKey = (id: string) => {
        if (confirm('Tem certeza que deseja deletar essa chave?')) {
            deleteMutation.mutate(id)
        }
    }

  return (
    <div>
        <h1 className="text-2xl font-bold mb-4">API Keys</h1>
        <Card>
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <div>
                <CardTitle>Gerencie suas APIs Keys</CardTitle>
                <CardDescription>Gerar e gerenciar suas chaves de API para acessar nossos serviços</CardDescription>
            </div>

            <Dialog open={isCreateDialogOpen} onOpenChange={()=> {
                setIsCreateDialogOpen(!isCreateDialogOpen)
                setIsCreatedKey('')
                createdMutation.reset()
            }}>
                <DialogTrigger asChild>
                    <Button className='bg-brand-purple-100 hover:bg-brand-purple-200'>Criar chave</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-[425px]">
                    <DialogHeader>
                        <DialogTitle>Criar chave</DialogTitle>
                        <DialogDescription>Insira um nome para a chave.</DialogDescription>
                    </DialogHeader>
                    {isCreatedKey.length === 0 && (
                        <>
                            <div className="grid gap-4 py-4">
                                <div className="grid grid-cols-4 items-center gap-4">
                                    <label htmlFor="name" className="text-right">Nome</label>
                                    <Input id="name" className="col-span-3" onChange={(e) => setNameKey(e.target.value)}/>
                                </div>
                            </div>
                            <DialogFooter>
                                <Button type="submit" onClick={handleGenerateKey}>Criar</Button>
                            </DialogFooter>
                        </>
                    )}

                    {createdMutation.isPending && (
                        <div className="mt-4 flex items-center space-x-2">
                            <Loader2 className="animate-spin" />
                            <span>Gerando chave...</span>
                        </div>
                    )}
                    {createdMutation.isError && (
                        <div className="mt-4 text-red-500">
                            Ocorreu um erro ao gerar a chave. Tente novamente.
                        </div>
                    )}

                    {createdMutation.isSuccess && (
                        <div className="flex flex-col leading-tight">
                            <span className="font-medium">Chave criada com sucesso!</span>
                            <span className="text-xs text-muted-foreground">Copie e guarde em local seguro, você não poderá ver essa chave novamente.</span>
                            <code className="bg-muted/50 p-2 rounded break-all">{isCreatedKey}</code>
                            {/* botão de copiar */}
                            <Button className="w-24" onClick={() => {
                                navigator.clipboard.writeText(isCreatedKey)
                                setCopyMessage('Copiado!')
                            }}>
                                {copyMessage}
                            </Button>
                        </div>
                    )}
                </DialogContent>
            </Dialog>
          </CardHeader>
          <CardContent>
            <div className='border rounded-md'>
                <table className="w-full text-sm">
                <thead className='bg-muted/50'>
                    <tr className='border-b'>
                        <th className="p-3 text-left font-medium">Nome</th>
                        <th className="p-3 text-left font-medium">Chave</th>
                        <th className="p-3 text-left font-medium">Data</th>
                        <th className="p-3 text-left font-medium">Ações</th>
                    </tr>
                </thead>
                    {apiKeys === undefined && (
                        <div className="p-4 text-center text-sm text-muted-foreground">Nenhuma chave de API encontrada. Crie uma nova chave para começar a usar nossos serviços.</div>
                    )}
                    {isLoading && (
                        <div className="p-4 text-center">
                            <Loader2 className="mx-auto mb-2 h-4 w-4 animate-spin" />
                            Carregando chaves de API...
                        </div>
                    )}
                    {isError && (
                        <div className="p-4 text-center text-sm text-red-500">Ocorreu um erro ao carregar as chaves de API. Tente novamente mais tarde.</div>
                    )}

                    {apiKeys && apiKeys.length === 0 && (
                        <div className="p-4 text-center text-sm text-muted-foreground">Nenhuma chave de API encontrada. Crie uma nova chave para commencar a usar nossos serviços.</div>
                    )}

                    {apiKeys && apiKeys.length > 0 && apiKeys.map((key) => (
                        <>
                            
                            <tbody>
                                <tr className="border-b">
                                    <td className="p-3">{key.name}</td>
                                    <td className="font-mono">***********</td>
                                    <td className="p-3">{key.createdData}</td>
                                    <td className="p-3">
                                        <Dialog>
                                            <DialogTrigger className="bg-red-500 hover:bg-red-600 text-white rounded-md px-4 py-2" onClick={() => handleDeleteKey(key.id)}>Remover</DialogTrigger>
                                        </Dialog>
                                    </td>
                                </tr>
                            </tbody>
                        </>
                    ))}

                    
                </table>
            </div>
          </CardContent>
        </Card>
    </div>
  )
}
