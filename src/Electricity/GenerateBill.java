package Electricity;

import java.awt.BorderLayout;
import java.awt.Choice;
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

public class GenerateBill extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JLabel l1, l2;
	JTextArea ta;
	JButton bttn;
	Choice ch;
	JPanel jp;
	String meter;
	
	GenerateBill(String meter) {
		this.meter = meter;
		this.setResizable(false);
		
		setSize(500, 900);
		setLayout(new BorderLayout());
		
		jp = new JPanel();
		
		l1 = new JLabel("Gerar fatura");
		l2 = new JLabel(meter);
		ch = new Choice();
		
		ch.add("Janeiro");
		ch.add("Fevereiro");
		ch.add("Março");
		ch.add("Abril");
		ch.add("Maio");
		ch.add("Junho");
		ch.add("Julho");
		ch.add("Agosto");
		ch.add("Setembro");
		ch.add("Outubro");
		ch.add("Novembro");
		ch.add("Dezembro");
		
		ta = new JTextArea(50, 15); // linhas, colunas
		ta.setText("\n\n Clique em gerar fatura para visualizar a fatura do mês\n selecionado.\n\n");
		JScrollPane sp = new JScrollPane(ta);
		ta.setFont(new Font("Senserif", Font.ITALIC, 18));
		
		bttn = new JButton("Gerar fatura");
		
		jp.add(l1);
		jp.add(l2);
		jp.add(ch);
		
		add(jp, "North");
		add(sp, "Center");
		add(bttn, "South");
		
		bttn.addActionListener(this);
		
		setLocation(750, 100);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			Conn c = new Conn();
			
			String month = ch.getSelectedItem();
            ta.setText("\tANEEL\nFatura elétrica para o mês de "+ month + " ,2022\n\n\n");
			
			ResultSet rs = c.st.executeQuery("select * from customer where meter='" + meter + "'");
		
			if (rs.next()) {
				ta.append("\n Nome do cliente: " + rs.getString("name"));
				ta.append("\n Nº do medidor: " + rs.getString("meter"));
				ta.append("\n Endereço: " + rs.getString("address"));
				ta.append("\n Estado: " + rs.getString("state"));
				ta.append("\n Cidade: " + rs.getString("city"));
				ta.append("\n Nº de celular: " + rs.getString("phone"));
				ta.append("\n-------------------------------------------------------------");
                ta.append("\n");
			}
			
			rs = c.st.executeQuery("select * from meter_info where meter_number = '" + meter + "'");
		
			if (rs.next()) {
				ta.append("\n Local do medidor: " + rs.getString("meter_location"));
				ta.append("\n Tipo do medidor: " + rs.getString("meter_type"));
				ta.append("\n Código de fase: " + rs.getString("phase_code"));
				ta.append("\n Tipo de fatura: " + rs.getString("bill_type"));
				ta.append("\n Dias: " + rs.getString("days"));
                ta.append("\n");
			}
			
            rs = c.st.executeQuery("SELECT * FROM tax");
            
            if (rs.next()) {
            	ta.append("\n-------------------------------------------------------------");
            	ta.append("\n Custo por unidade: R$ " + rs.getString("cost_per_unit"));
            	ta.append("\n Aluguel do medidor: R$ " + rs.getString("meter_rent"));
            	ta.append("\n Cobrança do serviço: R$ " + rs.getString("service_charge"));
            	ta.append("\n Taxa do serviço: R$ " + rs.getString("service_tax"));
            	ta.append("\n Taxa de ISS: R$ " + rs.getString("iss"));
            	ta.append("\n Taxa fixa: R$ " + rs.getString("fixed_tax"));
                ta.append("\n");
            }
            
            rs = c.st.executeQuery("select * from bill where meter=" + meter + " AND month = '" + ch.getSelectedItem() + "'");
		
            if (rs.next()) {
            	ta.append("\n Mês: \t" + rs.getString("month"));
            	ta.append("\n Unidades consumidas: \t" + rs.getString("units"));
            	ta.append("\n Fatura: \t" + rs.getString("total_bill"));
                ta.append("\n---------------------------------------------------------------");
            	ta.append("\n Valor total: R$\t" + rs.getString("total_bill"));
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GenerateBill("").setVisible(true);
	}

}
