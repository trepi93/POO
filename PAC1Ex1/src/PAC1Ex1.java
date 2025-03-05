import java.util.Scanner;

public class PAC1Ex1 {

    public static double calculatePiLeibniz(int n) {
        double pi = 0.0; //Canviem el tipus de boolean a double
        int sign = 1; // Inicialitzem el tipus

        for (int i = 0; i < n; i++) { // Canviem les comes per ;. També iterem fins a n-1 i no fins a n, per complir
                                     // amb la fórmula
            pi += sign / (2.0 * i + 1);
            sign = -sign; // Canviem el signe perquè volem alternar a cada iteració per complir amb la fórmula
        }

        return 4 * pi; // Corregim el return i multipliquem per 4 el pi, seguint la fórmula
    }

    public static void printPiComparison(int n) { // Canviem string per void perquè no retorna res
        double pi = calculatePiLeibniz(n); // Indiquem el tipus de variable i corregim el nom del mètode
        double actualPi = Math.PI; // Canviem float per double i el tipus d'operador, que és d'assignació.

        System.out.println("Comparison between the calculated value of Pi and the actual value of Pi:");
        System.out.println(pi + " (using Leibniz formula)");
        System.out.println(actualPi + " (actual value of Pi)");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Inicialitzem bé la classe Scanner
        int n; // Inicialitzem correctament la variable n

        do {
            System.out.print("Enter the value of N (only integer positive numbers): ");
            n = scanner.nextInt();

            if (n <= 0) { // Corregim l'operador perquè sinó no concorda amb el missatge
                System.out.println("The value of N must be a positive integer number. Try again.");
            }
        } while (n <= 0); // Afegim el <= perquè el 0 tampoc és positiu

        printPiComparison(n); // Al mètode li faltava un paràmetre n

        scanner.close();
    }
} // Faltava el parèntesi de tancament de la classe
