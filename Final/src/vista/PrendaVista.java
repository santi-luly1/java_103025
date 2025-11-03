package vista;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.PrendaController;
import modelo.Prenda;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PrendaVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldID;
	private JTextField fieldDescripcion;
	private JTextField fieldTalle;
	private JTextField fieldColor;
	private JTextField fieldPrecio;
	private JTextField fieldStock;
	private JTable tablePrenda;
	private PrendaController prendaController;

	public PrendaVista(PrendaController prendaController) {
	    this.prendaController = prendaController;
	    initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 500, 550);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JLabel lblPrenda_ID = new JLabel("ID");
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

	    fieldID = new JTextField();
	    fieldID.setBounds(108, 68, 114, 21);
	    contentPane.add(fieldID);

	    fieldDescripcion = new JTextField();
	    fieldDescripcion.setBounds(108, 97, 114, 21);
	    contentPane.add(fieldDescripcion);

	    fieldTalle = new JTextField();
	    fieldTalle.setBounds(108, 126, 114, 21);
	    contentPane.add(fieldTalle);

	    fieldColor = new JTextField();
	    fieldColor.setBounds(108, 155, 114, 21);
	    contentPane.add(fieldColor);

	    fieldPrecio = new JTextField();
	    fieldPrecio.setBounds(108, 184, 114, 21);
	    contentPane.add(fieldPrecio);

	    fieldStock = new JTextField();
	    fieldStock.setBounds(108, 213, 114, 21);
	    contentPane.add(fieldStock);

	    JButton btnAgregar = new JButton("Agregar");
	    btnAgregar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String descripcion = fieldDescripcion.getText(); 
	    		String talle = fieldTalle.getText();
	    		String color = fieldColor.getText();
	    		double precio = Double.parseDouble(fieldPrecio.getText());
	    		int stock = Integer.parseInt(fieldStock.getText());
	    		
	    		Prenda prenda = null;
				try {
					prenda = new Prenda(prendaController.getPrendas().size() + 1, descripcion, talle, color, precio, stock);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	    		try {
					prendaController.agregarPrenda(prenda);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });
	    btnAgregar.setBounds(334, 79, 105, 27);
	    contentPane.add(btnAgregar);

	    JButton btnActualizar = new JButton("Actualizar");
	    btnActualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
	    			abrirModificar();
	    		} catch (SQLException e1) {
	    			System.out.println(e1.getMessage());
	    		}
	    	}
	    });
	    btnActualizar.setBounds(334, 123, 105, 27);
	    contentPane.add(btnActualizar);

	    JButton btnEliminar = new JButton("Eliminar");
	    btnEliminar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
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
	//ttttttttt
	private void abrirModificar() throws SQLException {
		int filaSeleccionada = tablePrenda.getSelectedRow();

		if (filaSeleccionada != -1) {
			new Modificar(prendaController, filaSeleccionada + 1).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Debe de seleccionar una persona.");
		}
	}
}