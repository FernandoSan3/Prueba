package ec.edu.ups.PruebaAD.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.PruebaAD.modelo.SolicitudCredito;


public class SolicitudCreditoDAO {
	
	@Inject
	private EntityManager em;

	@Inject
	private Connection con;
	
	@Inject
	private CuentaDAO cuentaDAO;
	
	public boolean insertSolicitud(SolicitudCredito cuenta) throws SQLException {
		em.persist(cuenta);
		return true;
	}
	
	public SolicitudCredito readSolicitud(int cuenta_id) throws SQLException {
		SolicitudCredito cuenta = em.find(SolicitudCredito.class, cuenta_id);
		return cuenta;
	}

	public boolean updateSolicitud(SolicitudCredito cuenta) throws SQLException {
		em.merge(cuenta);
		return true;
	}

	public boolean deleteSolicitud(int cuenta_id) throws SQLException {
		em.remove(em.find(SolicitudCredito.class, cuenta_id));
		return true;
	}
	
	public int contarCuenta() throws SQLException {
		int s;
		String sql = "SELECT MAX (solicitud_id) FROM SolicitudCredito";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		res.next();
		s = res.getInt(1) + 1;
		ps.execute();
		ps.close();
		return s;
	}
	
	public SolicitudCredito buscarSolicituCuenta(String numero_cuenta) throws SQLException {
		SolicitudCredito solicitud = new SolicitudCredito();
		String sql = "SELECT * FROM SolicitudCredito WHERE  cuenta_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, numero_cuenta);
		ResultSet res = ps.executeQuery();
		res.next();
		solicitud.setSolicitud_id(res.getInt("solicitud_id"));
		solicitud.setFecha_actual(res.getDate("fecha_actual"));
		solicitud.setFecha_inicio_crdito(res.getDate("fecha_inicio_crdito"));
		solicitud.setCuenta(cuentaDAO.readCueta(res.getInt("cuenta_id")));
		
		ps.execute();
		ps.close();
		return solicitud;
	}
	
	

}
