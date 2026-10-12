package analyzer;

import java.util.ArrayList;

/**
 * Keeps a record of every measured operation so that
 * main menu option 8 ("Display All Results") can show them together.
 */
public class ResultLog {

    private static final ArrayList<String> entries = new ArrayList<>();

    /** Records one result as a formatted line. */
    public static void add(String operation, String algorithm, int steps, long nanos, String detail) {
        entries.add(String.format("%-16s %-18s %8d %12d   %s",
                operation, algorithm, steps, nanos, detail));
    }

    /** Prints everything recorded so far. */
    public static void displayAll() {
        System.out.println("\n=============================================");
        System.out.println("               ALL RESULTS");
        System.out.println("=============================================");

        if (entries.isEmpty()) {
            System.out.println("No results recorded yet. Run a search or traversal first.");
            return;
        }

        System.out.println(String.format("%-16s %-18s %8s %12s   %s",
                "Operation", "Algorithm", "Steps", "Time (ns)", "Details"));
        System.out.println("-----------------------------------------------------------------------------");
        for (String entry : entries) {
            System.out.println(entry);
        }
        System.out.println("=============================================");
    }
}