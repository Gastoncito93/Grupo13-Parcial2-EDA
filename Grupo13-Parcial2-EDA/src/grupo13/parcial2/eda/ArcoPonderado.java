package grupo13.parcial2.eda;

public class ArcoPonderado {
    private NodoPonderado destino;
    private int peso;

    public ArcoPonderado(NodoPonderado destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public NodoPonderado getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return destino.getValor() + " (" + peso + " min)";
    }
}
