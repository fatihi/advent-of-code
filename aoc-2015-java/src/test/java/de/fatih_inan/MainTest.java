package de.fatih_inan;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void test() throws Exception {
        var text = tapSystemOutNormalized(() -> Main.main(new String[]{}));

        assertEquals("Hello, world!\n", text);
    }
}
