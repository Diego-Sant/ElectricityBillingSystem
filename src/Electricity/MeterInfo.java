package Electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MeterInfo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
	Choice c1, c2, c3, c4, c5;
	JButton b1, b2;
	JPanel jp;
	
	MeterInfo(String meter) {
        setLocation(600, 200);
        setSize(700, 500);
		
		jp = new JPanel();
		jp.setBounds(30, 30, 700, 500);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Informações do medidor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
        JLabel title = new JLabel("Info do medidor");
        title.setBounds(180, 30, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jp.add(title);
		
		l1 = new JLabel("Nº do medidor");
		l1.setBounds(100, 90, 100, 20);
		l11 = new JLabel(meter);
		l11.setBounds(240, 90, 200, 20);
		l11.setForeground(Color.BLACK);
		jp.add(l1);
		jp.add(l11);
		
		l2 = new JLabel("Local do medidor");
		l2.setBounds(100, 130, 100, 20);
		c1 = new Choice();
		c1.add("Externo");
		c1.add("Interno");
		c1.setBounds(240, 130, 200, 20);
		c1.setForeground(Color.BLACK);
		jp.add(l2);
		jp.add(c1);
		
		l3 = new JLabel("Tipo do medidor");
		l3.setBounds(100, 170, 100, 20);
		c2 = new Choice();
		c2.add("Medidor elétrico");
		c2.add("Medidor solar");
		c2.add("Medidor inteligente");
		c2.setBounds(240, 170, 200, 20);
		c2.setForeground(Color.BLACK);
		jp.add(l3);
		jp.add(c2);
		
		l4 = new JLabel("Código de fase");
		l4.setBounds(100, 210, 100, 20);
		c3 = new Choice();
		c3.add("011");
		c3.add("022");
		c3.add("033");
		c3.add("044");
		c3.add("055");
		c3.add("066");
		c3.add("077");
		c3.add("088");
		c3.add("099");
		c3.setBounds(240, 210, 200, 20);
		c3.setForeground(Color.BLACK);
		jp.add(l4);
		jp.add(c3);
		
		l5 = new JLabel("Tipo de fatura");
		l5.setBounds(100, 250, 100, 20);
		c4 = new Choice();
		c4.add("Normal");
		c4.add("Industrial");
		c4.setBounds(240, 250, 200, 20);
		c4.setForeground(Color.BLACK);
		jp.add(l5);
		jp.add(c4);
		
		l6 = new JLabel("Dias");
		l6.setBounds(100, 290, 100, 20);		
		l7 = new JLabel("30 dias");
		l7.setBounds(240, 290, 200, 20);
		jp.add(l6);
		jp.add(l7);
		
		l8 = new JLabel("Anotação");
		l8.setBounds(100, 330, 100, 20);
		l9 = new JLabel("Por padrão a fatura é calculada nos últimos 30 dias");
		l9.setBounds(240, 330, 300, 20);
		jp.add(l8);
		jp.add(l9);
		
		b1 = new JButton("Enviar");
		b1.setBounds(160, 400, 100, 25);
		b2 = new JButton("Cancelar");
		b2.setBounds(290, 400, 100, 25);
		
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		
		jp.add(b1);
		jp.add(b2);
		
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/MIicon.png"));
		Image img2 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l6 = new JLabel(img3);
		l6.setBounds(425, 70, 250, 250);
		jp.add(l6);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == b1) {
			String meter_number = l11.getText();
			String meter_location = c1.getSelectedItem();
			String meter_type = c2.getSelectedItem();
			String phase_code = c3.getSelectedItem();
			String bill_type = c4.getSelectedItem();
			String days = "30";
			
			String query1 = "INSERT INTO meter_info VALUES('"
					+ meter_number + "','" + meter_location
					+ "','" + meter_type + "','" + phase_code 
					+ "','" + bill_type + "','" + days + "')";
			
			try {
				Conn c = new Conn();
				c.st.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "Informações sobre medidor adicionados com sucesso!");
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
		new MeterInfo("").setVisible(true);
	}

}
