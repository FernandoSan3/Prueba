package ec.edu.ups.PruebaAD.ON;

import java.sql.SQLException;

import javax.ejb.Local;

import ec.edu.ups.PruebaAD.modelo.Amortizacion;
import ec.edu.ups.PruebaAD.modelo.Cuenta;
import ec.edu.ups.PruebaAD.modelo.Persona;
import ec.edu.ups.PruebaAD.modelo.SolicitudCredito;

@Local
public interface PersonaLoca {
	
	public Cuenta buscarcuenta(int nCuenta) throws SQLException;
	public Persona buscarpersona(String cedula) throws SQLException;
	public SolicitudCredito buscarsolicitud(String numero_cuenta) throws SQLException ;
	public Amortizacion buscarAmortizacion(String numero_cuenta) throws SQLException ;
	public boolean guardarPersona(Persona person) throws Exception;
	public boolean guardarCuenta(Cuenta cuent) throws Exception;
	public boolean guardarSolicitus(SolicitudCredito solicitudCredito) throws Exception;
	public boolean guardarAmortizacion(Amortizacion amortizacio) throws Exception;
	
	
	

}
