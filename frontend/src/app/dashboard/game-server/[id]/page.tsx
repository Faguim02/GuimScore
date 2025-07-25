"use client"

import { useState } from 'react'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { MoreVertical, PlusCircle } from 'lucide-react'

const fakeGameServerDetails = {
  id: '1',
  name: 'Servidor de Teste 1',
  description: 'Um servidor para testar o jogo X.',
  createdAt: new Date().toISOString(),
}

const fakeGameData = [
  {
    id: 'data-1',
    key: 'vida',
    value: {
      current: 80,
      max: 100,
      min: 0,
    },
  },
  {
    id: 'data-2',
    key: 'mana',
    value: {
      current: 50,
      max: 150,
      min: 0,
    },
  },
  {
    id: 'data-3',
    key: 'pontuacao',
    value: 1250,
  },
]

const fakeLists = [
  {
    id: 'list-1',
    name: 'Inventário Inicial',
    items: [
      { id: 'item-1', name: 'Poção Pequena', quantity: 5 },
      { id: 'item-2', name: 'Adaga de Ferro', quantity: 1 },
    ]
  },
  {
    id: 'list-2',
    name: 'Inimigos Raros',
    items: [
      { id: 'enemy-1', name: 'Dragão Dourado', spawnRate: 0.01 },
      { id: 'enemy-2', name: 'Goblin de Diamante', spawnRate: 0.05 },
    ]
  }
]

export default function GameServerManagementPage({ params }: { params: { id: string } }) {
  const [gameData, setGameData] = useState(fakeGameData)
  const [lists, setLists] = useState(fakeLists)

  return (
    <div className="space-y-6">
      {/* Game Data Section */}
      <Card>
        <CardHeader className="flex flex-row items-center justify-between">
          <div>
            <CardTitle>Dados do Jogo</CardTitle>
            <CardDescription>Gerencie os dados de chave-valor do seu jogo.</CardDescription>
          </div>
          <Button size="sm" className="flex items-center gap-1">
            <PlusCircle className="h-4 w-4" />
            Adicionar Dado
          </Button>
        </CardHeader>
        <CardContent>
          <div className="border rounded-md">
            <table className="w-full text-sm">
              <thead className="bg-muted/50">
                <tr className="border-b">
                  <th className="p-3 text-left font-medium">Chave</th>
                  <th className="p-3 text-left font-medium">Valor</th>
                  <th className="p-3 text-left font-medium">Ações</th>
                </tr>
              </thead>
              <tbody>
                {gameData.map((data) => (
                  <tr key={data.id} className="border-b">
                    <td className="p-3 font-mono">{data.key}</td>
                    <td className="p-3">
                      <pre className="bg-gray-100 p-2 rounded-md text-xs">
                        {JSON.stringify(data.value, null, 2)}
                      </pre>
                    </td>
                    <td className="p-3">
                      <Button variant="ghost" size="icon">
                        <MoreVertical className="h-4 w-4" />
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>

      {/* Lists Section */}
      <Card>
        <CardHeader className="flex flex-row items-center justify-between">
          <div>
            <CardTitle>Listas do Jogo</CardTitle>
            <CardDescription>Gerencie as listas de itens, inimigos, etc.</CardDescription>
          </div>
          <Button size="sm" className="flex items-center gap-1">
            <PlusCircle className="h-4 w-4" />
            Adicionar Lista
          </Button>
        </CardHeader>
        <CardContent>
          <div className="border rounded-md">
            <table className="w-full text-sm">
              <thead className="bg-muted/50">
                <tr className="border-b">
                  <th className="p-3 text-left font-medium">Nome da Lista</th>
                  <th className="p-3 text-left font-medium">Nº de Itens</th>
                  <th className="p-3 text-left font-medium">Ações</th>
                </tr>
              </thead>
              <tbody>
                {lists.map((list) => (
                  <tr key={list.id} className="border-b">
                    <td className="p-3 font-medium">{list.name}</td>
                    <td className="p-3">{list.items.length}</td>
                    <td className="p-3">
                      <Button variant="ghost" size="icon">
                        <MoreVertical className="h-4 w-4" />
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
