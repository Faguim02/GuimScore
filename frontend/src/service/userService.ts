import { api } from "@/lib/api";
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
}
