package es.upm.grise.prof.curso2025.integrador1;

import java.util.ArrayList;
import java.util.List;
import es.upm.grise.prof.curso2025.integrador1.CuentaBancaria;
import es.upm.grise.prof.curso2025.integrador1.Cliente;
import es.upm.grise.prof.curso2025.integrador1.Operacion;
import es.upm.grise.prof.curso2025.integrador1.OperacionNulaException;
import es.upm.grise.prof.curso2025.integrador1.OperacionDuplicadaException;


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
		
	public void addOperacion(Operacion operacion) throws OperacionNulaException, OperacionDuplicadaException{
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
	
	public double getSaldoActual() throws saldoNegativoException {
		
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
	

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public boolean isAdmiteDescubierto() {
		return admiteDescubierto;
	}

	public List<Operacion> getOperaciones() {
		return operaciones;
	}

}
