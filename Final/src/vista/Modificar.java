package vista;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.PrendaController;
import main.PrendaInvalidaException;
import main.VerificarPrenda;
import modelo.Prenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Modificar extends JFrame { // TODO: La verdad no s'e si habr'ia que agregar esta clase en s'i, nunca se menciona una interf'az de "modificar", igualmente la agregu'e.
    private static final long serialVersionUID = 1L;

    private final PrendaController controlador;
    private final int indexPrenda;

    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEdad;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JComboBox<String> comboBoxPais;
    private JRadioButton rdbtnMale;
    private JRadioButton rdbtnFemale;
    private JCheckBox chckbxEstudiante;

    public Modificar(PrendaController controlador, int indexPrenda) throws SQLException {
        this.controlador = controlador;
        this.indexPrenda = indexPrenda;
        initComponents();
    }

    private void initComponents() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 420, 260);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(42, 63, 60, 17);
        contentPane.add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(42, 92, 60, 17);
        contentPane.add(lblApellido);

        JLabel lblEdad = new JLabel("Edad");
        lblEdad.setBounds(42, 121, 60, 17);
        contentPane.add(lblEdad);

        JLabel lblEstudiante = new JLabel("Estudiante");
        lblEstudiante.setBounds(42, 151, 83, 17);
        contentPane.add(lblEstudiante);

        Prenda prenda = controlador.obtenerIndex(indexPrenda);
        if (prenda == null) {
            JOptionPane.showMessageDialog(this, "Prenda no encontrada.");
            openUI();
            return;
        }

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(106, 61, 114, 21);
        contentPane.add(textFieldNombre);
        textFieldNombre.setText(prenda.getNombre());

        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(106, 92, 114, 21);
        contentPane.add(textFieldApellido);
        textFieldApellido.setText(prenda.getApellido());

        textFieldEdad = new JTextField();
        textFieldEdad.setBounds(106, 119, 37, 21);
        contentPane.add(textFieldEdad);
        textFieldEdad.setText(String.valueOf(prenda.getEdad()));

        chckbxEstudiante = new JCheckBox("");
        chckbxEstudiante.setBounds(116, 147, 37, 25);
        contentPane.add(chckbxEstudiante);
        chckbxEstudiante.setSelected(prenda.getEstudiante());

        JLabel lblPais = new JLabel("País");
        lblPais.setBounds(167, 151, 60, 17);
        contentPane.add(lblPais);

        comboBoxPais = new JComboBox<>(new DefaultComboBoxModel<>(
            new String[]{"Argentina", "Brasil", "Chile", "Colombia", "México", "Perú", "Uruguay", "Venezuela"}
        ));
        comboBoxPais.setBounds(200, 146, 110, 26);
        contentPane.add(comboBoxPais);
        comboBoxPais.setSelectedIndex(prenda.getPaisIndex());

        JLabel lblGenero = new JLabel("Género");
        lblGenero.setBounds(220, 121, 60, 17);
        contentPane.add(lblGenero);

        rdbtnMale = new JRadioButton("M");
        rdbtnFemale = new JRadioButton("F");
        buttonGroup.add(rdbtnMale);
        buttonGroup.add(rdbtnFemale);
        rdbtnMale.setBounds(275, 117, 47, 25);
        rdbtnFemale.setBounds(326, 117, 47, 25);
        contentPane.add(rdbtnMale);
        contentPane.add(rdbtnFemale);
        if ("Masculino".equals(prenda.getSexo())) rdbtnMale.setSelected(true);
        if ("Femenino".equals(prenda.getSexo())) rdbtnFemale.setSelected(true);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                int edad;
                try {
                    String txtEdad = textFieldEdad.getText();
                    edad = Integer.parseInt((txtEdad != null && !txtEdad.isEmpty()) ? txtEdad : "0");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La edad es inválida");
                    textFieldEdad.requestFocus();
                    return;
                }

                boolean estudiante = chckbxEstudiante.isSelected();
                String sexo = rdbtnMale.isSelected() ? "Masculino" : (rdbtnFemale.isSelected() ? "Femenino" : null);
                String pais = (String) comboBoxPais.getSelectedItem();

                try {
                    VerificarPrenda.validar(nombre, apellido, edad, sexo, pais);
                    Prenda p = controlador.obtenerIndex(indexPrenda);
                    if (p == null) {
                        JOptionPane.showMessageDialog(null, "Prenda no encontrada.");
                    } else {
                        p.setNombre(nombre);
                        p.setApellido(apellido);
                        p.setEdad(edad);
                        p.setEstudiante(estudiante);
                        p.setSexo(sexo);
                        p.setPais(pais);
                        p.setPaisIndex(comboBoxPais.getSelectedIndex());
                        
                        controlador.modificarPrenda(prenda);
                    }
                    openUI();
                } catch (PrendaInvalidaException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        btnModificar.setBounds(42, 208, 106, 27);
        contentPane.add(btnModificar);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textFieldNombre.setText("");
                textFieldApellido.setText("");
                textFieldEdad.setText("");
                chckbxEstudiante.setSelected(false);
                comboBoxPais.setSelectedIndex(-1);
                buttonGroup.clearSelection();
        	}
        });
        btnBorrar.setBounds(267, 208, 106, 27);
        contentPane.add(btnBorrar);
    }

    private void openUI() throws SQLException {
        new PrendaVista(controlador).setVisible(true);
        dispose();
    }
}
