import { api, setupInterceptorsAuth } from "@/lib/api";
import { GameServer } from "@/types";

export class GameServerService {
  
    private api = api;

    async createGameServer(body:{nameServer: string, description?: string}) {
        setupInterceptorsAuth();
        const response = await this.api.post<GameServer>('/game-server', body);

        if (response.status !== 201) {
            throw new Error('Erro ao criar servidor de jogo');
        }

        return response.data;
    }

    async getGameServers(): Promise<GameServer[]> {
        setupInterceptorsAuth();
        const response = await this.api.get<GameServer[]>('/game-server');

        if (response.status !== 200) {
            throw new Error('Erro ao buscar servidores de jogo');
        }

        return response.data;
    }

    async deleteGameServer(id: string): Promise<void> {
        setupInterceptorsAuth();
        const response = await this.api.delete(`/game-server/${id}`);

        if (response.status !== 200 && response.status !== 204) {
            throw new Error('Erro ao deletar servidor de jogo');
        }
    }

}
