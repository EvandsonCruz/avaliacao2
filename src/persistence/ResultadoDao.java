package persistence;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.Resultado;

public class ResultadoDao {
	private Connection c;
	
	public ResultadoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public String procInsereBateriaInicial(int id_prova) throws SQLException {
		String sql = "{CALL sp_insere_bateria_inicial(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, id_prova);
		cs.registerOutParameter(2,Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(2);
		cs.close();		
		return saida;
	}
	
	public String procInsereBateriaFinal(int id_prova) throws SQLException {
		String sql = "{CALL sp_insere_bateria_final(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, id_prova);
		cs.registerOutParameter(2,Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(2);
		cs.close();		
		return saida;
	}
	
	public List<Resultado> listaResultadoProvaBateria(int id_prova, String select) throws SQLException {
		List<Resultado> listaResultado = new ArrayList<Resultado>();
		String sql="";
		boolean ocorreu = true;
		String ordem = "";
		
		if(id_prova == 1) {
			ordem = "DESC";
		}		
		
		if(select == "todos_ini") {
			sql = "select * from fn_bateria_inicial("+id_prova+") order by melhor " + ordem;
		}
		if(select == "top_ini") {
			sql = "select top 9 * from fn_bateria_inicial("+id_prova+") order by melhor " + ordem;
		}
		if(select == "todos_fin") {
			sql = "select * from fn_bateria_final("+id_prova+") order by melhor " + ordem;
		}
		if(select == "top_fin") {
			sql = "select top 4 * from fn_bateria_final("+id_prova+") order by melhor " + ordem;
		}
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			ocorreu = false;
			UIManager.put("OptionPane.minimumSize",new Dimension(200,100));
			JOptionPane.showMessageDialog(null, "Esta prova não ocorreu ainda");
		}
		while(rs.next()) {
			Resultado res = new Resultado();
			res.setCod_atleta(rs.getInt("cod_atleta"));
			res.setNome_atleta(rs.getString("nome_atleta"));
			res.setNome_pais(rs.getString("nome_pais"));
			res.setResultado1(rs.getString("resultado1"));
			res.setResultado2(rs.getString("resultado2"));
			res.setResultado3(rs.getString("resultado3"));
			res.setResultado4(rs.getString("resultado4"));
			res.setResultado5(rs.getString("resultado5"));
			res.setResultado6(rs.getString("resultado6"));
			res.setMelhor(rs.getString("melhor"));
			res.setRecorde_mundial(rs.getString("recorde_mundial"));
			res.setRecorde_evento(rs.getString("recorde_evento"));
			res.setBateria(rs.getString("bateria"));
			listaResultado.add(res);
			if(rs.getString("resultado1") == null && ocorreu == true) {
				ocorreu = false;
				UIManager.put("OptionPane.minimumSize",new Dimension(200,100));
				JOptionPane.showMessageDialog(null, "Esta prova não ocorreu ainda");
			}
		}				
		
		rs.close();
		ps.close();
		return listaResultado;
	}
}
