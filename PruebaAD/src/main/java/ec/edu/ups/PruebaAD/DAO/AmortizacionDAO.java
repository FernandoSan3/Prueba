package ec.edu.ups.PruebaAD.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.PruebaAD.modelo.Amortizacion;
import ec.edu.ups.PruebaAD.modelo.Cuenta;
import ec.edu.ups.PruebaAD.modelo.SolicitudCredito;

public class AmortizacionDAO {
	
	@Inject
	private EntityManager em;

	@Inject
	private Connection con;
	
	@Inject
	private SolicitudCreditoDAO solicitudCreditoDAO;
	
	
	public boolean insertAmortizacion(Amortizacion cuenta) throws SQLException {
		em.persist(cuenta);
		return true;
	}
	
	public Amortizacion readAmortizacion(int cuenta_id) throws SQLException {
		Amortizacion cuenta = em.find(Amortizacion.class, cuenta_id);
		return cuenta;
	}

	public boolean updateAmortizacion(Amortizacion cuenta) throws SQLException {
		em.merge(cuenta);
		return true;
	}

	public boolean deleteAmortizacion(int cuenta_id) throws SQLException {
		em.remove(em.find(Amortizacion.class, cuenta_id));
		return true;
	}
	
	public int contarAmortizacion() throws SQLException {
		int s;
		String sql = "SELECT MAX (amortizacion_id) FROM Amortizacion";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		res.next();
		s = res.getInt(1) + 1;
		ps.execute();
		ps.close();
		return s;
	}
	
	public Amortizacion buscarCuenta(String numero_cuenta) throws SQLException {
		Amortizacion amortizacion = new Amortizacion();
		String sql = "SELECT * FROM Amortizacion WHERE  amortizacion_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, numero_cuenta);
		ResultSet res = ps.executeQuery();
		res.next();
		amortizacion.setAmortizacion_id(res.getInt("amortizacion_id"));
		amortizacion.setCoutas(res.getInt("coutas"));
		amortizacion.setValor_pagar(res.getDouble("valor_pagar"));
		amortizacion.setSolicitudCredito(solicitudCreditoDAO.readSolicitud(res.getInt("solicitud_id")));
		
		ps.execute();
		ps.close();
		return amortizacion;
	}

}
