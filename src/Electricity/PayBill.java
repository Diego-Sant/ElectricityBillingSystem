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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PayBill extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1, l2, l3, l4, l5, l7, l8, l9, l10, l11, l12;
	JTextField tf;
	Choice c1, c2;
	JButton b1, b2;
	String meter;
	JPanel jp;
	
	PayBill(String meter) {
        setLocation(600, 200);
        setSize(900, 600);
		
		jp = new JPanel();
		jp.setBounds(30, 30, 900, 600);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Pagar fatura", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
		JLabel title = new JLabel("Pagar fatura");
		title.setBounds(230, 30, 400, 30);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jp.add(title);
		
		l1 = new JLabel("Nº do medidor");
		l1.setBounds(35, 80, 200, 20);
		jp.add(l1);
		
		l2 = new JLabel();
		l2.setBounds(300, 80, 200, 20);
		jp.add(l2);
		
		l3 = new JLabel("Nome");
		l3.setBounds(35, 140, 200, 20);
		jp.add(l3);
		
		l4 = new JLabel();
		l4.setBounds(300, 140, 200, 20);
		jp.add(l4);
		
		l5 = new JLabel("Mês");
		l5.setBounds(35, 200, 200, 20);
		jp.add(l5);
		
		c1 = new Choice();
		c1.setBounds(300, 200, 200, 20);
		c1.add("Janeiro");
		c1.add("Fevereiro");
		c1.add("Março");
		c1.add("Abril");
		c1.add("Maio");
		c1.add("Junho");
		c1.add("Julho");
		c1.add("Agosto");
		c1.add("Setembro");
		c1.add("Outubro");
		c1.add("Novembro");
		c1.add("Dezembro");
		c1.setForeground(Color.BLACK);
		jp.add(c1);
		
		l7 = new JLabel("Unidades");
		l7.setBounds(35, 260, 200, 20);
		jp.add(l7);
		
		l8 = new JLabel();
		l8.setBounds(300, 260, 200, 20);
		jp.add(l8);
		
		l9 = new JLabel("Fatura total");
		l9.setBounds(35, 320, 200, 20);
		jp.add(l9);
		
		l10 = new JLabel();
		l10.setBounds(300, 320, 200, 20);
		jp.add(l10);
		
		l11 = new JLabel("Status");
		l11.setBounds(35, 380, 200, 20);
		jp.add(l11);
		
		l12 = new JLabel();
		l12.setBounds(300, 380, 200, 20);
		l12.setForeground(Color.RED);
		jp.add(l12);
		
		try {
            Conn c = new Conn();
            ResultSet rs = c.st.executeQuery("SELECT * FROM customer WHERE meter = '" + meter + "'");
            
            while(rs.next()) {
            	l2.setText(rs.getString("meter"));
            	l4.setText(rs.getString("name"));
            }
            
            rs = c.st.executeQuery("SELECT * FROM bill WHERE meter = '" + meter + "' AND month = 'Janeiro' ");
		
            while(rs.next()) {
            	l8.setText(rs.getString("units"));
            	l10.setText(rs.getString("total_bill"));
            	l12.setText(rs.getString("status"));
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		c1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ie) {
				try {
					Conn c = new Conn();
					ResultSet rs = c.st.executeQuery("SELECT * FROM bill WHERE meter = '" 
							+ meter + "' AND month = '" + c1.getSelectedItem() + "'");
				
					while(rs.next()) {
		            	l8.setText(rs.getString("units"));
		            	l10.setText(rs.getString("total_bill"));
		            	l12.setText(rs.getString("status"));
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		b1 = new JButton("Pagar");
		b1.setBounds(290, 460, 100, 25);
		jp.add(b1);
		
		b2 = new JButton("Voltar");
		b2.setBounds(160, 460, 100, 25);
		jp.add(b2);
		
		b1.setBackground(Color.BLACK);
		b2.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b2.setForeground(Color.WHITE);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/PBicon.png"));
		Image img2 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l13 = new JLabel(img3);
		l13.setBounds(400, 120, 600, 300);
		jp.add(l13);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == b1) {
			try {
				Conn c = new Conn();
				c.st.executeQuery("UPDATE bill status = 'Pago' WHERE meter = '" 
						+ meter + "' AND month = '" + c1.getSelectedItem() + "'");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			this.setVisible(false);
			new Paytm(meter).setVisible(true);
		} else if (actionEvent.getSource() == b2) {
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new PayBill("").setVisible(true);
	}

}
