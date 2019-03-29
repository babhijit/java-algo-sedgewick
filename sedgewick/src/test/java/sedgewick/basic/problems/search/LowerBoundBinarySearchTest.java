package sedgewick.basic.problems.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sedgewick.basic.problems.search.LowerBoundBinarySearch;

class LowerBoundBinarySearchTest {

    @Test
    void testBinarySearch() {
        Integer [] values = { 1, 2, 2, 3, 3, 4, 4, 4, 6, 7, 8 };
        Assertions.assertEquals(5, LowerBoundBinarySearch.binarySearch(values, 0, values.length - 1, 4));
    }

}