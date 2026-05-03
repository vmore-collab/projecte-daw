import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompteBancariTest { // Afegit "public"

    private CompteBancari compte;

    @BeforeEach
    void setUp() {
        compte = new CompteBancari("Joan Garcia", "ES1234567890123456789012", 1000.0);
    }

    @Test
    void testCreacioCorrecta() {
        assertEquals("Joan Garcia", compte.getTitular());
        assertEquals("ES1234567890123456789012", compte.getIban());
        assertEquals(1000.0, compte.getSaldo(), 0.001);
    }

    @Test
    void testErrorTitularBuitONul() {
        assertThrows(IllegalArgumentException.class, () -> new CompteBancari("", "ES12", 500.0));

        assertThrows(IllegalArgumentException.class, () -> new CompteBancari(null, "ES12", 500.0));
    }

    @Test
    void testErrorIbanBuitONul() {
        assertThrows(IllegalArgumentException.class, () -> new CompteBancari("Joan Garcia", "", 500.0));

        assertThrows(IllegalArgumentException.class, () -> new CompteBancari("Joan Garcia", null, 500.0));
    }

    @Test
    void testErrorSaldoInicialNegatiu() {
        assertThrows(IllegalArgumentException.class, () -> new CompteBancari("Joan Garcia", "ES12", -100.0));
    }

    @Test
    void testIngressarCorrecte() {
        compte.ingressar(300.0);
        assertEquals(1300.0, compte.getSaldo(), 0.001);
    }

    @Test
    void testErrorIngressarNegatiu() {
        assertThrows(IllegalArgumentException.class, () -> compte.ingressar(-50.0));
        assertThrows(IllegalArgumentException.class, () -> compte.ingressar(0));
    }

    @Test
    void testRetirarCorrecte() {
        compte.retirar(400.0);
        assertEquals(600.0, compte.getSaldo(), 0.001);
    }

    @Test
    void testErrorRetirarMesDelDisponible() {
        assertThrows(IllegalArgumentException.class, () -> compte.retirar(2000.0));
    }
}