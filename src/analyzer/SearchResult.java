package analyzer;

/**
 * The outcome of one search: where the value was found (-1 if not found),
 * how many comparison steps were needed, and how long it took.
 */
public class SearchResult {

    public final int index;
    public final int steps;
    public final long nanos;

    public SearchResult(int index, int steps, long nanos) {
        this.index = index;
        this.steps = steps;
        this.nanos = nanos;
    }

    public boolean isFound() {
        return index != -1;
    }
}