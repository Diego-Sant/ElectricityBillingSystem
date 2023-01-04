package Electricity;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener {

	JButton jBttn;
	JLabel jLb;
	Font f, f1, f2;
	TextArea txtArea;
	String str;
	
	public About() {
		
		// setBounds (x:, y:, width:, height:)
		
		// Configuração botão de sair
		setLayout(null);
		JButton jBttn = new JButton("Sair");
		add(jBttn);
		jBttn.setBounds(180, 430, 120, 20);
		jBttn.addActionListener(this);
		
		// Configuração da fonte
		Font f = new Font("RALEWAY", Font.BOLD, 180);
		setFont(f);
		
		// O que ficará escrito no About
		str = 	  "Sobre o projeto \n"
                + "\nElectricity Billing System é um aplicativo baseado em software "
                + "desenvolvido na linguagem de programação Java. O projeto visa atender "
                + "o departamento de eletricidade computando o sistema de cobrança. "
                + "Ele se concentra principalmente no cálculo das Unidades consumidas durante o "
                + "tempo especificado e o dinheiro a ser pago aos escritórios de eletricidade. "
                + "Este sistema informatizado facilitará o sistema geral de cobrança, "
                + "deixará acessível, confortável e eficaz para os consumidores."
                ;
		
		// Configuração da área de texto
		TextArea txtArea = new TextArea(str, 10, 40, Scrollbar.VERTICAL);
		txtArea.setEditable(false);
		txtArea.setBounds(20, 100, 450, 250);
		add(txtArea);
		
		// Adicionar fonte f1 para TextArea
		Font f1 = new Font("RALEWAY", Font.BOLD, 16);
		txtArea.setFont(f1);
		
		Container contentPane = this.getContentPane();
		txtArea = new TextArea();
		
		// Configuração para label no título
		JLabel jLb = new JLabel("Sobre o projeto");
		add(jLb);
		jLb.setBounds(170, 10, 180, 80);
		jLb.setForeground(Color.RED);
		
		// Adicionar fonte f2 para JLabel
		Font f2 = new Font("RALEWAY", Font.BOLD, 20);
		jLb.setFont(f2);
		
		// Configuração do container About
		setBounds(700, 220, 500, 550);
		setLayout(null);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
	public static void main(String args[]) {
		new About().setVisible(true);
	}

}
