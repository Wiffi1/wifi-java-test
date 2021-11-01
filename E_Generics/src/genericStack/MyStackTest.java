package genericStack;

// https://stackoverflow.com/questions/2893484/implementing-a-stack-using-test-driven-development

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("push tests")
    @Nested class pushTests {
        @Test
        public void push_int() {
            MyStack<Integer> myStack = new MyStack<Integer>(10);
            myStack.push(10);
            int size = myStack.size();
            int peekedEl = myStack.peek();
            assertEquals(size, 1);
            assertEquals(peekedEl, 10);
        }

        @Test
        public void push_String() {
            MyStack<String> myStack = new MyStack<String>(10);
            myStack.push((String)"Erster String");
            int size = myStack.size();
            String peekedEl = myStack.peek();
//            System.out.printf("%s");
            assertEquals(size, 1);
            assertEquals(peekedEl, "Erster String");
        }
    }


    @DisplayName("pop tests")
    @Nested class popTests {
        @Test
        public void pop_1() {
            MyStack stack = new MyStack(2);
            MyStack<String> myStack = new MyStack<>(10);
            myStack.push("Erster String");
            String peekedEl = myStack.peek();
            assertEquals(peekedEl, "Erster String");
//            var el = myStack.pop();
            String popedEl = myStack.pop();
            assertEquals(popedEl, "Erster String");
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
