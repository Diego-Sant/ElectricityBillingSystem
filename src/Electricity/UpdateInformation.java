package Electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class UpdateInformation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JTextField t1, t2, t3, t4, t5, t6;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
	JButton b1, b2;
	String meter;
	JPanel jp;
	Choice ch;
	
	UpdateInformation(String meter) {
		this.meter = meter;
		
        setLocation(600, 200);
        setSize(950, 450);
		
		jp = new JPanel();
		jp.setBounds(30, 30, 950, 450);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Atualizar informações", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
		JLabel title = new JLabel("Atualizar informações do cliente");
		title.setBounds(110, 30, 400, 30);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jp.add(title);
		
		l1 = new JLabel("Nome");
		l1.setBounds(30, 70, 100, 20);
		jp.add(l1);
		
		l11 = new JLabel();
		l11.setBounds(230, 70, 200, 20);
		jp.add(l11);
		
		l2 = new JLabel("N° do medidor");
		l2.setBounds(30, 110, 100, 20);
		jp.add(l2);
		
		l12 = new JLabel();
		l12.setBounds(230, 110, 200, 20);
		jp.add(l12);
		
		l3 = new JLabel("Endereço");
		l3.setBounds(30, 150, 100, 20);
		jp.add(l3);
		
		t1 = new JTextField();
		t1.setBounds(230, 150, 200, 20);
		jp.add(t1);
		
		l4 = new JLabel("Estado");
		l4.setBounds(30, 190, 100, 20);
		jp.add(l4);
		
        ch = new Choice();
        ch.add("Acre");
        ch.add("Alagoas");
        ch.add("Amapá");
        ch.add("Amazonas");
        ch.add("Bahia");
        ch.add("Ceará");
        ch.add("Distrito Federal");
        ch.add("Espírito Santo");
        ch.add("Goiás");
        ch.add("Maranhão");
        ch.add("Mato Grosso");
        ch.add("Mato Grosso do Sul");
        ch.add("Minas Gerais");
        ch.add("Pará");
        ch.add("Paraíba");
        ch.add("Paraná");
        ch.add("Pernambuco");
        ch.add("Piauí");
        ch.add("Rio de Janeiro");
        ch.add("Rio Grande do Norte");
        ch.add("Rio Grande do Sul");
        ch.add("Rondônia");
        ch.add("Roraima");
        ch.add("Santa Catarina");
        ch.add("São Paulo");
        ch.add("Sergipe");
        ch.add("Tocantins");
        ch.setBounds(230, 190, 200, 20);
		ch.setForeground(Color.BLACK);
		jp.add(ch);
		
		l5 = new JLabel("Cidade");
		l5.setBounds(30, 230, 100, 20);
		jp.add(l5);
		
		t2 = new JTextField();
		t2.setBounds(230, 230, 200, 20);
		jp.add(t2);
		
		l6 = new JLabel("Email");
		l6.setBounds(30, 270, 100, 20);
		jp.add(l6);
		
		t3 = new JTextField();
		t3.setBounds(230, 270, 200, 20);
		jp.add(t3);
		
		l7 = new JLabel("N° de celular");
		l7.setBounds(30, 310, 100, 20);
		jp.add(l7);
		
		t4 = new JTextField();
		t4.setBounds(230, 310, 200, 20);
		jp.add(t4);
		
		b1 = new JButton("Atualizar");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setBounds(260, 360, 100, 25);
		b1.addActionListener(this);
		jp.add(b1);
		
		b2 = new JButton("Voltar");
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		b2.setBounds(100, 360, 100, 25);
		b2.addActionListener(this);
		jp.add(b2);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.st.executeQuery("SELECT * FROM customer WHERE meter = '" + meter + "'");
		
			while (rs.next()) {
				l11.setText(rs.getString(1));
				l12.setText(rs.getString(2));
				t1.setText(rs.getString(3));
				ch.addItem(rs.getString(4));
				t2.setText(rs.getString(5));
				t3.setText(rs.getString(6));
				t4.setText(rs.getString(7));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/UIicon.png"));
		Image img2 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		l8 = new JLabel(img3);
		l8.setBounds(500, 50, 400, 300);
		jp.add(l8);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {	
		if (actionEvent.getSource() == b1) {
			String s1 = l11.getText();
			String s2 = l12.getText();
			String s3 = t1.getText();
			String s4 = ch.getSelectedItem();
			String s5 = t2.getText();
			String s6 = t3.getText();
			String s7 = t4.getText();
			
			try {
				Conn c = new Conn();
				c.st.executeUpdate("UPDATE customer SET address = '" 
						+ s3 + "', city = '" + s4 + "', state = '" + s5 + "', email = '" 
						+ s6 + "', phone = '" + s7 + "' where meter = '" + meter + "'");
				JOptionPane.showMessageDialog(null, "Detalhes do cliente atualizados com sucesso!");
				this.setVisible(false);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (actionEvent.getSource() == b2) {
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new UpdateInformation("").setVisible(true);
	}

}
