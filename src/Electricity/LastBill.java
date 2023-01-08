package Electricity;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LastBill extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1;
	JTextArea t1, t2;
	JButton bttn;
	JPanel jp;
	
	LastBill() {
		setSize(500, 900);
		setLayout(new BorderLayout());
		this.setResizable(false);
		
		jp = new JPanel();
		l1 = new JLabel("Gerar fatura");
		t1 = new JTextArea();
		
		t2 = new JTextArea(50, 15);
		JScrollPane sp = new JScrollPane(t2);
		t2.setFont(new Font("Senserif", Font.ITALIC, 18));
		
		bttn = new JButton("Gerar fatura");
		
		jp.add(l1);
		jp.add(t1);
		
		add(jp, "North");
		add(sp, "Center");
		add(bttn, "South");
		
		bttn.addActionListener(this);
		
		setLocation(350, 40);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			Conn c = new Conn();
			
			ResultSet rs = c.st.executeQuery("SELECT * FROM customer WHERE meter= " + t1.getSelectedText());
		
			if (rs.next()) {
				t2.append("\n Nome do cliente: " + rs.getString("name"));
				t2.append("\n Nº do medidor: " + rs.getString("meter"));
				t2.append("\n Endereço: " + rs.getString("address"));
				t2.append("\n Estado: " + rs.getString("state"));
				t2.append("\n Cidade: " + rs.getString("city"));
				t2.append("\n Email: " + rs.getString("email"));
				t2.append("\n Nº de celular: " + rs.getString("phone"));
                t2.append("\n-------------------------------------------------------------");
                t2.append("\n");
			}
			
			t2.append("Detalhes da última fatura\n\n\n");
			
			rs = c.st.executeQuery("SELECT * FROM bill WHERE meter=" + t1.getSelectedText());
			
			while (rs.next()) {
				t2.append("       " + rs.getString("month") + "           "
						+ rs.getString("amount") + "\n");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new LastBill().setVisible(true);
	}

}
