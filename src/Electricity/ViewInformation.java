package Electricity;

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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ViewInformation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14;
	JButton b1;
	JPanel jp;
	
	ViewInformation(String meter) {
		this.setResizable(false);
		
        setLocation(600, 200);
        setSize(850, 650);
		
		jp = new JPanel();
		jp.setBounds(30, 30, 850, 650);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Ver informações", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
		JLabel title = new JLabel("Ver informações");
		title.setBounds(250, 30, 500, 40);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jp.add(title);
		
		l1 = new JLabel("Nome");
		l1.setBounds(70, 80, 100, 20);
		jp.add(l1);
		
		l2 = new JLabel();
		l2.setBounds(250, 80, 100, 20);
		jp.add(l2);
		
		l3 = new JLabel("Nº do medidor");
		l3.setBounds(70, 140, 100, 20);
		jp.add(l3);
		
		l4 = new JLabel();
		l4.setBounds(250, 140, 100, 20);
		jp.add(l4);
		
		l5 = new JLabel("Endereço");
		l5.setBounds(70, 200, 100, 20);
		jp.add(l5);
		
		l6 = new JLabel();
		l6.setBounds(250, 200, 100, 20);
		jp.add(l6);
		
		l7 = new JLabel("Estado");
		l7.setBounds(70, 260, 100, 20);
		jp.add(l7);
		
		l8 = new JLabel();
		l8.setBounds(250, 260, 100, 20);
		jp.add(l8);
		
		l9 = new JLabel("Cidade");
		l9.setBounds(70, 320, 100, 20);
		jp.add(l9);
		
		l10 = new JLabel();
		l10.setBounds(250, 320, 100, 20);
		jp.add(l10);
		
		l11 = new JLabel("Email");
		l11.setBounds(70, 380, 100, 20);
		jp.add(l11);
		
		l12 = new JLabel();
		l12.setBounds(250, 380, 100, 20);
		jp.add(l12);
		
		l13 = new JLabel("Nº de celular");
		l13.setBounds(70, 440, 100, 20);
		jp.add(l13);
		
		l14 = new JLabel();
		l14.setBounds(250, 440, 100, 20);
		jp.add(l14);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.st.executeQuery("SELECT * FROM customer WHERE meter  = '" + meter + "'");
		
			while(rs.next()) {
				l2.setText(rs.getString(1));
				l4.setText(rs.getString(2));
				l6.setText(rs.getString(3));
				l8.setText(rs.getString(4));
				l10.setText(rs.getString(5));
				l12.setText(rs.getString(6));
				l14.setText(rs.getString(7));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
        b1 = new JButton("Voltar");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(220, 520, 100, 25);
        b1.addActionListener(this);
        jp.add(b1);
        
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/VIicon.png"));
		Image img2 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l15 = new JLabel(img3);
		l15.setBounds(350, 120, 600, 300);
		jp.add(l15);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		new ViewInformation("").setVisible(true);
	}
	
}
