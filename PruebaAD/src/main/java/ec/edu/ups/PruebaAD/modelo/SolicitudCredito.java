package ec.edu.ups.PruebaAD.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudCredito implements Serializable   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "solicitud_id")
	private int solicitud_id;
	
	private Date fecha_actual;
	private Date fecha_inicio_crdito;
	
	@ManyToOne
	@JoinColumn(name = "cuenta_id")
	private Cuenta cuenta;
	
	
	public int getSolicitud_id() {
		return solicitud_id;
	}
	public void setSolicitud_id(int solicitud_id) {
		this.solicitud_id = solicitud_id;
	}
	public Date getFecha_actual() {
		return fecha_actual;
	}
	public void setFecha_actual(Date fecha_actual) {
		this.fecha_actual = fecha_actual;
	}
	public Date getFecha_inicio_crdito() {
		return fecha_inicio_crdito;
	}
	public void setFecha_inicio_crdito(Date fecha_inicio_crdito) {
		this.fecha_inicio_crdito = fecha_inicio_crdito;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "SolicitudCredito [solicitud_id=" + solicitud_id + ", fecha_actual=" + fecha_actual
				+ ", fecha_inicio_crdito=" + fecha_inicio_crdito + ", cuenta=" + cuenta + "]";
	}
	
	
	
	

}
