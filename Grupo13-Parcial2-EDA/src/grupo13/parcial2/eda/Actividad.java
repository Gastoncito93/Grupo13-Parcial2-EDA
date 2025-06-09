package grupo13.parcial2.eda;

import java.util.*;

class Actividad {
    private String nombre;
    private String lugar;
    private String hora;
    private int duracion;
    private List<String> requisitos;

    public Actividad(String nombre, String lugar, String hora, int duracion) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.hora = hora;
        this.duracion = duracion;
        this.requisitos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public String getHora() {
        return hora;
    }

    public int getDuracion() {
        return duracion;
    }

    public List<String> getRequisitos() {
        return requisitos;
    }

    public void agregarRequisito(String r) {
        requisitos.add(r);
    }

    public int getHoraInicioEnMinutos() {
        String[] partes = hora.split(":");
        return Integer.parseInt(partes[0]) * 60 + Integer.parseInt(partes[1]);
    }

    @Override
    public String toString() {
        return nombre + " (" + lugar + " - " + hora + ")";
    }
}
	