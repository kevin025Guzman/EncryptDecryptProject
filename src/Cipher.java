public class Cipher {
    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String cifrar(String texto, int clave) {
        StringBuilder resultado = new StringBuilder(); // Cadena para almacenar el resultado
        int tamanoAlfabeto = ALFABETO.length(); // Tamaño del alfabeto para el cálculo circular

        for (char caracter : texto.toCharArray()) { // Iterar por cada carácter del texto
            int index = ALFABETO.indexOf(caracter); // Buscar la posición del carácter en el alfabeto
            if (index != -1) { // Si el carácter está en el alfabeto
                int nuevoIndex = (index + clave) % tamanoAlfabeto; // Calcular nueva posición
                if (nuevoIndex < 0) nuevoIndex += tamanoAlfabeto; // Ajustar para claves negativas
                resultado.append(ALFABETO.charAt(nuevoIndex)); // Añadir carácter cifrado
            } else {
                resultado.append(caracter); // Si no está en el alfabeto, dejar el carácter igual
            }
        }
        return resultado.toString(); // Devolver el texto cifrado
    }

    public String descifrar(String texto, int clave) {
        return cifrar(texto, -clave); // Descifrar es igual que cifrar con clave negativa
    }
}
