import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // hoặc '0.0.0.0'
    port: 5173, // Bạn có thể thay đổi cổng nếu cần
  },
})
