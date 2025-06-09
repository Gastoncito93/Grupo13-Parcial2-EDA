package grupo13.parcial2.eda;

public class DijkstraMatriz {

    public static int[] dijkstra(int[][] C, int origen) {
        int n = C.length;
        int[] D = new int[n]; // distancias mínimas
        boolean[] visitado = new boolean[n];

        // Inicializar D[] con infinito (999), y origen con 0
        for (int i = 0; i < n; i++) {
            D[i] = 999;
            visitado[i] = false;
        }
        D[origen] = 0;

        for (int i = 0; i < n - 1; i++) {
            // Buscar el nodo no visitado con menor D[]
            int minDist = 999;
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!visitado[j] && D[j] < minDist) {
                    minDist = D[j];
                    v = j;
                }
            }

            if (v == -1) break; // todos los alcanzables ya están visitados

            visitado[v] = true;

            // Actualizar distancias a los vecinos de v
            for (int z = 0; z < n; z++) {
                if (C[v][z] != 999 && !visitado[z]) {
                    if (D[z] > D[v] + C[v][z]) {
                        D[z] = D[v] + C[v][z];
                    }
                }
            }
        }

        return D;
    }
}
