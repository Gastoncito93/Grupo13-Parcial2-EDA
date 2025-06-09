package grupo13.parcial2.eda;

import java.util.*;

// CLASE 1: NodoPonderado
public class NodoPonderado {
    private Object valor;
    private Set<ArcoPonderado> arcos;

    public NodoPonderado(Object valor) {
        this.valor = valor;
        this.arcos = new HashSet<>();
    }

    public Object getValor() {
        return valor;
    }

    public Set<ArcoPonderado> getArcos() {
        return arcos;
    }

    public void mostrarArcos() {
        System.out.print("Nodo{" + valor + "} => [ ");
        for (ArcoPonderado arco : arcos) {
            System.out.print(arco + " ");
        }
        System.out.println("]");
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodoPonderado that = (NodoPonderado) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return "Nodo{" + valor + '}';
    }
}
