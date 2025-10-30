package UI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Exe.GestorPersona;
import Exe.PersonaInvalidaException;
import Exe.VerificarPersona;
import Model.Persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UI2 extends JFrame {
    private static final long serialVersionUID = 1L;

    private final GestorPersona gestor;
    private final int indexPersona;

    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEdad;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JComboBox<String> comboBoxPais;
    private JRadioButton rdbtnMale;
    private JRadioButton rdbtnFemale;
    private JCheckBox chckbxEstudiante;

    public UI2(GestorPersona gestor, int indexPersona) throws SQLException {
        this.gestor = gestor;
        this.indexPersona = indexPersona;
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

        Persona persona = gestor.obtenerIndex(indexPersona);
        if (persona == null) {
            JOptionPane.showMessageDialog(this, "Persona no encontrada.");
            openUI();
            return;
        }

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(106, 61, 114, 21);
        contentPane.add(textFieldNombre);
        textFieldNombre.setText(persona.getNombre());

        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(106, 92, 114, 21);
        contentPane.add(textFieldApellido);
        textFieldApellido.setText(persona.getApellido());

        textFieldEdad = new JTextField();
        textFieldEdad.setBounds(106, 119, 37, 21);
        contentPane.add(textFieldEdad);
        textFieldEdad.setText(String.valueOf(persona.getEdad()));

        chckbxEstudiante = new JCheckBox("");
        chckbxEstudiante.setBounds(116, 147, 37, 25);
        contentPane.add(chckbxEstudiante);
        chckbxEstudiante.setSelected(persona.getEstudiante());

        JLabel lblPais = new JLabel("País");
        lblPais.setBounds(167, 151, 60, 17);
        contentPane.add(lblPais);

        comboBoxPais = new JComboBox<>(new DefaultComboBoxModel<>(
            new String[]{"Argentina", "Brasil", "Chile", "Colombia", "México", "Perú", "Uruguay", "Venezuela"}
        ));
        comboBoxPais.setBounds(200, 146, 110, 26);
        contentPane.add(comboBoxPais);
        comboBoxPais.setSelectedIndex(persona.getPaisIndex());

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
        if ("Masculino".equals(persona.getSexo())) rdbtnMale.setSelected(true);
        if ("Femenino".equals(persona.getSexo())) rdbtnFemale.setSelected(true);

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
                    VerificarPersona.validar(nombre, apellido, edad, sexo, pais);
                    Persona p = gestor.obtenerIndex(indexPersona);
                    if (p == null) {
                        JOptionPane.showMessageDialog(null, "Persona no encontrada.");
                    } else {
                        p.setNombre(nombre);
                        p.setApellido(apellido);
                        p.setEdad(edad);
                        p.setEstudiante(estudiante);
                        p.setSexo(sexo);
                        p.setPais(pais);
                        p.setPaisIndex(comboBoxPais.getSelectedIndex());
                        
                        gestor.modificarPersona(persona);
                    }
                    openUI();
                } catch (PersonaInvalidaException | SQLException ex) {
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
        new UI(gestor).setVisible(true);
        dispose();
    }
}
