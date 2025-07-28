import axios from 'axios';
import { ApiResponse, PaginatedResponse, User, GameServer, GameItem } from '@/types';

// Configure a base URL da sua API aqui
const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080/api';

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});