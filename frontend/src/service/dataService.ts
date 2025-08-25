import { api, setupInterceptorsAuth } from "@/lib/api";
import { DataItem } from "@/types";

export class DataService {

    private api = api;

    async findAllDataItems(gameServerId: string) {
        setupInterceptorsAuth();
        const response = await this.api.get<DataItem[]>(`/data?game-id=${gameServerId}`);

        if (response.status !== 200) {
            throw new Error('Erro ao buscar dados');
        }

        return response.data;
    }

    async createDataItem(body: DataItem): Promise<string> {
        
        setupInterceptorsAuth();
        const response = await this.api.post<string>(`/data?game-id=${body.gameServerId}`, body);

        if (response.status !== 201) {
            throw new Error('Erro ao criar dado');
        }

        return response.data;
    }

}