import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Klasse für Schüler und deren Noten
    static class Schueler {
        String name;
        double note;

        // Konstruktor
        public Schueler(String name, double note) {
            this.name = name;
            this.note = note;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Schüler Liste
        ArrayList<Schueler> schuelerArrayList = new ArrayList<>();
        boolean weiter = true;

        // Notenverwaltung
        System.out.println("Notenverwaltungssystem");

        while (weiter) {

            System.out.print("Möchtest du einen Schüler hinzufügen? (j/n): ");
            char weitereEingabe = scanner.nextLine().charAt(0);
            if (weitereEingabe == 'n' || weitereEingabe == 'N') {
                weiter = false;
            } else {

                System.out.print("Gib den Namen des Schülers ein: ");
                String name = scanner.nextLine();

                System.out.print("Gib die Note des Schülers ein: ");
                double note = scanner.nextDouble();

                scanner.nextLine();

                // Einen neuen Schüler zur Liste hinzufügen
                Schueler schueler = new Schueler(name, note);
                schuelerArrayList.add(schueler);
            }
        }


        // Durchschnitt berechnen
        if (!schuelerArrayList.isEmpty()) {
            double summe = 0;
            System.out.println("\nSchülerliste und Noten:");
            for (Schueler s : schuelerArrayList) {
                if (s.name.length() < 1) {
                    System.out.println("Name fehlt, Durchschnitt ist nicht berechenbar!");
                    break;
                }
                System.out.println(s.name + ": " + s.note);
                summe += s.note;
            }

            double durchschnitt = summe / schuelerArrayList.size();
            System.out.printf("\nDurchschnittsnote der Klasse: " + durchschnitt + "\n");
        } else {
            System.out.println("Keine Schülerdaten vorhanden.");
        }
        System.out.println("Programm beendet.");
        scanner.close();
    }
}