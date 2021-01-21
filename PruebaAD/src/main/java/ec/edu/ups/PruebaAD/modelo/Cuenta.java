package ec.edu.ups.PruebaAD.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Cuenta implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cuenta_id")
	private int cuenta_id;
	
	private int numero_cuenta;
	private double valor_credito;
	private int meses_plazo;
	
	@ManyToOne
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
	public int getCuenta_id() {
		return cuenta_id;
	}
	public void setCuenta_id(int cuenta_id) {
		this.cuenta_id = cuenta_id;
	}
	public int getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(int numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public double getValor_credito() {
		return valor_credito;
	}
	public void setValor_credito(double valor_credito) {
		this.valor_credito = valor_credito;
	}
	public int getMeses_plazo() {
		return meses_plazo;
	}
	public void setMeses_plazo(int meses_plazo) {
		this.meses_plazo = meses_plazo;
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	@Override
	public String toString() {
		return "Cuenta [cuenta_id=" + cuenta_id + ", numero_cuenta=" + numero_cuenta + ", valor_credito="
				+ valor_credito + ", meses_plazo=" + meses_plazo + ", persona=" + persona + "]";
	}
	
	
	
}
