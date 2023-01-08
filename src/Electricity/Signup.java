package Electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Signup extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JPanel jp;
	JTextField t1, t2, t3, t4;
	Choice ch;
	JButton b1, b2;

	// setBounds (x:, y:, width:, height:)

	Signup() {
		setBounds(600, 250, 700, 400);
		this.setResizable(false);

		jp = new JPanel();
		jp.setBounds(30, 30, 650, 300);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Criar conta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);

		JLabel l1 = new JLabel("Usuário:");
		l1.setForeground(Color.DARK_GRAY);
		l1.setFont(new Font("Tahoma", Font.BOLD, 14)); // nome da fonte, estilo e tamanho
		l1.setBounds(20, 70, 100, 20);
		jp.add(l1);

		t1 = new JTextField();
		t1.setBounds(220, 70, 150, 20);
		jp.add(t1);

		JLabel l2 = new JLabel("Nome completo:");
		l2.setForeground(Color.DARK_GRAY);
		l2.setFont(new Font("Tahoma", Font.BOLD, 14));
		l2.setBounds(20, 110, 100, 20);
		jp.add(l2);

		t2 = new JTextField();
		t2.setBounds(220, 110, 150, 20);
		jp.add(t2);

		JLabel l3 = new JLabel("Senha:");
		l3.setForeground(Color.DARK_GRAY);
		l3.setFont(new Font("Tahoma", Font.BOLD, 14));
		l3.setBounds(20, 150, 100, 20);
		jp.add(l3);

		t3 = new JTextField();
		t3.setBounds(220, 150, 150, 20);
		jp.add(t3);

		JLabel l4 = new JLabel("Criar conta como:");
		l4.setForeground(Color.DARK_GRAY);
		l4.setFont(new Font("Tahoma", Font.BOLD, 14));
		l4.setBounds(20, 190, 140, 20);
		jp.add(l4);

		JLabel l5 = new JLabel("Nº do medidor");
		l5.setForeground(Color.DARK_GRAY);
		l5.setFont(new Font("Tahoma", Font.BOLD, 14));
		l5.setBounds(20, 230, 100, 20);
		l5.setVisible(false);
		jp.add(l5);

		t4 = new JTextField();
		t4.setBounds(220, 230, 150, 20);
		t4.setVisible(false);
		jp.add(t4);

		ch = new Choice();
		ch.add("Administrador");
		ch.add("Cliente");
		ch.setBounds(220, 190, 150, 20);
		jp.add(ch);

		ch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String user = ch.getSelectedItem();

				// Caso selecione cliente aparecerá a opção de colocar o número do medidor
				if (user.equals("Cliente")) {
					l5.setVisible(true);
					t4.setVisible(true);
				} else {
					l5.setVisible(false);
					t4.setVisible(false);
				}
			}
		});

		b1 = new JButton("Criar");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setBounds(70, 290, 120, 30);
		b1.addActionListener(this);
		jp.add(b1);

		b2 = new JButton("Voltar");
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		b2.setBounds(230, 290, 120, 30);
		b2.addActionListener(this);
		jp.add(b2);

		ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/signupImage.png"));
		Image img2 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l6 = new JLabel(img3);
		l6.setBounds(425, 40, 250, 250);
		jp.add(l6);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// Configurações para o botão criar
		if (actionEvent.getSource() == b1) {
			// Caso todas as informações baterem
			String username = t1.getText();
			String name = t2.getText();
			String password = t3.getText();
			String user = ch.getSelectedItem();
			String meter = t4.getText();
			
			try {
				Conn c = new Conn();
				String str = null;
				
				// meter será selecionado automaticamente caso escolha a opção "Administrador"
				if(user.equals("Administrador")) {
                    str = "INSERT INTO login VALUES"
                    		+ "('" + meter + "', '" + username + "', '" 
                    		+ name + "', '" + password + "', '" + user + "')";
				} else {
                    str = "UPDATE login SET username = '" 
                    		+ username + "', name = '" + name + "', password = '"
                    		+ password + "', user = '" + user + "' "
                    		+ "WHERE meter_no = '" + t4.getText() + "'";
				}
				
				c.st.executeUpdate(str);
				JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
				this.setVisible(false);
				new Login().setVisible(true);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		// Caso escolha o botão b2(voltar)
		} else if (actionEvent.getSource() == b2) {
			this.setVisible(false);
			new Login().setVisible(true); // Voltar para tela de Login
		}
	}

	public static void main(String[] args) {
		new Signup().setVisible(true);
	}

}
