package Electricity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomerDetails extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JTable tb;
	JButton bttn;
	// Nome de cada tabela
	String x[] = {"Nome do cliente", "Nº do medidor", "Endereço", "Cidade", "Estado", "Email", "Nº de celular"};
	String y[][] = new String[40][8]; //8 tabelas contendo 40 linhas
	
	int i=0, j=0;
	CustomerDetails() {
		super("Detalhes dos clientes");
		setSize(1200, 650);
		setLocation(400, 150);
		
		try {
			Conn c = new Conn();
			String str = "SELECT * FROM customer";
			ResultSet rs = c.st.executeQuery(str);
			
			// Pegar no banco de dados(Está em inglês)
			while(rs.next()) {
				y[i][j++] = rs.getString("name");
				y[i][j++] = rs.getString("meter");
				y[i][j++] = rs.getString("address");
				y[i][j++] = rs.getString("city");
				y[i][j++] = rs.getString("state");
				y[i][j++] = rs.getString("email");
				y[i][j++] = rs.getString("phone");
				// Configuração de looping para ir avançando por tabela e quando chegar no final voltar ao 0
				i++;
				j=0;
			}
			tb = new JTable(y, x);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		bttn = new JButton("Imprimir");
		add(bttn, "South");
		
		JScrollPane sp = new JScrollPane(tb);
		add(sp);
		
		bttn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			tb.print();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new CustomerDetails().setVisible(true);
	}
	
}
