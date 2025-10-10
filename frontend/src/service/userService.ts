import { api, setupInterceptorsAuth } from "@/lib/api";
import { ErroMessage, User } from "@/types";

export class UserService {
  private api = api;

  async signUp(user: User) {
    const response = await this.api.post<{message: string, name: string}>('/auth/signUp', user);

    if (response.status !== 201) {
      throw new Error('Erro ao criar usuário');
    }

    return response.data;
  }

  async signIn(user: { name: string; password: string }) {
    const response = await this.api.post<{ accessToken: string }>('/auth/signIn', user);

    if (response.status !== 200) {
      throw new Error('Erro ao fazer login');
    }

    return response.data.accessToken;
  }
  async generateApiKey(name: string) {
    setupInterceptorsAuth();
    const response = await this.api.post<string>('/auth/apiKey', { name });

    if (response.status !== 201) {
      throw new Error('Erro ao gerar chave de API');
    }

    console.log(response.data);

    return {
      apiKey: response.data,
      
    };
   
  }

  async getApiKeys() {
    setupInterceptorsAuth();
    const response = await this.api.get<{ id: string; name: string; createdData: string }[]>('/auth/apiKey');

    if (response.status !== 200) {
      throw new Error('Erro ao buscar chaves de API');
    }

    return response.data;
  }

  async deleteApiKey(id: string) {
    setupInterceptorsAuth();
    const response = await this.api.delete<{ message: string }>(`/auth/apiKey/${id}`);

    if (response.status !== 200) {
      throw new Error('Erro ao deletar chave de API');
    }

    return 200;
  }
}
