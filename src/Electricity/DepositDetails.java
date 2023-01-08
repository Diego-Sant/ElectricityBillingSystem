package Electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JTable tb;
	JButton b1, b2;
	JLabel l1, l2;
	Choice c1, c2;
	String x[] = {"Nº do medidor", "Mês", "Unidades", "Fatura total", "Status"};
	String y[][] = new String[40][8];
	
	int i=0, j=0;
	DepositDetails() {
		super("Detalhes dos depósitos");
		setSize(700, 750);
		setLocation(600, 150);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		this.setResizable(false);
		
		l1 = new JLabel("Ordenar por nº do medidor");
		l1.setBounds(20, 20, 150, 20);
		add(l1);
		
		c1 = new Choice();
		
		l2 = new JLabel("Ordenar por mês");
		l2.setBounds(400, 20, 100, 20);
		add(l2);
		
		c2 = new Choice();
		
		tb = new JTable(y, x);
		
		try {
			Conn c = new Conn();
			String str = "SELECT * FROM bill";
			ResultSet rs = c.st.executeQuery(str);
			
			tb.setModel(DbUtils.resultSetToTableModel(rs));
			
			String str2 = "SELECT * FROM customer";
			rs = c.st.executeQuery(str2);
			
			while (rs.next()) {
				c1.add(rs.getString("meter")); // coluna dentro do customer
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		c1.setBounds(180, 20, 150, 20);
		add(c1);
		
		c2.setBounds(520, 20, 150, 20);
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
		add(c2);
		
		b1 = new JButton("Pesquisar");
		b1.setBounds(20, 70, 80, 20);
		b1.addActionListener(this);
		add(b1);
		
		b2 = new JButton("Imprimir");
		b2.setBounds(120, 70, 80, 20);
		b2.addActionListener(this);
		add(b2);
		
		JScrollPane sp = new JScrollPane(tb);
		sp.setBounds(0, 100, 700, 650);
		add(sp);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == b1) { //Pesquisar
			
			String str = "SELECT * FROM bill WHERE meter = '" 
					+ c1.getSelectedItem() + "' AND month = '"
					+ c2.getSelectedItem() + "'";
			
			try {
				Conn c = new Conn();
				ResultSet rs = c.st.executeQuery(str);
				tb.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (actionEvent.getSource() == b2) { // Imprimir
			try {
				tb.print();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new DepositDetails().setVisible(true);
	}

}
