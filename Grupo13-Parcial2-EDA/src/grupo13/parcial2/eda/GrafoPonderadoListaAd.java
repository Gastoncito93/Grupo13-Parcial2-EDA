/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo13.parcial2.eda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrafoPonderadoListaAd {
    private Set<NodoPonderado> nodos;

    public GrafoPonderadoListaAd() {
        nodos = new HashSet<>();
    }

    public boolean agregarVertice(Object valor) {
        return nodos.add(new NodoPonderado(valor));
    }

    public boolean agregarArco(Object origen, Object destino, int peso) {
        NodoPonderado nodoOrigen = buscarNodo(origen);
        NodoPonderado nodoDestino = buscarNodo(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.getArcos().add(new ArcoPonderado(nodoDestino, peso));
            return true;
        }
        return false;
    }

    private NodoPonderado buscarNodo(Object valor) {
        for (NodoPonderado n : nodos) {
            if (n.getValor().equals(valor)) {
                return n;
            }
        }
        return null;
    }

    public void mostrarGrafo() {
        for (NodoPonderado n : nodos) {
            n.mostrarArcos();
        }
    }

    public Set<NodoPonderado> getNodos() {
        return nodos;
    }

    // DIJKSTRA EN ESTILO DE LA MATERIA (SIN PRIORITYQUEUE)
    public int dijkstra(Object origen, Object destino) {
        int n = nodos.size();
        List<NodoPonderado> lista = new ArrayList<>(nodos);
        int[][] C = new int[n][n];
        int[] D = new int[n];
        boolean[] visitado = new boolean[n];

        // Inicializar matriz con infinito (999)
        for (int i = 0; i < n; i++) {
            Arrays.fill(C[i], 999);
        }

        for (int i = 0; i < n; i++) {
            NodoPonderado nodo = lista.get(i);
            for (ArcoPonderado arco : nodo.getArcos()) {
                int j = lista.indexOf(arco.getDestino());
                C[i][j] = arco.getPeso();
            }
        }

        // Inicializar D[] y visitado[]
        Arrays.fill(D, 999);
        int posOrigen = -1;
        int posDestino = -1;

        for (int i = 0; i < n; i++) {
            if (lista.get(i).getValor().equals(origen)) posOrigen = i;
            if (lista.get(i).getValor().equals(destino)) posDestino = i;
        }

        if (posOrigen == -1 || posDestino == -1) return -1;

        D[posOrigen] = 0;

        for (int i = 0; i < n - 1; i++) {
            int min = 999;
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!visitado[j] && D[j] < min) {
                    min = D[j];
                    v = j;
                }
            }

            if (v == -1) break;
            visitado[v] = true;

            for (int z = 0; z < n; z++) {
                if (C[v][z] != 999 && !visitado[z]) {
                    if (D[z] > D[v] + C[v][z]) {
                        D[z] = D[v] + C[v][z];
                    }
                }
            }
        }

        return D[posDestino] == 999 ? -1 : D[posDestino];
    }
}
