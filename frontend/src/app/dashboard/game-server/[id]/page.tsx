"use client"

import { useState } from 'react'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { MoreVertical, PlusCircle } from 'lucide-react'
import { useMutation, useQueryClient, useQuery } from '@tanstack/react-query'
import { DataService } from '@/service/dataService'
import { DataItem } from '@/types'

const fakeGameServerDetails = {
  id: '1',
  name: 'Servidor de Teste 1',
  description: 'Um servidor para testar o jogo X.',
  createdAt: new Date().toISOString(),
}

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
  const gameServerId = params.id;
  const [lists, setLists] = useState(fakeLists) // This will be replaced by react-query later
  const [isAddDataModalOpen, setIsAddDataModalOpen] = useState(false)

  const queryClient = useQueryClient();
  const dataService = new DataService();

  const { data: dataItems, isLoading, isError } = useQuery<DataItem[]>({
    queryKey: ['dataItems', gameServerId],
    queryFn: () => dataService.findAllDataItems(gameServerId),
  });

  const [newData, setNewData] = useState<Partial<DataItem>>({
    nameData: '',
    value: '',
    maxValue: '',
    minValue: '',
  });

  const createDataMutation = useMutation({
    mutationFn: (data: DataItem) => dataService.createDataItem(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['dataItems', gameServerId] });
      setIsAddDataModalOpen(false);
      setNewData({ nameData: '', value: '', maxValue: '', minValue: '' });
    },
    onError: (error) => {
      console.error("Error creating data item:", error);
      // Optionally, show an error message to the user
    },
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setNewData((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmitNewData = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newData.nameData || !newData.value) {
      alert('Chave e Valor são obrigatórios.');
      return;
    }

    const dataToCreate: DataItem = {
      nameData: newData.nameData,
      value: newData.value,
      maxValue: newData.maxValue,
      minValue: newData.minValue,
      gameServerId: gameServerId,
    };

    createDataMutation.mutate(dataToCreate);
  };

  return (
    <div className="space-y-6">
      {/* Game Data Section */}
      <Card>
        <CardHeader className="flex flex-row items-center justify-between">
          <div>
            <CardTitle>Dados do Jogo</CardTitle>
            <CardDescription>Gerencie os dados de chave-valor do seu jogo.</CardDescription>
          </div>
          <Dialog open={isAddDataModalOpen} onOpenChange={setIsAddDataModalOpen}>
            <DialogTrigger asChild>
              <Button size="sm" className="flex items-center gap-1">
                <PlusCircle className="h-4 w-4" />
                Adicionar Dado
              </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
              <DialogHeader>
                <DialogTitle>Adicionar Novo Dado</DialogTitle>
                <DialogDescription>
                  Preencha os campos para adicionar um novo dado ao seu jogo.
                </DialogDescription>
              </DialogHeader>
              <div className="grid gap-4 py-4">
                <div className="grid grid-cols-4 items-center gap-4">
                  <label htmlFor="nameData" className="text-right">
                    Chave
                  </label>
                  <Input id="nameData" value={newData.nameData as string} onChange={handleInputChange} className="col-span-3" />
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                  <label htmlFor="value" className="text-right">
                    Valor
                  </label>
                  <Input id="value" value={newData.value as string} onChange={handleInputChange} className="col-span-3" />
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                  <label htmlFor="maxValue" className="text-right">
                    Valor máximo
                  </label>
                  <Input id="maxValue" value={newData.maxValue as string} onChange={handleInputChange} className="col-span-3" />
                </div>
                <div className="grid grid-cols-4 items-center gap-4">
                  <label htmlFor="minValue" className="text-right">
                    Valor mínimo
                  </label>
                  <Input id="minValue" value={newData.minValue as string} onChange={handleInputChange} className="col-span-3" />
                </div>
              </div>
              <DialogFooter>
                <Button type="submit" onClick={handleSubmitNewData} disabled={createDataMutation.isPending}>
                  {createDataMutation.isPending ? 'Salvando...' : 'Salvar'}
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
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
                {dataItems?.map((data) => (
                  <tr key={data.id} className="border-b">
                    <td className="p-3 font-mono">{data.nameData}</td>
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
