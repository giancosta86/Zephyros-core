/*^
  ===========================================================================
  Zephyros - Core
  ===========================================================================
  Copyright (C) 2017 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

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
