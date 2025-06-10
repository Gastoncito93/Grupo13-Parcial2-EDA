/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grupo13.parcial2.eda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Grupo13Parcial2EDA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        GrafoPonderadoListaAd grafo = new GrafoPonderadoListaAd();
        Map<String, Actividad> actividades = new HashMap<>();

        while (true) {
            System.out.println("\n===== FESTIVAL CULTURAL - MENÚ =====");
            System.out.println("1. Agregar nueva actividad");
            System.out.println("2. Crear dependencia entre actividades");
            System.out.println("3. Consultar si se puede hacer una actividad (previas requeridas)");
            System.out.println("4. Mostrar actividades que dependen de una dada");
            System.out.println("5. Mostrar actividades necesarias para hacer una actividad");
            System.out.println("6. Mostrar grafo completo (lugares y tiempos)");
            System.out.println("7. Consultar si se llega a tiempo entre dos actividades");
            System.out.println("8. Crear camino entre lugares con tiempo de traslado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int op = Integer.parseInt(sc.nextLine());
            if (op == 0) break;

            try {
                switch (op) {
                    case 1 -> {
                        System.out.print("Nombre de la actividad: ");
                        String nombre = sc.nextLine();
                        System.out.print("Lugar (ej. Carpa A): ");
                        String lugar = sc.nextLine();
                        System.out.print("Hora inicio (HH:MM): ");
                        String hora = sc.nextLine();
                        System.out.print("Duración en minutos: ");
                        int duracion = Integer.parseInt(sc.nextLine());
                        Actividad a = new Actividad(nombre, lugar, hora, duracion);
                        actividades.put(nombre, a);
                        grafo.agregarVertice(lugar);
                        System.out.println("Actividad agregada.");
                    }
                    case 2 -> {
                        System.out.print("Actividad base: ");
                        String base = sc.nextLine();
                        System.out.print("Actividad que depende: ");
                        String dependiente = sc.nextLine();
                        if (actividades.containsKey(base) && actividades.containsKey(dependiente)) {
                            actividades.get(dependiente).agregarRequisito(base);
                            System.out.println("Dependencia registrada.");
                        } else {
                            System.out.println("Error: actividades no encontradas.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Actividad: ");
                        String act = sc.nextLine();
                        if (actividades.containsKey(act)) {
                            List<String> reqs = actividades.get(act).getRequisitos();
                            if (reqs.isEmpty()) System.out.println("No tiene requisitos.");
                            else System.out.println("Debe hacer antes: " + reqs);
                        } else System.out.println("Actividad no encontrada.");
                    }
                    case 4 -> {
                        System.out.print("Actividad base: ");
                        String act = sc.nextLine();
                        for (Actividad a : actividades.values()) {
                            if (a.getRequisitos().contains(act)) {
                                System.out.println(a.getNombre());
                            }
                        }
                    }
                    case 5 -> {
                        System.out.print("Actividad: ");
                        String act = sc.nextLine();
                        if (actividades.containsKey(act)) {
                            System.out.println("Requiere haber hecho: " + actividades.get(act).getRequisitos());
                        }
                    }
                    case 6 -> grafo.mostrarGrafo();

                    case 7 -> {
                        System.out.print("Actividad de origen: ");
                        String desde = sc.nextLine();
                        System.out.print("Actividad de destino: ");
                        String hasta = sc.nextLine();

                        if (actividades.containsKey(desde) && actividades.containsKey(hasta)) {
                            Actividad a1 = actividades.get(desde);
                            Actividad a2 = actividades.get(hasta);

                            int finA1 = a1.getHoraInicioEnMinutos() + a1.getDuracion();
                            int inicioA2 = a2.getHoraInicioEnMinutos();

                            int tiempoTraslado = grafo.dijkstra(a1.getLugar(), a2.getLugar());

                            System.out.println("Tiempo disponible para trasladarse: " + (inicioA2 - finA1));
                            System.out.println("Tiempo de traslado calculado: " + tiempoTraslado);

                            if (tiempoTraslado != -1 && tiempoTraslado <= (inicioA2 - finA1)) {
                                System.out.println("✅ Se puede asistir a tiempo a la siguiente actividad.");
                            } else {
                                System.out.println("❌ No se llega a tiempo.");
                            }
                        } else {
                            System.out.println("Actividades no encontradas.");
                        }
                    }
                    case 8 -> {
                        System.out.print("Lugar origen: ");
                        String origen = sc.nextLine();
                        System.out.print("Lugar destino: ");
                        String destino = sc.nextLine();
                        System.out.print("Tiempo de traslado (minutos): ");
                        int tiempo = Integer.parseInt(sc.nextLine());
                        grafo.agregarVertice(origen);
                        grafo.agregarVertice(destino);
                        grafo.agregarArco(origen, destino, tiempo);
                        System.out.println("Camino registrado.");
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: entrada inválida o acción no permitida.");
            }
        }
        System.out.println("Fin del programa.");
    }
    }
    

