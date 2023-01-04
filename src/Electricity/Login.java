package Electricity;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4;
	JTextField tf;
	JPasswordField psw;
	JButton b1, b2, b3;
	JPanel p1, p2, p3, p4;
	Choice ch;

	Login() {

		// setBounds (x:, y:, width:, height:)

		super("Login Page");
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		// Label do usuário
		l1 = new JLabel("Usuário:");
		l1.setBounds(300, 20, 100, 20);
		add(l1);

		// Label da senha
		l2 = new JLabel("Senha:");
		l2.setBounds(300, 60, 100, 20);
		add(l2);

		// Campo para preencher o usuário(máximo 15)
		tf = new JTextField(15);
		tf.setBounds(400, 20, 150, 20);
		add(tf);

		// Campo para preencher a senha(máximo 15)
		psw = new JPasswordField(15);
		psw.setBounds(400, 60, 150, 20);
		add(psw);

		// Label para escolhar como logar
		l3 = new JLabel("Logando como:");
		l3.setBounds(300, 100, 100, 20);
		add(l3);

		// Campo para escolher entre Administrador ou Cliente
		ch = new Choice();
		ch.add("Administrador");
		ch.add("Cliente");
		ch.setBounds(400, 100, 150, 20);
		add(ch);

		// Adicionar imagem de login
		ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
		Image img1 = imgIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		b1 = new JButton("Logar", new ImageIcon(img1));
		b1.setBounds(330, 160, 100, 20);
		add(b1);

		// Adicionar imagem de cancelar
		ImageIcon imgIcon2 = new ImageIcon(ClassLoader.getSystemResource("icons/cancel.png"));
		Image img2 = imgIcon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		b2 = new JButton("Cancelar", new ImageIcon(img2));
		b2.setBounds(450, 160, 100, 20);
		add(b2);

		// Adicionar imagem de cadastrar
		ImageIcon imgIcon3 = new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
		Image img3 = imgIcon3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		b3 = new JButton("Cadastrar", new ImageIcon(img3));
		b3.setBounds(380, 200, 130, 20);
		add(b3);

		// Ativar ActionListener nos botões
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		ImageIcon imgIcon4 = new ImageIcon(ClassLoader.getSystemResource("icons/businessman.png"));
		Image img4 = imgIcon4.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon imgIcon5 = new ImageIcon(img4);
		l4 = new JLabel(imgIcon5);
		l4.setBounds(5, 5, 250, 250);
		add(l4);

		setLayout(new BorderLayout());

		// Configurações da tela de Login
		setSize(640, 300); // width, height
		setLocation(600, 300);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == b1) {
			try {
				Conn c = new Conn();

				String a = tf.getText();
				String b = new String(psw.getPassword());
				String user = ch.getSelectedItem();

				String query = "SELECT * FROM login WHERE username = '"
						+ a + "' AND password = '"
						+ b + "' AND user = '"
						+ user + "'";

				ResultSet rs = c.st.executeQuery(query);
				if (rs.next()) {
					String meter = rs.getString("meter_no");
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Login inválido");
					tf.setText("");
					psw.setText("");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: " + e);
			}
		} else if (actionEvent.getSource() == b2) {
			this.setVisible(false);
		} else if (actionEvent.getSource() == b3) {
			this.setVisible(false);
			new Signup().setVisible(true);
		}
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}

}
