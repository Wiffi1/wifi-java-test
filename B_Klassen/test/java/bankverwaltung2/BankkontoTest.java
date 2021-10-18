package bankverwaltung2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BankkontoTest {
    Bankkonto konto1;

    static final Logger log = Logger.getLogger(BankkontoTest.class.getName());

    @BeforeAll
    public static void beforeClass() {
        /*log.info("@BeforeAll");*/
    }

    @AfterAll
    public static void afterClass() {
        /*log.info("@AterAll");*/
    }

    @BeforeEach
    public void setUp() {
        /*log.info("@BeforeEach");*/
    }

    @AfterEach
    public void tearDown() {
        /*log.info("@AfterEach");*/
    }

    @Test
    public void test1() {
        konto1 = new Bankkonto("FI1", 1.1, 300);
        konto1.abheben(100);
        konto1.anzeigen();
        assertEquals(200, konto1.getKontostand());
    }

    @DisplayName("Sparbuch anlegen")
    @Nested class sparbuch_anlegen {

        @DisplayName("mit Maximalbetrag - 15000")
        @Test
        public void test1() {
            konto1 = new Bankkonto("FI1", 1.1, 15000);
            assertEquals(15000, konto1.getKontostand());
        }

        @DisplayName("weniger als Maximalbetrag - 15000")
        @Test
        public void test2() {
            assertThrows(IllegalArgumentException.class, () -> {
                konto1 = new Bankkonto("FI1", 1.1, 15001);
            });
        }
    }

    @DisplayName("Gehaltskonto anlegen")
    @Nested class gehaltskonto_anlegen {
        public void test1() {
            konto1 = new Bankkonto("FI1", 200);
            assertEquals(15000, konto1.getKontostand());
        }
    }

    @DisplayName("einzahlen")
    @Nested class einzahlen {
        @Test
        public void test1() {
            konto1 = new Bankkonto("FI1", 1.1, 100);
            konto1.einzahlen(100);
            assertEquals(200, konto1.getKontostand());
        }
    }

    @DisplayName("abheben")
    @Nested class abheben {
        @Test
        public void test1() {
            konto1 = new Bankkonto("FI1", 1.1, 100);
            konto1.abheben(100);
            assertEquals(0, konto1.getKontostand());
        }

        @Test
        public void test2() {
            assertThrows(IllegalArgumentException.class, () -> {
                konto1 = new Bankkonto("FI1", 1.1, 100);
                konto1.abheben(200);
            });
        }
    }

}