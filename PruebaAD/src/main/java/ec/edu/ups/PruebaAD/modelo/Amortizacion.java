package ec.edu.ups.PruebaAD.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Amortizacion  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "amortizacion_id")
	private int amortizacion_id;
	private int coutas;
	private double valor_pagar;
	
	@ManyToOne
	@JoinColumn(name = "solicitud_id")
	private SolicitudCredito solicitudCredito;
	
	public int getAmortizacion_id() {
		return amortizacion_id;
	}
	public void setAmortizacion_id(int amortizacion_id) {
		this.amortizacion_id = amortizacion_id;
	}
	public int getCoutas() {
		return coutas;
	}
	public void setCoutas(int coutas) {
		this.coutas = coutas;
	}
	public double getValor_pagar() {
		return valor_pagar;
	}
	public void setValor_pagar(double valor_pagar) {
		this.valor_pagar = valor_pagar;
	}
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}
	@Override
	public String toString() {
		return "Amortizacion [amortizacion_id=" + amortizacion_id + ", coutas=" + coutas + ", valor_pagar="
				+ valor_pagar + ", solicitudCredito=" + solicitudCredito + "]";
	}
	
	

}
