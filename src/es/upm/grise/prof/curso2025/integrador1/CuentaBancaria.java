package es.upm.grise.prof.curso2025.integrador1;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
	
	String numeroCuenta;
	double saldoInicial;
	boolean admiteDescubierto;
	List<Operacion> operaciones;
	
	//
	// CUERPO DEL EXAMEN
	//
	
	public CuentaBancaria(String numeroCuenta, double saldoInicial) {
		
		// TODO: Realizar
		this.numeroCuenta = numeroCuenta;
		this.saldoInicial = saldoInicial; 
		this.admiteDescubierto = false;
		this.operaciones = new ArrayList<>();
		
	}
		
	public void addOperacion(Operacion operacion) {
		if (operacion != null) {
			operaciones.add(operacion);
		}

	}
	
	public double getSaldoActual() {
		
		double saldo = saldoInicial;
        for (Operacion op : operaciones) {
            saldo += op.getImporte();
        }
        return saldo;
	}

}
