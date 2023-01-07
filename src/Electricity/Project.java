package Electricity;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Project extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	String meter;
	Project(String meter, String person) {
		super("Sistema de Faturamento de Eletricidade"); // title
		this.meter = meter;
		
		setSize(1920, 1030); // width, height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/Elect1.jpg"));
		Image img2 = img1.getImage().getScaledInstance(1900, 950, Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l1 = new JLabel(img3);
		add(l1);
		
		// Primeira coluna caso seja adm
		JMenuBar jmb = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem m1 = new JMenuItem("Novo cliente");
		JMenuItem m2 = new JMenuItem("Detalhes dos clientes");
		JMenuItem m3 = new JMenuItem("Detalhes dos depósitos");
		JMenuItem m4 = new JMenuItem("Calcular contas");
		menu.setForeground(Color.BLUE);
		
		// Novo cliente menu e icon
		m1.setFont(new Font("monospaced", Font.PLAIN, 12));
		ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/icon1.png"));
		Image image2 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		m1.setIcon(new ImageIcon(image2));
		m1.setMnemonic('D');
		m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK)); // Comando para utilizar shortcuts (CTRL + D)
		m1.setBackground(Color.WHITE);
		
		// Detalhes do cliente menu e icon
        m2.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icons/icon2.png"));
        Image image3 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        m2.setIcon(new ImageIcon(image3));
        m2.setMnemonic('M');
        m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        m2.setBackground(Color.WHITE);
        
        // Detalhes do depósito menu e icon
        m3.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icons/icon3.png"));
        Image image4 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        m3.setIcon(new ImageIcon(image4));
        m3.setMnemonic('N');
        m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        m3.setBackground(Color.WHITE);
        
        // Calcular contas menu e icon
        m4.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icons/icon4.png"));
        Image image5 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        m4.setIcon(new ImageIcon(image5));
        m4.setMnemonic('B');
        m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        m4.setBackground(Color.WHITE);
		
        // Ativar ação
		m1.addActionListener(this);
		m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        
        // Primeira coluna caso seja cliente
        JMenu info = new JMenu("Informações");
        JMenuItem info1 = new JMenuItem("Atualizar informações");
        JMenuItem info2 = new JMenuItem("Ver informações");
        
        info.setForeground(Color.RED);
        
        // Atualizar informações menu e icon
        info1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icons/icon5.png"));
        Image image6 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        info1.setIcon(new ImageIcon(image6));
        info1.setMnemonic('P');
        info1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        info1.setBackground(Color.WHITE);
         
        // Ver informações menu e icon
        info2.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icons/icon6.png"));
        Image image7 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        info2.setIcon(new ImageIcon(image7));
        info2.setMnemonic('L');
        info2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        info2.setBackground(Color.WHITE);
        
        info1.addActionListener(this);
        info2.addActionListener(this);
        
        // Segunda coluna caso seja cliente
        JMenu user = new JMenu("Usuário");
        JMenuItem u1 = new JMenuItem("Pagar contas");
        JMenuItem u2 = new JMenuItem("Detalhe das contas");
        user.setForeground(Color.BLUE);
        
        // Pagar contas menu e icon
        u1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icons/icon7.png"));
        Image image8 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        u1.setIcon(new ImageIcon(image8));
        u1.setMnemonic('P');
        u1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        u1.setBackground(Color.WHITE);
        
        // Detalhe das contas menu e icon
        u2.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icons/icon8.png"));
        Image image9 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        u2.setIcon(new ImageIcon(image9));
        u2.setMnemonic('L');
        u2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        u2.setBackground(Color.WHITE);
        
        u1.addActionListener(this);
        u2.addActionListener(this);
        
        // Terceira coluna caso seja cliente
        JMenu report = new JMenu("Reclamações");
        JMenuItem gb = new JMenuItem("Gerar fatura");
        report.setForeground(Color.RED);
        
        // Gerar fatura menu e icon
        gb.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icons/icon9.png"));
        Image image10 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        gb.setIcon(new ImageIcon(image10));
        gb.setMnemonic('R');
        gb.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        gb.setBackground(Color.WHITE);
        
        gb.addActionListener(this);
        
        // Segunda coluna caso seja adm, quarta caso seja cliente
        JMenu utility = new JMenu("Utilidades");
        JMenuItem ut1 = new JMenuItem("Bloco de notas");
        JMenuItem ut2 = new JMenuItem("Calculadora");
        JMenuItem ut3 = new JMenuItem("Pesquisa na web");
        utility.setForeground(Color.BLUE);
        
        // Bloco de notas menu e icon
        ut1.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icons/icon10.png"));
        Image image11 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ut1.setIcon(new ImageIcon(image11));
        ut1.setMnemonic('C');
        ut1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        ut1.setBackground(Color.WHITE);
        
        // Calculadora menu e icon
        ut2.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icons/icon11.png"));
        Image image12 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ut2.setIcon(new ImageIcon(image12));
        ut2.setMnemonic('X');
        ut2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        ut2.setBackground(Color.WHITE);
        
        // Pesquisa na web menu e icon
        ut3.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icons/icon12.png"));
        Image image13 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ut3.setIcon(new ImageIcon(image13));
        ut3.setMnemonic('W');
        ut3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        ut3.setBackground(Color.WHITE);
        
        ut1.addActionListener(this);
        ut2.addActionListener(this);
        ut3.addActionListener(this);
        
        // Terceira coluna caso seja adm, quinta caso seja cliente
        JMenu exit = new JMenu("Deslogar");
        JMenuItem ex = new JMenuItem("Sair");
        exit.setForeground(Color.RED);
        
        // Sair menu e icon
        ex.setFont(new Font("monospaced", Font.PLAIN, 12));
        ImageIcon icon13 = new ImageIcon(ClassLoader.getSystemResource("icons/icon13.png"));
        Image image14 = icon13.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image14));
        ex.setMnemonic('Z');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        ex.setBackground(Color.WHITE);
        
        ex.addActionListener(this);
        
        // Adicionar ao JMenu
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);
        menu.add(m4);
        
        info.add(info1);
        info.add(info2);
        
        user.add(u1);
        user.add(u2);
        
        report.add(gb);
        
        utility.add(ut1);
        utility.add(ut2);
        utility.add(ut3);
        
        exit.add(ex);
        
        // Caso seja adm, irá aparecer menu, utilidade e sair
        // Caso seja cliente, irá aparecer info, usuário, reclamações, utilidade e sair
        if(person.equals("Administrador")) {
        	jmb.add(menu);
        }
        else {
        	jmb.add(info);
        	jmb.add(user);
        	jmb.add(report);
        }
        jmb.add(utility);
        jmb.add(exit);
        
        setJMenuBar(jmb);
        
        setFont(new Font("Senserif", Font.BOLD, 16));
        setLayout(new FlowLayout());
        setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String msg = actionEvent.getActionCommand();
		
		if (msg.equals("Detalhes dos clientes")) {
			new CustomerDetails().setVisible(true);
		}
		else if (msg.equals("Novo cliente")) {
			new NewCustomer().setVisible(true);
		}
		else if(msg.equals("Calcular contas")){
            new CalculateBill().setVisible(true);
        }
		else if(msg.equals("Pagar contas")){
            new PayBill(meter).setVisible(true);
        }
        else if(msg.equals("Bloco de notas")){
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }
            catch(Exception e){e.printStackTrace();}
        }
        else if(msg.equals("Calculadora")){
            try{
                Runtime.getRuntime().exec("calc.exe");
            }
            catch(Exception e){e.printStackTrace();}
        }
        else if(msg.equals("Pesquisa na web")){
            try{
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            }
            catch(Exception e){e.printStackTrace();}
        }
        else if(msg.equals("Sair")){
			System.exit(EXIT_ON_CLOSE);
        }
        else if(msg.equals("Gerar Fatura")){
            //new GenerateBill(meter).setVisible(true);
        }
        else if(msg.equals("Detalhes dos depósitos")){
            new DepositDetails().setVisible(true);
        }
        else if(msg.equals("Ver informações")){
            new ViewInformation(meter).setVisible(true);
        }
        else if(msg.equals("Atualizar informações")){
            new UpdateInformation(meter).setVisible(true);
        }
        else if(msg.equals("Detalhe das contas")){
            //new BillDetails(meter).setVisible(true);
        }
	}
	
	public static void main(String[] args) {
		new Project("", "").setVisible(true);
	}

}
