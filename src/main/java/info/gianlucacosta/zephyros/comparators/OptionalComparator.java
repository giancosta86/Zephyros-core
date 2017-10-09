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
