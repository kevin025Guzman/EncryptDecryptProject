public class BruteForce {
    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String crack(String texto) {
        System.out.println("=== Fuerza Bruta ===");
        String result = "";
        for (int clave = 1; clave < ALFABETO.length(); clave++) { // Probar todas las claves posibles
            String descifrado = new Cipher().descifrar(texto, clave); // Descifrar con la clave actual
            System.out.println("Clave " + clave + ": " + descifrado);
            result = descifrado;// Mostrar el texto descifrado
        }

        return result;
    }
}
