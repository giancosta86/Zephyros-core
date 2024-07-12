package info.gianlucacosta.zephyros.comparators;

import java.util.Comparator;
import java.util.Optional;

/**
 * Comparator for Optional.
 * <p>
 * It sorts Optional.empty() before Optional.of().
 * After that, it employs the given comparator for items (by default,
 * the natural comparator)
 *
 * @param <T> Comparable type argument of Optional
 */
public class OptionalComparator<T extends Comparable<T>> implements Comparator<Optional<T>> {
    private final Comparator<T> itemComparator;

    public OptionalComparator() {
        this(
                Comparator.naturalOrder()
        );
    }


    public OptionalComparator(Comparator<T> itemComparator) {
        this.itemComparator = itemComparator;
    }

    @Override
    public int compare(Optional<T> leftOption, Optional<T> rightOption) {
        return leftOption
                .map(left ->
                        rightOption
                                .map(right -> itemComparator.compare(left, right))
                                .orElse(1)
                )
                .orElseGet(() ->
                        rightOption
                                .map(right -> -1)
                                .orElse(0)
                );
    }
}
