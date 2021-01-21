package ec.edu.ups.PruebaAD.ON;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.PruebaAD.DAO.AmortizacionDAO;
import ec.edu.ups.PruebaAD.DAO.CuentaDAO;
import ec.edu.ups.PruebaAD.DAO.PersonaDAO;
import ec.edu.ups.PruebaAD.DAO.SolicitudCreditoDAO;
import ec.edu.ups.PruebaAD.modelo.Amortizacion;
import ec.edu.ups.PruebaAD.modelo.Cuenta;
import ec.edu.ups.PruebaAD.modelo.Persona;
import ec.edu.ups.PruebaAD.modelo.SolicitudCredito;




@Stateless
public class PersonaON  implements PersonaLoca{
	
	
	private Cuenta cuenta = new Cuenta();
	private Persona persona = new Persona();
	private SolicitudCredito solicitudCredito = new SolicitudCredito();
	private Amortizacion amortizacion = new Amortizacion();
	
	@Inject
	private PersonaDAO personaDAO;
	
	@Inject
	private CuentaDAO cuentaDAO;
	
	@Inject
	private SolicitudCreditoDAO solicitudCreditoDAO;
	
	@Inject
	private AmortizacionDAO amortizacionDAO;
	
	
	public Cuenta buscarcuenta(int nCuenta) throws SQLException {
		
		cuenta = cuentaDAO.buscarCuenta(nCuenta);
		return cuenta;
	}
	
	public Persona buscarpersona(String cedula) throws SQLException {
		
		persona = personaDAO.buscarCedula(cedula);
		return persona;
	}
	
	public SolicitudCredito buscarsolicitud(String numero_cuenta) throws SQLException {
		
		solicitudCredito = solicitudCreditoDAO.buscarSolicituCuenta(numero_cuenta);
		return solicitudCredito;
	}
	
	public Amortizacion buscarAmortizacion(String numero_cuenta) throws SQLException {
		
		amortizacion = amortizacionDAO.buscarCuenta(numero_cuenta);
		return amortizacion;
	}
	
	
	public boolean guardarPersona(Persona person) throws Exception {
		if (person == null)
			throw new Exception("Error en el Objeto Persona");
		try {
			personaDAO.insertPersona(person);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Ingresar Persona");
		}
		return true;
	}
	
	public boolean guardarCuenta(Cuenta cuent) throws Exception {
		if (cuent == null)
			throw new Exception("Error en el Objeto Cuenta");
		try {
			cuentaDAO.insertCuenta(cuent);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Ingresar Cuenta");
		}
		return true;
	}
	
	public boolean guardarSolicitus(SolicitudCredito solicitudCredito) throws Exception {
		if (solicitudCredito == null)
			throw new Exception("Error en el Objeto Solicitud de Credito");
		try {
			solicitudCreditoDAO.insertSolicitud(solicitudCredito);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Ingresar Solicitud de Credito");
		}
		return true;
	}
	
	public boolean guardarAmortizacion(Amortizacion amortizacio) throws Exception {
		if (amortizacio == null)
			throw new Exception("Error en el Objeto Amortizacion");
		try {
			amortizacionDAO.insertAmortizacion(amortizacio);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Ingresar Amortizacion");
		}
		return true;
	}

	

}
