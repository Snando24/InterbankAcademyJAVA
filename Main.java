import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            // Por favor, ingrese un número como argumento.
            System.out.println("Please provide a number as an argument.");
            return;
        }

        String rutaArchivo = args[0];
        List<Transaccion> transacciones = leerArchivo(rutaArchivo);

        if (transacciones.isEmpty()) {
            System.out.println("No se encontraron transacciones válidas.");
            return;
        }

        generarReporte(transacciones);
    }

    public static List<Transaccion> leerArchivo(String ruta) {
        List<Transaccion> transacciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean primeraLinea = true;
            while((linea = br.readLine()) != null){
                if(primeraLinea) {
                    primeraLinea = false; // Ignorar la primera línea (encabezado)
                    continue;
                }
                String[] leerTransaccion = linea.split(",");
                if (leerTransaccion.length != 3) continue; // Ignorar líneas mal formadas
                try {
                    int id = Integer.parseInt(leerTransaccion[0].trim());
                    String tipo = leerTransaccion[1].trim();
                    double monto = Double.parseDouble(leerTransaccion[2].trim());
                    if(monto < 0 || (!tipo.equals("Crédito") && !tipo.equals("Débito"))) {
                        System.out.println("Transaccion invalida: " + linea);
                        continue; // Ignorar transacciones inválidas
                    }
                    transacciones.add(new Transaccion(id, tipo, monto));
                } catch (NumberFormatException  e) {
                    System.out.println("Error en formato: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return transacciones;
    }

    public static void generarReporte(List<Transaccion> transacciones){
        double balance = 0;
        int contadorDebitos = 0;
        int contadorCreditos = 0;
        Transaccion transaccionMayor = null;

        for(Transaccion t : transacciones){
            if(t.tipo.equalsIgnoreCase("Crédito")){
                balance += t.monto;
                contadorCreditos++;
            } else if(t.tipo.equalsIgnoreCase("Débito")){
                balance -= t.monto;
                contadorDebitos++;
            }
            if(transaccionMayor == null || t.monto > transaccionMayor.monto){
                transaccionMayor = t;
            }
        }
        System.out.println("Reporte de Transacciones");
        System.out.println("---------------------------------------------");
        System.out.printf("Balance Final: $%.2f%n", balance);
        System.out.printf("Transacción de Mayor Monto: ID %d - %.2f%n", transaccionMayor.id, transaccionMayor.monto);
        System.out.printf("Conteo de Transacciones: Crédito: %d Débito: %d%n", contadorCreditos, contadorDebitos);
    }

    static class Transaccion {
        int id;
        String tipo;
        double monto;

        public Transaccion(int id, String tipo, double monto) {
            this.id = id;
            this.tipo = tipo;
            this.monto = monto;
        }
    }
}