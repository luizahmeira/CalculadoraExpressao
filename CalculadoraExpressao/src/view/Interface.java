package view;

import java.awt.Color;
import java.awt.EventQueue;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class Interface {

	private JFrame frame;
	private JTextField campoExpressao;
	private JTextField campoResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	private void detectarLetras(String expressao){
        //testando uma verificação aqui
        String letras = "[a-zA-Z]+";

        //Definindo um padrão para encontrar letras!
        Pattern padraoLetras = Pattern.compile(letras);

        //agora vamos encontrar letrinhas a partir do padrão definido
        Matcher matcherLetras = padraoLetras.matcher(expressao);

        if(matcherLetras.find()){
            throw new IllegalArgumentException("Foi detectado letras na expressão! Que é proibido, favor mudar!");
        }
        
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 669, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//_CAMPO TEXTO_
		campoExpressao = new JTextField();
		campoExpressao.setBounds(126, 56, 440, 19);
		frame.getContentPane().add(campoExpressao);
		campoExpressao.setColumns(10);
		
		campoResultado = new JTextField();
		campoResultado.setBounds(126, 202, 440, 19);
		frame.getContentPane().add(campoResultado);
		campoResultado.setColumns(10);
		
		//_LABEL_
		JLabel labelResultado = new JLabel("Resultado:");
		labelResultado.setBounds(47, 205, 101, 13);
		frame.getContentPane().add(labelResultado);
		
		JLabel labelExpressao = new JLabel("Expressão:");
		labelExpressao.setBounds(47, 59, 101, 13);
		frame.getContentPane().add(labelExpressao);

		//decimal format!!
		DecimalFormat df = new DecimalFormat("0.00");
		
		//_BOTÃO CALCULAR_
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Calculadora calculadora = new Calculadora();

				String expressao = campoExpressao.getText();

				//metodo criado para detectar letras na expressão, caso ache ele ja da um throw :)
				detectarLetras(expressao);

				Fila<String> termosInfixada = calculadora.extrairTermos(expressao);

				//testando fila no modelo infixada
				//System.out.println("Fila no modelo Infixa: " + termosInfixada.toString());

				Fila<String> termosPosfixada = calculadora.gerarExprPosfixada(termosInfixada);

				//testando fila no modelo pos-fixada 
				//System.out.println("Fila no modelo Pós-fixada: " + termosPosfixada.toString());

				double resultado = calculadora.calcularExprPosfixada(termosPosfixada);

				String resultadoFromatado = df.format(resultado).replace(".", ",");

				campoResultado.setText(resultadoFromatado);

			}
		});
		//__Deixando o botão mais bonitinho__
        btnCalcular.setBackground(Color.WHITE); 
        btnCalcular.setForeground(Color.DARK_GRAY);  
        btnCalcular.setFocusPainted(false); 

		btnCalcular.setBounds(295, 102, 85, 21);
		frame.getContentPane().add(btnCalcular);
		
		//_PAINEL_
		JPanel painelCor = new JPanel();
		painelCor.setBackground(new Color(170, 210, 230));
		painelCor.setBounds(10, 10, 635, 342);
		frame.getContentPane().add(painelCor);
		
		
	}
}