package view;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerResultado;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class Tela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String saida;
	ControllerResultado res = new ControllerResultado();
	private String[] provas = {"Salto Triplo","3000m"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Tela() throws ClassNotFoundException, SQLException {
		setTitle("SISTEMA DE PROVAS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 297, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Prova");
		lblNewLabel_2.setBounds(56, 21, 46, 14);
		contentPane.add(lblNewLabel_2);
								
		JComboBox<String> cbProva = new JComboBox<String>();
		cbProva.setBounds(56, 46, 176, 22);
		contentPane.add(cbProva);
		cbProva.addItem(provas[0]);
		cbProva.addItem(provas[1]);
						
		JButton btnTodosIni = new JButton("Ver Bateria Inicial Todos");
		btnTodosIni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerResultado cr = new ControllerResultado();
				try {
						String select = "todos_ini";
						cr.listaResultadoProvaBateria(cbProva.getSelectedIndex()+1,select);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTodosIni.setBounds(56, 89, 176, 23);
		contentPane.add(btnTodosIni);
		
		JButton btnTopIni = new JButton("Ver Bateria Inicial Top 8");
		btnTopIni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerResultado cr = new ControllerResultado();
				try {
						String select = "top_ini";
						cr.listaResultadoProvaBateria(cbProva.getSelectedIndex()+1,select);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTopIni.setBounds(56, 133, 176, 23);
		contentPane.add(btnTopIni);
		
		JButton btnTodosFin = new JButton("Ver Bateria Final Todos");
		btnTodosFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerResultado cr = new ControllerResultado();
				try {
						String select = "todos_fin";
						cr.listaResultadoProvaBateria(cbProva.getSelectedIndex()+1,select);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTodosFin.setBounds(56, 177, 176, 23);
		contentPane.add(btnTodosFin);
		
		JButton btnTopFin = new JButton("Ver Bateria Final Top 3");
		btnTopFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerResultado cr = new ControllerResultado();
				try {
						String select = "top_fin";
						cr.listaResultadoProvaBateria(cbProva.getSelectedIndex()+1,select);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTopFin.setBounds(56, 221, 176, 23);
		contentPane.add(btnTopFin);
		
		JButton btnCompIni = new JButton("Competir Bateria Inicial");
		btnCompIni.setForeground(Color.BLACK);
		btnCompIni.setBackground(SystemColor.activeCaption);
		btnCompIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCompIni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerResultado cr = new ControllerResultado();
				try {
						System.out.println(cr.insereResultadoBateriaInicial(cbProva.getSelectedIndex()+1));
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCompIni.setBounds(56, 265, 176, 23);
		contentPane.add(btnCompIni);
		
		JButton btnCompFin = new JButton("Competir Bateria Final");
		btnCompFin.setForeground(Color.BLACK);
		btnCompFin.setBackground(SystemColor.activeCaption);
		btnCompFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCompFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerResultado cr = new ControllerResultado();
				try {
					System.out.println(cr.insereResultadoBateriaFinal(cbProva.getSelectedIndex()+1));
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCompFin.setBounds(56, 309, 176, 23);
		contentPane.add(btnCompFin);
		
		
	}
}
