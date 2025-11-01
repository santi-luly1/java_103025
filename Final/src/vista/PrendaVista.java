import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PrendaVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrendaVista frame = new PrendaVista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrendaVista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrenda_ID = new JLabel("Prenda_ID");
		lblPrenda_ID.setBounds(26, 72, 84, 17);
		contentPane.add(lblPrenda_ID);
		
		JLabel lblDescripcion_Prenda = new JLabel("Descripción");
		lblDescripcion_Prenda.setBounds(26, 99, 84, 17);
		contentPane.add(lblDescripcion_Prenda);
		
		JLabel lblTalle_Prenda = new JLabel("Talle");
		lblTalle_Prenda.setBounds(26, 128, 60, 17);
		contentPane.add(lblTalle_Prenda);
		
		JLabel lblColor_Prenda = new JLabel("Color");
		lblColor_Prenda.setBounds(26, 157, 60, 17);
		contentPane.add(lblColor_Prenda);
		
		JLabel lblPrecio_Prenda = new JLabel("Precio");
		lblPrecio_Prenda.setBounds(26, 186, 60, 17);
		contentPane.add(lblPrecio_Prenda);
		
		JLabel lblStock_Prenda = new JLabel("Stock");
		lblStock_Prenda.setBounds(26, 215, 60, 17);
		contentPane.add(lblStock_Prenda);
		
		textField = new JTextField();
		textField.setBounds(108, 68, 114, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 97, 114, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(108, 126, 114, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(108, 155, 114, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(108, 184, 114, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(108, 213, 114, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(304, 72, 105, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(304, 118, 105, 27);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(304, 164, 105, 27);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(304, 210, 105, 27);
		contentPane.add(btnNewButton_3);

	}
}