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
		if (operacion == null) {
        	throw new OperacionNulaException("La operación no puede ser nula");
    	}

    
   	 	for (Operacion op : operaciones) {
        	if (op.getId() == operacion.getId()) {
            	throw new OperacionDuplicadaException(
                	"Ya existe una operación con id: " + operacion.getId()
            	);
       		}
    	}

    	operaciones.add(operacion);

	}
	
	public double getSaldoActual() {
		
		double saldo = saldoInicial;
		
        for (Operacion op : operaciones) {
            saldo += op.getImporte();
        }
		if (!admiteDescubierto && saldo <0){
			throw new saldoNegativoException("Saldo no puede ser negativo" );
		}
		
		double saldoMult = saldo * 1000;
		if (saldoMult % 10 != 0) {  
			saldo = Math.round(saldo * 100.0) / 100.0;
		}
		return saldo; 
	
        
	}

}
