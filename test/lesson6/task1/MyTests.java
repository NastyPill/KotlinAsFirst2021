package lesson6.task1;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static lesson6.task1.ParseKt.firstDuplicateIndex;

public class MyTests {

    @Test
    public void firstDuplicateIndex_shouldReturnCorrectResults() {
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("word")
        );
        Assertions.assertEquals(
                0,
                firstDuplicateIndex("word word")
        );
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("word wоrd") // cyrillic letter 'o'
        );
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("")
        );
        Assertions.assertEquals(
                5,
                firstDuplicateIndex("word a a word")
        );
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("word a word a word a")
        );
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("word wor d word")
        );
        Assertions.assertEquals(
                0,
                firstDuplicateIndex("word WoRd")
        );
        Assertions.assertEquals(
                12,
                firstDuplicateIndex("word a word a a")
        );
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("   ") // " " - is not a word but test fails
        );
        Assertions.assertEquals(
                -1,
                firstDuplicateIndex("# #") // "#" - is not a word but test fails
        );
    }
}
