package sedgewick.basic.problems.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sedgewick.basic.problems.search.StaticSetOfInts;

class StaticSetOfIntsTest {

    @Test
    void testHowMany() {
        int [] values = {1, 1, 2, 3, 4, 5, 6, 7, 7, 7, 7, 8};
        StaticSetOfInts staticSetOfInts = new StaticSetOfInts(values);
        Assertions.assertEquals(4, staticSetOfInts.howMany(7));
    }

}