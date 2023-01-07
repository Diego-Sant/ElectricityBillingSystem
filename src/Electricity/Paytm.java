package Electricity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Paytm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	String meter;
	JButton b1;
	JPanel jp;
	
	Paytm(String meter) {
		this.meter = meter;
		
        JEditorPane ep = new JEditorPane();
        ep.setEditable(false);  
		
        setLocation(600, 200);
        setSize(800, 800);
		
		jp = new JPanel();
		jp.setBounds(30, 30, 800, 800);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		null, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
		b1 = new JButton("Voltar");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(500, 20, 120, 25);
        b1.addActionListener(this);
        jp.add(b1);
        
        try {
        	ep.setPage("https://paytm.com/electricity-bill-payment");
        }
        catch (Exception e) {
        	ep.setContentType("text/html");
        	ep.setText("<html>Não foi possível carregar</html>");
        }
        
        JScrollPane scrollPane = new JScrollPane(jp);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800, 600));
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.setVisible(false);
		new PayBill(meter).setVisible(true);
	}
	
	public static void main(String[] args) {
		new Paytm("").setVisible(true);
	}

}
