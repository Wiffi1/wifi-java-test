package genericStackTDD;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStackTest {
    static final Logger log = Logger.getLogger("Logger");

    @BeforeAll
    public static void beforeClass() { /*log.info("@BeforeAll");*/ }

    @AfterAll
    public static void afterClass() { /*log.info("@AterAll");*/ }

    @BeforeEach
    public void setUp() { /*log.info("@BeforeEach");*/ }

    @AfterEach
    public void tearDown() { /*log.info("@AfterEach");*/ }

    @DisplayName("simpleTest")
    @Nested class simpleTest {
        @Test
        public void simpleTest1() {
            assertTrue( true);
        }
    }

    @DisplayName("nestedTest")
    @Nested class nestedTest {
        @Test
        public void nestedTest1() {
            assertEquals(true, true);
        }
    }

}
