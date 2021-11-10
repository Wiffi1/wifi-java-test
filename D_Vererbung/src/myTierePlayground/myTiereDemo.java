package myTierePlayground;

import java.time.LocalDate;

public class myTiereDemo {
    public static void main(String[] args) {
        Haustier minna =  new Haustier("Minna", LocalDate.of(2005, 12, 3));
        Hund rex = new Hund("Rex", LocalDate.of(2015, 12, 30), 21);
//        System.out.println(Haustier.ichEsse());
        System.out.println(Hund.ichEsse());
    }
}
