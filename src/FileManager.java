import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    /**
     * Lee un archivo muy grande línea por línea y devuelve su contenido como una sola cadena.
     * @param ruta Ruta del archivo.
     * @return Contenido completo del archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public String leerArchivo(String ruta) throws IOException {
        StringBuilder contenido = new StringBuilder(); // Usar StringBuilder para evitar overhead de concatenaciones
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) { // Leer línea por línea
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n"); // Agregar cada línea al StringBuilder
            }
        }
        return contenido.toString(); // Devolver todo el contenido como una sola cadena
    }

    /**
     * Escribe contenido muy grande en un archivo de manera eficiente.
     * @param ruta Ruta del archivo de salida.
     * @param contenido Contenido a escribir.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void escribirArchivo(String ruta, String contenido) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) { // Escribir de forma eficiente
            bw.write(contenido); // Escribir el contenido completo en el archivo
        }
    }

    /**
     * Lee un archivo muy grande línea por línea, aplicando una operación en cada línea (procesamiento en streaming).
     * @param ruta Ruta del archivo.
     * @param processor Interfaz funcional que define cómo procesar cada línea.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void procesarArchivo(String ruta, LineProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                processor.procesar(linea); // Llamar al método de procesamiento definido por el usuario
            }
        }
    }

    /**
     * Interfaz funcional para definir un procesamiento personalizado por línea.
     */
    @FunctionalInterface
    public interface LineProcessor {
        void procesar(String linea);
    }
}
