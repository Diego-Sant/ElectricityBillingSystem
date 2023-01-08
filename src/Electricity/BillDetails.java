package Electricity;
import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JTable tb;
	String x[] = {"Nº do medidor", "Mês", "Unidades", "Fatura total", "Status"};
	String y[][] = new String[40][8];
	
	int i=0, j=0;
	
	BillDetails(String meter) {
		super("Detalhes da fatura");
		
		setSize(700, 650);
		setLocation(600, 150);
		setLayout(null);
		this.setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		
		tb = new JTable(y, x);
		
		try {
			Conn c = new Conn();
			String s1 = "SELECT * FROM bill WHERE meter = '" + meter + "'";
			ResultSet rs = c.st.executeQuery(s1);
			
			tb.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane sp = new JScrollPane(tb);
		sp.setBounds(0, 0, 700, 650);
		add(sp);
	}
	
	public static void main(String[] args) {
		new BillDetails("").setVisible(true);
	}

}
