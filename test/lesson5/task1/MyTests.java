package lesson5.task1;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lesson5.task1.MapKt.removeFillerWords;

public class MyTests {

    @Test
    public void removeFillerWords_shouldReturnExpectedValues() {
        Assertions.assertEquals(
                Arrays.stream("Мама мыла раму".split(" ")).collect(Collectors.toList()),
                removeFillerWords(Arrays.stream("Мама мыла типа э раму".split(" ")).collect(Collectors.toList()), "типа", "э")
        );
        Assertions.assertEquals(
                List.of(),
                removeFillerWords(Arrays.stream("Аэээ ммм".split(" ")).collect(Collectors.toList()), "Аэээ", "ммм")
        );
        Assertions.assertEquals(
                Arrays.stream("Clean text without filler words".split(" ")).collect(Collectors.toList()),
                removeFillerWords(Arrays.stream("Clean text without filler words".split(" ")).collect(Collectors.toList()), "qwerty")
        );
        Assertions.assertEquals(
                Arrays.stream("Clean text without filler words".split(" ")).collect(Collectors.toList()),
                removeFillerWords(Arrays.stream("Clean text without filler words".split(" ")).collect(Collectors.toList()))
        );
        Assertions.assertEquals(
                List.of(),
                removeFillerWords(Arrays.stream("a a a a a a a a a a a a a a a a a a a".split(" ")).collect(Collectors.toList()), "a")
        );
        Assertions.assertEquals(
                Arrays.stream("a a a a a a a a a a a a a a a a a a a".split(" ")).collect(Collectors.toList()),
                removeFillerWords(Arrays.stream("a a a a a a a a a a a a a a a a a a a".split(" ")).collect(Collectors.toList()), "а") //cyrillic letter a
        );
        Assertions.assertEquals(
                List.of(),
                removeFillerWords(List.of())
        );
    }

    @Test(expected = NullPointerException.class)
    public void removeFillerWords_shouldCorrectlyPassNullText() {
        removeFillerWords(null, "типа", "э");
    }

    @Test(expected = NullPointerException.class)
    public void removeFillerWords_shouldCorrectlyPassNullWords() {
        removeFillerWords(List.of(), null);
    }

}
