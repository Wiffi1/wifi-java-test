package genericStack;

// https://stackoverflow.com/questions/2893484/implementing-a-stack-using-test-driven-development

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

    static final Logger log = Logger.getLogger("Logger");

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
            String popedEl = myStack.pop();
            assertEquals(popedEl, "Erster String");
            assertEquals(0, myStack.size());
        }
    }

    @DisplayName("Push on full Stack")
    @Nested class arrayGrösseVerändern {
        @Test
        public void push_int_onFullStack() {
            MyStack<Integer> myStack = new MyStack<Integer>(2);
            myStack.push(1);
            myStack.push(2);
            int size = myStack.size();
            assertEquals(size, 2);
            int peekeddEl = myStack.peek();
            assertEquals(peekeddEl, 2);
        }

        @Test
        public void push_String_onFullStack() {
            MyStack<String> myStack = new MyStack<>(2);
            myStack.push("first String");
            myStack.push("second String");
            myStack.push("third String");
            var size = myStack.size();
            assertEquals(size, 3);
            assertEquals(4, myStack.getMaxAnzahl());
            var peekeddEl = myStack.peek();
            assertEquals(peekeddEl, "third String");
        }
    }


    @DisplayName("Truncate")
    @Nested class Truncate {
        @Test
        public void truncate1() {
            MyStack<String> myStack = new MyStack<>(2);
            myStack.push("first String");
            myStack.push("second String");
            myStack.push("third String");
            myStack.truncate();
            assertEquals(3, myStack.size());
            assertEquals(3, myStack.getMaxAnzahl());
            var peekeddEl = myStack.peek();
        }
    }
}
