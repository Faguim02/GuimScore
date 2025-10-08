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

export default function Dashboarda() {

    const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false)

  return (
    <div>
        <h1 className="text-2xl font-bold mb-4">API Keys</h1>
        <Card>
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <div>
                <CardTitle>Gerencie suas APIs Keys</CardTitle>
                <CardDescription>Gerar e gerenciar suas chaves de API para acessar nossos serviços</CardDescription>
            </div>

            <Dialog open={isCreateDialogOpen} onOpenChange={setIsCreateDialogOpen}>
                <DialogTrigger asChild>
                    <Button className='bg-brand-purple-100 hover:bg-brand-purple-200'>Criar chave</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-[425px]">
                    <DialogHeader>
                        <DialogTitle>Criar chave</DialogTitle>
                        <DialogDescription>Insira um nome para a chave.</DialogDescription>
                    </DialogHeader>
                    <div className="grid gap-4 py-4">
                        <div className="grid grid-cols-4 items-center gap-4">
                            <label htmlFor="name" className="text-right">Nome</label>
                            <Input id="name" className="col-span-3" />
                        </div>
                    </div>
                    <DialogFooter>
                        <Button type="submit">Criar</Button>
                    </DialogFooter>
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
                    <tbody>
                        <tr className="border-b">
                            <td className="p-3">Chave Padrão</td>
                            <td className="font-mono">***********</td>
                            <td className="p-3">2024-06-10</td>
                            <td className="p-3">
                                <Dialog>
                                    <DialogTrigger className="bg-red-500 hover:bg-red-600 text-white rounded-md px-4 py-2">Remover</DialogTrigger>
                                </Dialog>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
          </CardContent>
        </Card>
    </div>
  )
}
