package genericStack;

public class MyStackDemo {
    public static void main(String[] args) {
        MyStack<String> myStringStack = new MyStack<>(10);
        myStringStack.push("Erster String");
        listStack(myStringStack);
    }

    private static void listStack(MyStack mystack) {
        System.out.printf(" listStack " + mystack.toString());
        for (int i = 0; i < mystack.size(); i++) {
            var el = mystack.pop();
            System.out.println("pop " + el.getClass().getName());
            System.out.println("pop " + el.getClass());
        }
/*
        try {
            mystack.pop()
        } catch (Error e) {

        }
*/

    }

}
