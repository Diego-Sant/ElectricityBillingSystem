package Electricity;

import java.awt.BorderLayout;
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

public class NewCustomer extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JButton b1, b2;
    
    NewCustomer(){
        setLocation(600,200);
        setSize(700,500);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        p.setBackground(new Color(173,216,230));
        
        JLabel title = new JLabel("Novo cliente");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);
        
        l1 = new JLabel("Nome do cliente");
        l1.setBounds(100, 80, 100, 20);   
        t1 = new JTextField();
        t1.setBounds(240, 80, 200, 20);
        p.add(l1);
        p.add(t1);
        
        l2 = new JLabel("Nº do medidor");
        l2.setBounds(100, 120, 100, 20);
        l9 = new JLabel();
        l9.setBounds(240, 120, 200, 20);
        p.add(l2);
        p.add(l9);
        
        l3 = new JLabel("Endereço");
        l3.setBounds(100, 160, 100, 20);
        t3 = new JTextField();
        t3.setBounds(240, 160, 200, 20);
        p.add(l3);
        p.add(t3);
        
        l4 = new JLabel("Cidade");
        l4.setBounds(100, 200, 100, 20);
        t4 = new JTextField();
        t4.setBounds(240, 200, 200, 20);
        p.add(l4);
        p.add(t4);
        
        l5 = new JLabel("Estado");
        l5.setBounds(100, 240, 100, 20);
        t5 = new JTextField();
        t5.setBounds(240, 240, 200, 20);
        p.add(l5);
        p.add(t5);
        
        l6 = new JLabel("Email");
        l6.setBounds(100, 280, 100, 20);
        t6 = new JTextField();
        t6.setBounds(240, 280, 200, 20);
        p.add(l6);
        p.add(t6);
        
        l7 = new JLabel("Nº de celular");
        l7.setBounds(100, 320, 100, 20);
        t7 = new JTextField();
        t7.setBounds(240, 320, 200, 20);
        p.add(l7);
        p.add(t7);
        
        b1 = new JButton("Próximo");
        b1.setBounds(120, 390, 100, 25);
        b2 = new JButton("Cancelar");
        b2.setBounds(250, 390, 100, 25);
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);   
        
        p.add(b1);
        p.add(b2);

        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/NCicon.png"));
        Image img2 = img1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        l8 = new JLabel(img3);
        add(l8, "West");
        
        getContentPane().setBackground(new Color(173,216,230));
        
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
            String state = t4.getText();
            String city = t5.getText();
            String email = t6.getText();
            String phone = t7.getText();

            String query1 = "INSERT INTO customer VALUES('" + name + "','" + meter + "','" 
            			+ address + "','" + city + "','" + state + "','" + email + "','" 
            			+ phone+ "')";
            String query2 = "INSERTO INTO login VALUES('" + meter + "', '', '', '', '')";
            
            try{
                Conn c1 = new Conn();
                c1.st.executeUpdate(query1);
                c1.st.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Detalhes do cliente adicionados com sucesso!");
                this.setVisible(false);
                //new MeterInfo(meter).setVisible(true);
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