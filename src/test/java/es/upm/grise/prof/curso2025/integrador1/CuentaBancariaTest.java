package test.java.es.upm.grise.prof.curso2025.integrador1;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.beans.Transient;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import es.upm.grise.prof.curso2025.integrador1.CuentaBancaria;
import es.upm.grise.prof.curso2025.integrador1.Cliente;
import es.upm.grise.prof.curso2025.integrador1.Operacion;
import es.upm.grise.prof.curso2025.integrador1.OperacionDuplicadaException;
import es.upm.grise.prof.curso2025.integrador1.OperacionNulaException;
import es.upm.grise.prof.curso2025.integrador1.saldoNegativoException;

public class CuentaBancariaTest {

    private CuentaBancaria cuenta;
    private Operacion operacionMock1;
    private Operacion operacionMock2;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
   

    @Before
    public void inicializar() {
    
       
        cuenta = new CuentaBancaria("12345", 100.0);
        operacionMock1 = mock(Operacion.class);
        when(operacionMock1.getImporte()).thenReturn(50.0);
        when(operacionMock1.getId()).thenReturn(1L);
        when(operacionMock1.getConcepto()).thenReturn("Depósito");

        operacionMock2 = mock(Operacion.class);
        when(operacionMock2.getImporte()).thenReturn(-30.0);
        when(operacionMock2.getId()).thenReturn(2L);
        when(operacionMock2.getConcepto()).thenReturn("Retiro");
    }

    @Test
    public void testConstructor() {
        assertEquals("12345", cuenta.getNumeroCuenta());
        assertEquals(100.0, cuenta.getSaldoInicial(), 0.001);
        assertFalse(cuenta.isAdmiteDescubierto());
        assertTrue(cuenta.getOperaciones().isEmpty());
    }

    @Test
    public void testAddOperacion() throws OperacionNulaException, OperacionDuplicadaException{
        cuenta.addOperacion(operacionMock1);
        cuenta.addOperacion(operacionMock2);

        assertEquals(2, cuenta.getOperaciones().size());
        assertTrue(cuenta.getOperaciones().contains(operacionMock1));
        assertTrue(cuenta.getOperaciones().contains(operacionMock2));
    }

    @Test(expected = OperacionNulaException.class)
    public void testOperacionNula() throws OperacionNulaException, OperacionDuplicadaException {
        cuenta.addOperacion(null);
    }

    @Test
    public void testOperacionDuplicada() throws OperacionNulaException, OperacionDuplicadaException {
        cuenta.addOperacion(operacionMock1);

        exceptionRule.expect(OperacionDuplicadaException.class);
        exceptionRule.expectMessage("Ya existe una operación con id: 1");

       cuenta.addOperacion(operacionMock1);
    }

    @Test
    public void testGetSaldoActual() throws OperacionNulaException, OperacionDuplicadaException, saldoNegativoException{
       
        assertEquals(100.0, cuenta.getSaldoActual(), 0.001);

        // Con operaciones
        cuenta.addOperacion(operacionMock1);  // +50
        cuenta.addOperacion(operacionMock2);  // -30

        assertEquals(120.0, cuenta.getSaldoActual(), 0.001);
    }

   

}









