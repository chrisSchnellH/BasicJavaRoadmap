import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.*;

public class LargestFilesFinder {

    // Maximum Anzahl der größten Dateien/Ordner
    private static final int MAX_SIZE = 10;

    /* Statt die gesamte Map für jeden Thread zu sperren (wie bei Hashtable), teilt ConcurrentHashMap die Daten in mehrere Segmente auf,
    die separat gesperrt werden. Das bedeutet, dass mehrere Threads gleichzeitig auf verschiedene Segmente zugreifen und diese ändern können,
    was die Performance verbessert.
    */
    private static final Map<Path, Long> fileSizes = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Path startPath = Paths.get("E:\\Downloads"); // Eigener Pfad

        ExecutorService executorService = Executors.newCachedThreadPool();

        // Suche nach Dateien und Ordnern
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Überprüfe, ob die Datei eine Größe hat
                long size = attrs.size();
                executorService.submit(() -> fileSizes.put(file, size));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // Überprüfe, ob der Ordner eine Größe hat
                long dirSize = calculateDirectorySize(dir);
                executorService.submit(() -> fileSizes.put(dir, dirSize));
                return FileVisitResult.CONTINUE;
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        // Finde die 10 größten Dateien und Ordner
        List<Map.Entry<Path, Long>> largestFiles = fileSizes.entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(MAX_SIZE)
                .toList();

        // Ausgabe der Ergebnisse
        System.out.println("Die 10 größten Dateien/Ordner:");
        for (Map.Entry<Path, Long> entry : largestFiles) {
            System.out.printf("%s: %d Bytes%n", entry.getKey(), entry.getValue());
        }
    }

    private static long calculateDirectorySize(Path dir) {
        try {
            // Berechne die Größe des Verzeichnisses durch die Summe der Größen der Dateien in diesem Verzeichnis
            return Files.list(dir)
                    .mapToLong(path -> {
                        try {
                            return Files.isDirectory(path) ? calculateDirectorySize(path) : Files.size(path);
                        } catch (IOException e) {
                            return 0;
                        }
                    })
                    .sum();
        } catch (IOException e) {
            return 0;
        }
    }
}
