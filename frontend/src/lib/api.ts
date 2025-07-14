import axios from 'axios';
import { ApiResponse, PaginatedResponse, User, GameServer, GameItem } from '@/types';

// Configure a base URL da sua API aqui
const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:3001/api';

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para adicionar token de autenticação se necessário
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Interceptor para tratar erros globalmente
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API Error:', error);
    return Promise.reject(error);
  }
);

// API functions for Users
export const userApi = {
  getAll: () => api.get<PaginatedResponse<User>>('/users'),
  getById: (id: string) => api.get<ApiResponse<User>>(`/users/${id}`),
  create: (data: Omit<User, 'id' | 'createdAt' | 'updatedAt'>) => 
    api.post<ApiResponse<User>>('/users', data),
  update: (id: string, data: Partial<User>) => 
    api.put<ApiResponse<User>>(`/users/${id}`, data),
  delete: (id: string) => api.delete<ApiResponse<void>>(`/users/${id}`),
};

// API functions for Game Servers
export const gameServerApi = {
  getAll: (userId?: string) => {
    const params = userId ? { userId } : {};
    return api.get<PaginatedResponse<GameServer>>('/game-servers', { params });
  },
  getById: (id: string) => api.get<ApiResponse<GameServer>>(`/game-servers/${id}`),
  create: (data: Omit<GameServer, 'id' | 'createdAt' | 'updatedAt'>) => 
    api.post<ApiResponse<GameServer>>('/game-servers', data),
  update: (id: string, data: Partial<GameServer>) => 
    api.put<ApiResponse<GameServer>>(`/game-servers/${id}`, data),
  delete: (id: string) => api.delete<ApiResponse<void>>(`/game-servers/${id}`),
};

// API functions for Game Items
export const gameItemApi = {
  getAll: (gameServerId: string) => 
    api.get<PaginatedResponse<GameItem>>(`/game-servers/${gameServerId}/items`),
  getById: (gameServerId: string, id: string) => 
    api.get<ApiResponse<GameItem>>(`/game-servers/${gameServerId}/items/${id}`),
  create: (gameServerId: string, data: Omit<GameItem, 'id' | 'gameServerId' | 'createdAt' | 'updatedAt'>) => 
    api.post<ApiResponse<GameItem>>(`/game-servers/${gameServerId}/items`, data),
  update: (gameServerId: string, id: string, data: Partial<GameItem>) => 
    api.put<ApiResponse<GameItem>>(`/game-servers/${gameServerId}/items/${id}`, data),
  delete: (gameServerId: string, id: string) => 
    api.delete<ApiResponse<void>>(`/game-servers/${gameServerId}/items/${id}`),
  getByKey: (gameServerId: string, key: string) => 
    api.get<ApiResponse<GameItem>>(`/game-servers/${gameServerId}/items/key/${key}`),
};

// Auth functions (se você tiver autenticação)
export const authApi = {
  login: (credentials: { email: string; password: string }) => 
    api.post<ApiResponse<{ user: User; token: string }>>('/auth/login', credentials),
  register: (userData: { username: string; email: string; password: string }) => 
    api.post<ApiResponse<{ user: User; token: string }>>('/auth/register', userData),
  logout: () => api.post<ApiResponse<void>>('/auth/logout'),
};
