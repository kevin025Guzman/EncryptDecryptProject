import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalyzer {
    private static final char LETRA_FRECUENTE = 'e'; // Letra más común en español

    public String analizar(String texto) {
        Map<Character, Integer> frecuencia = new HashMap<>(); // Mapa para contar frecuencias
        for (char caracter : texto.toCharArray()) { // Contar frecuencias de cada carácter
            frecuencia.put(caracter, frecuencia.getOrDefault(caracter, 0) + 1);
        }

        // Encontrar la letra más frecuente en el texto
        char masFrecuente = texto.chars()
                .filter(c -> Character.isLetter(c))
                .mapToObj(c -> (char) c)
                .max((a, b) -> frecuencia.get(a) - frecuencia.get(b))
                .orElse(' ');

        int desplazamiento = masFrecuente - LETRA_FRECUENTE; // Calcular el desplazamiento
        return new Cipher().descifrar(texto, desplazamiento); // Descifrar con el desplazamiento calculado
    }
}
