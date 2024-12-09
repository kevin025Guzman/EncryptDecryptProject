import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private final Cipher cipher;
    private final FileManager fileManager;
    private final Validator validator;
    private final BruteForce bruteForce;
    private final StatisticalAnalyzer statisticalAnalyzer;

    public UserInterface(Cipher cipher, FileManager fileManager, Validator validator, BruteForce bruteForce, StatisticalAnalyzer statisticalAnalyzer) {
        this.cipher = cipher;
        this.fileManager = fileManager;
        this.validator = validator;
        this.bruteForce = bruteForce;
        this.statisticalAnalyzer = statisticalAnalyzer;
    }

    public void crearInterfaz() {
        // Crear el marco principal
        JFrame frame = new JFrame("Cifrado César - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Cifrado César", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);

        // Botones
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnCifrar = crearBoton("Cifrar Archivo", e -> realizarCifrado());
        JButton btnDescifrar = crearBoton("Descifrar Archivo", e -> realizarDescifrado());
        JButton btnFuerzaBruta = crearBoton("Fuerza Bruta", e -> realizarFuerzaBruta());
        JButton btnStatistical = crearBoton("Análisis Estadístico", e -> realizarAnalisisEstadistico());

        buttonPanel.add(btnCifrar);
        buttonPanel.add(btnDescifrar);
        buttonPanel.add(btnFuerzaBruta);
        buttonPanel.add(btnStatistical);

        // Añadir elementos al marco
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JButton crearBoton(String texto, java.awt.event.ActionListener action) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(action);
        return button;
    }

    private void realizarCifrado() {
        String rutaEntrada = JOptionPane.showInputDialog("Introduce la ruta del archivo de entrada:");
        String rutaSalida = JOptionPane.showInputDialog("Introduce la ruta del archivo de salida:");
        String claveStr = JOptionPane.showInputDialog("Introduce la clave de cifrado (número entero):");

        try {
            int clave = Integer.parseInt(claveStr);
            if (validator.validarExistenciaArchivo(rutaEntrada) && validator.validarNombreArchivo(rutaSalida)) {
                String texto = fileManager.leerArchivo(rutaEntrada);
                String textoCifrado = cipher.cifrar(texto, clave);
                fileManager.escribirArchivo(rutaSalida, textoCifrado);
                JOptionPane.showMessageDialog(null, "Archivo cifrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la validación de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarDescifrado() {
        String rutaEntrada = JOptionPane.showInputDialog("Introduce la ruta del archivo de entrada:");
        String rutaSalida = JOptionPane.showInputDialog("Introduce la ruta del archivo de salida:");
        String claveStr = JOptionPane.showInputDialog("Introduce la clave de descifrado (número entero):");

        try {
            int clave = Integer.parseInt(claveStr);
            if (validator.validarExistenciaArchivo(rutaEntrada) && validator.validarNombreArchivo(rutaSalida)) {
                String texto = fileManager.leerArchivo(rutaEntrada);
                String textoDescifrado = cipher.descifrar(texto, clave);
                fileManager.escribirArchivo(rutaSalida, textoDescifrado);
                JOptionPane.showMessageDialog(null, "Archivo descifrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la validación de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarFuerzaBruta() {
        String rutaEntrada = JOptionPane.showInputDialog("Introduce la ruta del archivo de entrada:");
        String rutaSalida = JOptionPane.showInputDialog("Introduce la ruta del archivo de salida:");

        try {
            if (validator.validarExistenciaArchivo(rutaEntrada) && validator.validarNombreArchivo(rutaSalida)) {
                String texto = fileManager.leerArchivo(rutaEntrada);
                String resultados = bruteForce.crack(texto);
                fileManager.escribirArchivo(rutaSalida, resultados);
                JOptionPane.showMessageDialog(null, "Fuerza bruta completada. Resultados guardados.");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la validación de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarAnalisisEstadistico() {
        String rutaEntrada = JOptionPane.showInputDialog("Introduce la ruta del archivo de entrada:");
        String rutaSalida = JOptionPane.showInputDialog("Introduce la ruta del archivo de salida:");

        try {
            if (validator.validarExistenciaArchivo(rutaEntrada) && validator.validarNombreArchivo(rutaSalida)) {
                String texto = fileManager.leerArchivo(rutaEntrada);
                String resultado = statisticalAnalyzer.analizar(texto);
                fileManager.escribirArchivo(rutaSalida, resultado);
                JOptionPane.showMessageDialog(null, "Análisis estadístico completado. Resultados guardados.");
            } else {
                JOptionPane.showMessageDialog(null, "Error en la validación de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
