import java.io.File;
import java.util.regex.Pattern;

public class Validator {

    /**
     * Valida si un archivo existe en la ruta especificada.
     *
     * @param ruta Ruta del archivo a verificar.
     * @return true si el archivo existe, de lo contrario false.
     */
    public boolean validarExistenciaArchivo(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists() && archivo.isFile();
    }

    /**
     * Valida si un nombre de archivo es válido.
     *
     * @param nombreArchivo Nombre del archivo a validar.
     * @return true si el nombre del archivo es válido, de lo contrario false.
     */
    public boolean validarNombreArchivo(String nombreArchivo) {
        // Evitar caracteres prohibidos en nombres de archivo (Windows)
        String regex = "^[^<>:\"/\\\\|?*]+$"; // Prohíbe caracteres especiales
        Pattern pattern = Pattern.compile(regex);

        // El nombre no debe estar vacío, ser nulo ni contener caracteres inválidos
        return nombreArchivo != null && !nombreArchivo.trim().isEmpty() && pattern.matcher(nombreArchivo).matches();
    }

    /**
     * Valida si la clave de cifrado es válida.
     *
     * @param clave Clave de cifrado.
     * @return true si la clave es válida, de lo contrario false.
     */
    public boolean validarClave(int clave) {
        // La clave debe ser mayor o igual a 0 para el cifrado César
        return clave >= 0;
    }
}
