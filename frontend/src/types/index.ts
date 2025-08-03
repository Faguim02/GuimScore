import exp from "constants";

export interface User {
  id?: string;
  name: string;
  email: string;
  password: string;
}

export interface ErroMessage {
  title: string;
  message: string;
  status: number;
}

export interface GameServer {
  id: string;
  nameServer: string;
  description?: string;
  userId?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface GameItem {
  id: string;
  key: string;
  value: string | number | boolean;
  gameServerId: string;
  createdAt: string;
  updatedAt: string;
}

export interface ApiResponse<T> {
  data: T;
  message?: string;
  success: boolean;
}

export interface PaginatedResponse<T> {
  data: T[];
  total: number;
  page: number;
  limit: number;
}
