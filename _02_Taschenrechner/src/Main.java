import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean weiterRechnen = true;

        System.out.println("Das ist ein Taschenrechner");

        while (weiterRechnen) {

            System.out.println("Gib die 1. Zahl ein");
            double zahl1 = scanner.nextDouble();

            System.out.println("Gib die 2. Zahl ein");
            double zahl2 = scanner.nextDouble();

            System.out.println("Welche Rechenoperation möchtest du ausführen? (+, -, *, /)");
            char rechenOperation = scanner.next().charAt(0);

            double ergebnis = 0;

            if (rechenOperation == '+') {
                ergebnis = zahl1 + zahl2;
            } else if (rechenOperation == '-') {
                ergebnis = zahl1 - zahl2;
            } else if (rechenOperation == '*') {
                ergebnis = zahl1 * zahl2;
            } else if (rechenOperation == '/') {
                if (zahl2 != 0) {
                    ergebnis = zahl1 / zahl2;
                } else {
                    System.out.println("Fehler: Division durch Null ist nicht möglich!");
                }
            } else {
                System.out.println("Ungültige Operation! Bitte versuche es erneut.");
            }

            System.out.println("Das Ergebnis ist: " + ergebnis);

            System.out.print("Möchtest du eine weitere Berechnung durchführen? (j/n): ");
            char weiterBerechnen = scanner.next().charAt(0);
            if (weiterBerechnen == 'n' || weiterBerechnen == 'N') {
                weiterRechnen = false;
            }
        }
        System.out.println("Programm beendet. Danke fürs Verwenden des Taschenrechners!");
        scanner.close();
    }
}