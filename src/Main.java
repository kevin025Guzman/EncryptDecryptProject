public class Main {
    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        FileManager fileManager = new FileManager();
        Validator validator = new Validator();
        BruteForce bruteForce = new BruteForce();
        StatisticalAnalyzer statisticalAnalyzer = new StatisticalAnalyzer();

        UserInterface ui = new UserInterface(cipher, fileManager, validator, bruteForce, statisticalAnalyzer);
        ui.crearInterfaz();
    }
}
