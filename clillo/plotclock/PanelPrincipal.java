package com.clillo.plotclock;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PanelPrincipal extends JPanel implements ListenerPosicion, ActionListener{

	private static final long serialVersionUID = -5869553409971473557L;
	
	private PanelPosicion panelPosicion = new PanelPosicion();
	private PanelReal panelReal = new PanelReal();
	private PanelRobot panelRobot = new PanelRobot();

	private CinematicaInversa cinematica;
	private JTextField txtPosRealX;
	private JTextField txtPosRealY;
	private JTextField txtAnguloIzquerdo;
	private JTextField txtAnguloDerecho;
	private JTextField txtCoordenadaRealX;
	private JTextField txtCoordenadaRealY;
	private JLabel lblAnguloIzq;
	private JLabel lblAnguloDer;
	private static JTextField txtServoFaktor;

	public PanelPrincipal() {
		setLayout(null);
		
		panelReal.setBackground(Color.BLACK);
		panelReal.setBounds(189, 11, 166, 153);
		add(panelReal);
		
		panelPosicion.setListener(this);
		panelPosicion.setBounds(10, 11, 150, 150);
		add(panelPosicion);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(356, 11, 17, 153);
		add(scrollBar);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar_1.setBounds(189, 172, 156, 17);
		add(scrollBar_1);
		
		txtPosRealX = new JTextField();
		txtPosRealX.setBounds(10, 188, 74, 20);
		add(txtPosRealX);
		txtPosRealX.setColumns(10);
		
		txtPosRealY = new JTextField();
		txtPosRealY.setColumns(10);
		txtPosRealY.setBounds(86, 188, 74, 20);
		add(txtPosRealY);
		
		txtAnguloIzquerdo = new JTextField();
		txtAnguloIzquerdo.setColumns(10);
		txtAnguloIzquerdo.setBounds(189, 232, 74, 20);
		add(txtAnguloIzquerdo);
		
		txtAnguloDerecho = new JTextField();
		txtAnguloDerecho.setColumns(10);
		txtAnguloDerecho.setBounds(273, 232, 74, 20);
		add(txtAnguloDerecho);
		
		txtCoordenadaRealX = new JTextField();
		txtCoordenadaRealX.setColumns(10);
		txtCoordenadaRealX.setBounds(86, 232, 74, 20);
		add(txtCoordenadaRealX);
		
		txtCoordenadaRealY = new JTextField();
		txtCoordenadaRealY.setColumns(10);
		txtCoordenadaRealY.setBounds(10, 232, 74, 20);
		add(txtCoordenadaRealY);
		
		cinematica = new CinematicaInversa();
		cinematica.setPanelReal(panelReal);
		
		panelRobot.setBackground(Color.BLACK);
		panelRobot.setBounds(555, 11, 281, 280);
		add(panelRobot);
		
		JLabel lblCoordenadasReales = new JLabel("Coordenadas Normalizadas:");
		lblCoordenadasReales.setBounds(10, 172, 150, 14);
		add(lblCoordenadasReales);
		
		JLabel label = new JLabel("Coordenadas Reales:");
		label.setBounds(10, 215, 103, 14);
		add(label);
		
		lblAnguloIzq = new JLabel("Angulo Izq.");
		lblAnguloIzq.setBounds(189, 215, 74, 14);
		add(lblAnguloIzq);
		
		lblAnguloDer = new JLabel("Angulo Der.");
		lblAnguloDer.setBounds(273, 215, 74, 14);
		add(lblAnguloDer);
		
		txtServoFaktor = new JTextField("633");
		txtServoFaktor.setBounds(412, 22, 86, 20);
		add(txtServoFaktor);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(412, 75, 89, 23);
		btnNewButton.addActionListener(this);
		add(btnNewButton);
	
		//cinematica.moverA(75, 47.5);
	}
	
	public static double getServoFaktor(){
		return Double.parseDouble(txtServoFaktor.getText());
	}
	
	private String redondea(double d){
		d = ((int)(d*100.0))/100.0;
		return d+"";
	}

	@Override
	public void mover(double x, double y) {
		txtPosRealX.setText(redondea(x));
		txtPosRealY.setText(redondea(y));
		cinematica.moverA(x, y);
		txtCoordenadaRealX.setText(redondea(cinematica.getCoordenadaRealX()));
		txtCoordenadaRealY.setText(redondea(cinematica.getCoordenadaRealY()));
		
		txtAnguloIzquerdo.setText(CinematicaInversa.servoIzquerdo.getEstado());
		txtAnguloDerecho.setText(CinematicaInversa.servoDerecho.getEstado());
		this.repaint();
		panelRobot.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cinematica.dibuja(1, 2, 3, 4);
		
	}
}
