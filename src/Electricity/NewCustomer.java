package Electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NewCustomer extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField t1, t2, t3, t5, t6, t7;
    JButton b1, b2;
    Choice ch;
	JPanel jp;
    
    NewCustomer(){
        setLocation(600,200);
        setSize(700,500);
		this.setResizable(false);
        
		jp = new JPanel();
		jp.setBounds(30, 30, 700, 500);
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		jp.setForeground(new Color(34, 139, 34)); // r: g: b:
		jp.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), // ((r: g: b:), thickness)
		"Novo cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230))); // titleFont: null
		add(jp);
		
        JLabel title = new JLabel("Novo Cliente");
        title.setBounds(180, 30, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jp.add(title);
        
        l1 = new JLabel("Nome do cliente");
        l1.setBounds(60, 80, 100, 20);   
        t1 = new JTextField();
        t1.setBounds(200, 80, 200, 20);
        jp.add(l1);
        jp.add(t1);
        
        l2 = new JLabel("Nº do medidor");
        l2.setBounds(60, 120, 100, 20);
        l9 = new JLabel();
        l9.setBounds(200, 120, 200, 20);
        jp.add(l2);
        jp.add(l9);
        
        l3 = new JLabel("Endereço");
        l3.setBounds(60, 160, 100, 20);
        t3 = new JTextField();
        t3.setBounds(200, 160, 200, 20);
        jp.add(l3);
        jp.add(t3);
        
        l4 = new JLabel("Estado");
        l4.setBounds(60, 200, 100, 20);
        ch = new Choice();
        ch.add("Acre");
        ch.add("Alagoas");
        ch.add("Amapá");
        ch.add("Amazonas");
        ch.add("Bahia");
        ch.add("Ceará");
        ch.add("Distrito Federal");
        ch.add("Espírito Santo");
        ch.add("Goiás");
        ch.add("Maranhão");
        ch.add("Mato Grosso");
        ch.add("Mato Grosso do Sul");
        ch.add("Minas Gerais");
        ch.add("Pará");
        ch.add("Paraíba");
        ch.add("Paraná");
        ch.add("Pernambuco");
        ch.add("Piauí");
        ch.add("Rio de Janeiro");
        ch.add("Rio Grande do Norte");
        ch.add("Rio Grande do Sul");
        ch.add("Rondônia");
        ch.add("Roraima");
        ch.add("Santa Catarina");
        ch.add("São Paulo");
        ch.add("Sergipe");
        ch.add("Tocantins");
        ch.setBounds(200, 200, 200, 20);
		ch.setForeground(Color.BLACK);
        jp.add(l4);
        jp.add(ch);
        
        l5 = new JLabel("Cidade");
        l5.setBounds(60, 240, 100, 20);
        t5 = new JTextField();
        t5.setBounds(200, 240, 200, 20);
    	jp.add(l5);
    	jp.add(t5);
        
        l6 = new JLabel("Email");
        l6.setBounds(60, 280, 100, 20);
        t6 = new JTextField();
        t6.setBounds(200, 280, 200, 20);
        jp.add(l6);
        jp.add(t6);
        
        l7 = new JLabel("Nº de celular");
        l7.setBounds(60, 320, 100, 20);
        t7 = new JTextField();
        t7.setBounds(200, 320, 200, 20);
        jp.add(l7);
        jp.add(t7);
        
        b1 = new JButton("Próximo");
        b1.setBounds(120, 390, 100, 25);
        b2 = new JButton("Cancelar");
        b2.setBounds(250, 390, 100, 25);
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);   
        
        jp.add(b1);
        jp.add(b2);
        
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/NCicon.png"));
		Image img2 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l8 = new JLabel(img3);
		l8.setBounds(425, 100, 250, 250);
		jp.add(l8);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        // Pegar um número aleatório de medidor
        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        l9.setText(""+Math.abs(first));
        
    }
    public void actionPerformed(ActionEvent actionEvent){
    	
        if (actionEvent.getSource() == b1){
        	
            String name = t1.getText();
            String meter = l9.getText();
            String address = t3.getText();
            String state = ch.getSelectedItem();
            String city = t5.getText();
            String email = t6.getText();
            String phone = t7.getText();

            String query1 = "insert into customer values('" + name + "','" + meter + "','" 
            		+ address + "','" + state + "','" + city + "','" 
            		+ email + "','" + phone + "')";
            String query2 = "insert into login values('" + meter + "', '', '', '', '')";
            
            try{
                Conn c1 = new Conn();
                c1.st.executeUpdate(query1);
                c1.st.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Detalhes do cliente adicionados com sucesso!");
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);
            }
            catch(Exception e){
                 e.printStackTrace();
            }
        }
        else if(actionEvent.getSource() == b2) {
                this.setVisible(false);
        }
    }
    
    
    public static void main(String[] args){
        new NewCustomer().setVisible(true);
    }
}