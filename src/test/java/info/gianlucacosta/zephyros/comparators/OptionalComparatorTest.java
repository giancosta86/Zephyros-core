package info.gianlucacosta.zephyros.comparators;

import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class OptionalComparatorTest {
    @Test
    public void emptyAndEmptyShouldBeEqual() {
        assertComparison(
                Optional.<Integer>empty(),
                Optional.empty(),
                0
        );
    }


    @Test
    public void leftEmptyShouldComeBeforeRightNonEmpty() {
        assertComparison(
                Optional.empty(),
                Optional.of(9),
                -1
        );
    }


    @Test
    public void rightEmptyShouldComeBeforeLeftNonEmpty() {
        assertComparison(
                Optional.of(9),
                Optional.empty(),
                1
        );
    }


    @Test
    public void nonEmptyValuesShouldUseNaturalComparatorByDefault() {
        assertComparison(
                Optional.of(90),
                Optional.of(50),
                1
        );
    }


    @Test
    public void nonEmptyValuesShouldUseTheProvidedComparator() {
        assertComparison(
                Comparator.<Integer>naturalOrder().reversed(),
                Optional.of(90),
                Optional.of(50),
                -1
        );
    }


    private <T extends Comparable<T>> void assertComparison(
            Optional<T> left,
            Optional<T> right,
            int expectedComparisonSign
    ) {
        assertComparison(
                Comparator.naturalOrder(),
                left,
                right,
                expectedComparisonSign
        );
    }


    private <T extends Comparable<T>> void assertComparison(
            Comparator<T> itemComparator,
            Optional<T> left,
            Optional<T> right,
            int expectedComparisonSign
    ) {
        OptionalComparator<T> optionalComparator =
                new OptionalComparator<>(itemComparator);

        int comparisonResult =
                optionalComparator.compare(left, right);

        int comparisonSign =
                (int) Math.signum(comparisonResult);

        assertThat(
                comparisonSign,
                equalTo(expectedComparisonSign)
        );
    }
}
