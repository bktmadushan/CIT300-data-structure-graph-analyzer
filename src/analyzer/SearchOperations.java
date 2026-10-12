package analyzer;

import java.util.Random;

/**
 * Searching algorithms with step counting and timing,
 * so the difference between them can be observed.
 */
public class SearchOperations {

    /**
     * Linear search: check every element from the start. O(n).
     * Works on unsorted data.
     */
    public static SearchResult linearSearch(int[] data, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int foundIndex = -1;

        for (int i = 0; i < data.length; i++) {
            steps++;                       // one comparison
            if (data[i] == target) {
                foundIndex = i;
                break;
            }
        }

        long elapsed = System.nanoTime() - start;
        return new SearchResult(foundIndex, steps, elapsed);
    }

    /**
     * Binary search: repeatedly halve the search range. O(log n).
     * The data MUST already be sorted in ascending order.
     */
    public static SearchResult binarySearch(int[] sortedData, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int foundIndex = -1;

        int low = 0;
        int high = sortedData.length - 1;

        while (low <= high) {
            steps++;                       // one comparison with the middle element
            int mid = low + (high - low) / 2;

            if (sortedData[mid] == target) {
                foundIndex = mid;
                break;
            } else if (sortedData[mid] < target) {
                low = mid + 1;             // discard the left half
            } else {
                high = mid - 1;            // discard the right half
            }
        }

        long elapsed = System.nanoTime() - start;
        return new SearchResult(foundIndex, steps, elapsed);
    }

    /**
     * Insertion sort on a COPY of the data (the original is left unchanged).
     * O(n^2), so the random data size is limited to 10,000 in the menu.
     */
    public static int[] insertionSort(int[] data) {
        int[] sorted = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            sorted[i] = data[i];
        }

        for (int i = 1; i < sorted.length; i++) {
            int key = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j] > key) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    /** Creates an array of n random numbers between 1 and 100000. */
    public static int[] generateRandom(int n) {
        Random random = new Random();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(100000) + 1;
        }
        return data;
    }
}