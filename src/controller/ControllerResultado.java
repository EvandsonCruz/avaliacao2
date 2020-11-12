package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Resultado;
import persistence.ResultadoDao;

public class ControllerResultado {

	public String insereResultadoBateriaInicial(int id_prova) throws SQLException, ClassNotFoundException {
		ResultadoDao rDao = new ResultadoDao();
		String saida = rDao.procInsereBateriaInicial(id_prova);
		return saida;
	}

	public String insereResultadoBateriaFinal(int id_prova) throws SQLException, ClassNotFoundException {
		ResultadoDao rDao = new ResultadoDao();
		String saida = rDao.procInsereBateriaFinal(id_prova);
		return saida;
	}

	public void listaResultadoProvaBateria(int id_prova, String select) throws ClassNotFoundException, SQLException {

		ResultadoDao rDao = new ResultadoDao();
		List<Resultado> listaResultado = rDao.listaResultadoProvaBateria(id_prova, select);

		String col[] = { "COD", "NOME", "NOME_PAIS", "RES_1", "RES_2", "RES_3", "RES_4", "RES_5", "RES_6", "MELHOR",
				"R_MUNDIAL", "R_EVENTO", "BATERIA" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (Resultado res : listaResultado) {
			Object[] resultado = { res.getCod_atleta(), res.getNome_atleta(), res.getNome_pais(), res.getResultado1(),
					res.getResultado2(), res.getResultado3(), res.getResultado4(), res.getResultado5(),
					res.getResultado6(), res.getMelhor(), res.getRecorde_mundial(), res.getRecorde_evento(),
					res.getBateria() };
			tableModel.addRow(resultado);

		}
		JTable table = new JTable(tableModel);

		CorNaLinha(table);

		UIManager.put("OptionPane.minimumSize", new Dimension(1000, 300));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		table.getColumnModel().getColumn(11).setPreferredWidth(100);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "Resultados da bateria e prova selecionados",
				JOptionPane.PLAIN_MESSAGE);
	}

	public void CorNaLinha(JTable table) {

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				if (table.getValueAt(row, 10).toString().equals("sim")) {
					label.setBackground(Color.GREEN);
				} else if (table.getValueAt(row, 11).toString().equals("sim")) {
					label.setBackground(Color.cyan);
				} else {
					label.setBackground(Color.WHITE);
				}

				return label;
			}

			private static final long serialVersionUID = 1L;
		});
	}
}
