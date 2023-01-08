package Electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class CalculateBill extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JTextField tf;
	Choice c1, c2;
	JButton b1, b2;
	JPanel jp;
	
	CalculateBill() {
        setLocation(550, 220);
        setSize(750, 500);
		this.setResizable(false);
		
		jp = new JPanel();
		jp.setBounds(30, 30, 700, 500);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Calcular fatura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
		l1 = new JLabel("Calcular fatura");
		l1.setBounds(30, 30, 400, 30);
		
		l2 = new JLabel("Nº do medidor");
		l2.setBounds(60, 90, 100, 30);
		jp.add(l2);
		
		l3 = new JLabel("Nome");
		l3.setBounds(60, 140, 100, 30);
		jp.add(l3);
		
		l4 = new JLabel("Endereço");
		l4.setBounds(60, 190, 100, 30);
		jp.add(l4);
		
		l5 = new JLabel("Unidades consumidas");
		l5.setBounds(60, 240, 100, 30);
		jp.add(l5);
		
		l6 = new JLabel("Mês");
		l6.setBounds(60, 290, 100, 30);
		jp.add(l6);
		
		c1 = new Choice();
		c1.setBounds(200, 90, 180, 20);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.st.executeQuery("SELECT * FROM customer");
			
			while (rs.next()) {
				c1.add(rs.getString("meter")); // Pegando no MySQL
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		l7 = new JLabel();
		l7.setBounds(200, 140, 180, 20);
		jp.add(l7);
		
		l8 = new JLabel();
		l8.setBounds(200, 190, 180, 20);
		jp.add(l8);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.st.executeQuery("SELECT * FROM customer WHERE meter = '"
					+ c1.getSelectedItem() + "'");
			
			while (rs.next()) {
				l7.setText(rs.getString("name")); // Pegando no MySQL
				l8.setText(rs.getString("address")); // Pegando no MySQL
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		c1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Conn c = new Conn();
					ResultSet rs = c.st.executeQuery("SELECT * FROM customer WHERE meter = '"
							+ c1.getSelectedItem() + "'");
					
					while (rs.next()) {
						l7.setText(rs.getString("name")); // Pegando no MySQL
						l8.setText(rs.getString("address")); // Pegando no MySQL
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		tf = new JTextField();
		tf.setBounds(200, 240, 180, 20);
		jp.add(tf);
		
		c2 = new Choice();
		c2.setBounds(200, 290, 180, 20);
		c2.add("Janeiro");
		c2.add("Fevereiro");
		c2.add("Março");
		c2.add("Abril");
		c2.add("Maio");
		c2.add("Junho");
		c2.add("Julho");
		c2.add("Agosto");
		c2.add("Setembro");
		c2.add("Outubro");
		c2.add("Novembro");
		c2.add("Dezembro");
		
		c2.setForeground(Color.BLACK);
		c1.setForeground(Color.BLACK);
		
		b1 = new JButton("Enviar");
		b1.setBounds(100, 370, 100, 25);
		b2 = new JButton("Cancel");
		b2.setBounds(230, 370, 100, 25);
		
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		
		jp.add(b1);
		jp.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/CBicon.png"));
		Image img2 = img1.getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l9 = new JLabel(img3);
		l9.setBounds(425, 70, 250, 250);
		jp.add(l9);
		
		l1.setFont(new Font("Senserif", Font.PLAIN, 26));
		l1.setHorizontalAlignment(JLabel.CENTER);
		
		jp.add(l1);
		jp.add(c1);
		jp.add(c2);
		
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == b1) {
			String meter_no = c1.getSelectedItem();
			String units = tf.getText();
			String month = c2.getSelectedItem();
			
			int units_consumed = Integer.parseInt(units);
			int total_bill = 0;
			
			try {
				Conn c = new Conn();
				ResultSet rs = c.st.executeQuery("SELECT * FROM tax");
				
				while(rs.next()) {
					total_bill = units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
					total_bill += Integer.parseInt(rs.getString("meter_rent"));
					total_bill += Integer.parseInt(rs.getString("service_charge"));
					total_bill += Integer.parseInt(rs.getString("service_tax"));
					total_bill += Integer.parseInt(rs.getString("iss"));
					total_bill += Integer.parseInt(rs.getString("fixed_tax"));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			String query1 = "INSERT INTO bill VALUES('" + meter_no + "','" 
					+ month + "','" + units + "','" + total_bill 
					+ "', 'não pago')";
			
			try {
				Conn c = new Conn();
				c.st.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "Fatura do cliente atualizada com sucesso!");
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
		new CalculateBill().setVisible(true);
	}

}
