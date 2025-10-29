package test.java.ej;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class CuentaBancariaTest {

    private CuentaBancaria cuenta;
    private Operacion operacionMock1;
    private Operacion operacionMock2;

    @Before
    public void inicializar() {
    
        cuenta = new CuentaBancaria("12345", 100.0);

        operacionMock1 = mock(Operacion.class);
        when(operacionMock1.getImporte()).thenReturn(50.0);
        when(operacionMock1.getConcepto()).thenReturn("Depósito");

        operacionMock2 = mock(Operacion.class);
        when(operacionMock2.getImporte()).thenReturn(-30.0);
        when(operacionMock2.getConcepto()).thenReturn("Retiro");
    }

    @Test
    public void testConstructor() {
        assertEquals("12345", cuenta.getNumeroCuenta());
        assertEquals(100.0, cuenta.getSaldoActual(), 0.001);
        assertFalse(cuenta.isAdmiteDescubierto());
        assertTrue(cuenta.getOperaciones().isEmpty());
    }

    @Test
    public void testAddOperacion() {
        cuenta.addOperacion(operacionMock1);
        cuenta.addOperacion(operacionMock2);

        assertEquals(2, cuenta.getOperaciones().size());
        assertTrue(cuenta.getOperaciones().contains(operacionMock1));
        assertTrue(cuenta.getOperaciones().contains(operacionMock2));
    }

    @Test
    public void testGetSaldoActual() {
        // Sin operaciones
        assertEquals(100.0, cuenta.getSaldoActual(), 0.001);

        // Con operaciones
        cuenta.addOperacion(operacionMock1);  // +50
        cuenta.addOperacion(operacionMock2);  // -30

        assertEquals(120.0, cuenta.getSaldoActual(), 0.001);
    }
}









