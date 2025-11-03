import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.Font;

public class PrendaVista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable tablePrenda;

	
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

	public PrendaVista() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 500, 550);
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

	    textField_1 = new JTextField();
	    textField_1.setBounds(108, 97, 114, 21);
	    contentPane.add(textField_1);

	    textField_2 = new JTextField();
	    textField_2.setBounds(108, 126, 114, 21);
	    contentPane.add(textField_2);

	    textField_3 = new JTextField();
	    textField_3.setBounds(108, 155, 114, 21);
	    contentPane.add(textField_3);

	    textField_4 = new JTextField();
	    textField_4.setBounds(108, 184, 114, 21);
	    contentPane.add(textField_4);

	    textField_5 = new JTextField();
	    textField_5.setBounds(108, 213, 114, 21);
	    contentPane.add(textField_5);

	    JButton btnAgregar = new JButton("Agregar");
	    btnAgregar.setBounds(334, 79, 105, 27);
	    contentPane.add(btnAgregar);

	    JButton btnActualizar = new JButton("Actualizar");
	    btnActualizar.setBounds(334, 123, 105, 27);
	    contentPane.add(btnActualizar);

	    JButton btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBounds(334, 166, 105, 27);
	    contentPane.add(btnEliminar);

	    JButton btnListar = new JButton("Listar");
	    btnListar.setBounds(334, 205, 105, 27);
	    contentPane.add(btnListar);

	    JLabel lblModaUrbana_TituloVentana = new JLabel("MODA URBANA");
	    lblModaUrbana_TituloVentana.setFont(new Font("URW Gothic", Font.BOLD, 20));
	    lblModaUrbana_TituloVentana.setBounds(195, 28, 204, 46);
	    contentPane.add(lblModaUrbana_TituloVentana);

	    String[] columnas = {"ID", "Descripción", "Talle", "Color", "Precio", "Stock"};
	    Object[][] datos = {}; 
	    DefaultTableModel modelo = new DefaultTableModel(datos, columnas);

	    tablePrenda = new JTable(modelo);

	    JScrollPane scrollPane = new JScrollPane(tablePrenda);
	    scrollPane.setBounds(26, 262, 453, 230);
	    contentPane.add(scrollPane);
	    
	    btnListar.addActionListener(e -> {
	        DefaultTableModel model = (DefaultTableModel) tablePrenda.getModel();
	        model.addRow(new Object[]{1, "Remera oversize", "M", "Negra", 1299.50, 10});
	        model.addRow(new Object[]{2, "Pantalón cargo", "L", "Verde", 2599.00, 5});
	    });
	}
}