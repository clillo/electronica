package com.clillo.plotclock;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelPrincipal extends JPanel implements ListenerPosicion, ActionListener, ChangeListener, AdjustmentListener{

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
	private JTextField txtD1;
	private JTextField txtD2;
	private JTextField txtD3;
	private JTextField txtD4;
	private JButton btnLimpia;
	private JButton btnDibuja;
	private JButton button;
	private JButton button_1;
	
	private JSlider sldrEscala;
	private JScrollBar scrlRobotY;
	private JScrollBar scrlRobotX;
	
	public PanelPrincipal() {
		setLayout(null);
		
		panelReal.setBackground(Color.BLACK);
		panelReal.setBounds(189, 11, 166, 153);
		add(panelReal);
		
		panelPosicion.setListener(this);
		panelPosicion.setBounds(10, 11, 150, 150);
		add(panelPosicion);
		
		scrlRobotY = new JScrollBar();
		scrlRobotY.addAdjustmentListener(this);
		scrlRobotY.setBounds(839, 11, 17, 280);
		add(scrlRobotY);
		
		scrlRobotX = new JScrollBar();
		scrlRobotX.setOrientation(JScrollBar.HORIZONTAL);
		scrlRobotX.addAdjustmentListener(this);

		scrlRobotX.setBounds(555, 291, 281, 17);
		add(scrlRobotX);
		
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
		
		btnDibuja = new JButton("Dibuja");
		btnDibuja.setBounds(400, 99, 89, 23);
		btnDibuja.addActionListener(this);
		add(btnDibuja);
		
		txtD1 = new JTextField();
		txtD1.setText("1");
		txtD1.setBounds(383, 53, 28, 20);
		add(txtD1);
		txtD1.setColumns(10);
		
		txtD2 = new JTextField();
		txtD2.setText("2");
		txtD2.setColumns(10);
		txtD2.setBounds(422, 53, 28, 20);
		add(txtD2);
		
		txtD3 = new JTextField();
		txtD3.setText("3");
		txtD3.setColumns(10);
		txtD3.setBounds(460, 53, 28, 20);
		add(txtD3);
		
		txtD4 = new JTextField();
		txtD4.setText("4");
		txtD4.setColumns(10);
		txtD4.setBounds(497, 53, 28, 20);
		add(txtD4);
		
		btnLimpia = new JButton("Limpia");
		btnLimpia.setBounds(400, 133, 89, 23);
		btnLimpia.addActionListener(this);
		add(btnLimpia);
		
		sldrEscala = new JSlider();
		sldrEscala.setBounds(382, 263, 150, 23);
		sldrEscala.addChangeListener(this);
		sldrEscala.setMinimum(10);
		sldrEscala.setMaximum(500);
		sldrEscala.setValue(70);
		add(sldrEscala);

		cinematica = new CinematicaInversa();
		cinematica.setPanelReal(panelReal);
		cinematica.setPanelPrincipal(this);
		cinematica.setPanelRobot(panelRobot);

		button = new JButton("Dibuja 2");
		button.setBounds(400, 168, 89, 23);
		button.addActionListener(this);
		add(button);
		
		button_1 = new JButton("Dibuja 2");
		button_1.setBounds(400, 206, 89, 23);
		button_1.addActionListener(this);
		add(button_1);
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
		
		if (arg0.getSource().equals(btnDibuja))
			new Thread(){
				@Override
				public void run() {
					super.run();
					cinematica.dibuja(Integer.parseInt(txtD1.getText()), Integer.parseInt(txtD2.getText()), Integer.parseInt(txtD3.getText()), Integer.parseInt(txtD4.getText()));
					//this.repaint();
					panelRobot.repaint();
				}
			}.start();
			
		if (arg0.getSource().equals(btnLimpia)){
			panelRobot.limpia();
			panelReal.limpia();
			this.repaint();
		}
			
		if (arg0.getSource().equals(button)){
			new Thread(){
				@Override
				public void run() {
					super.run();
					int motor1[] = {1848,
							1792,
							1699,
							1669,
							1594,
							1488,
							1431,
							1350,
							1258,
							1168,
							1085



};
					int motor2[] = {1805,
							1710,
							1595,
							1509,
							1418,
							1353,
							1265,
							1219,
							1177,
							1150,
							1118



};
					cinematica.dibuja(motor1, motor2);
					panelRobot.repaint();
				}
			}.start();
		}
		
		if (arg0.getSource().equals(button_1)){
			new Thread(){
				@Override
				public void run() {
					super.run();
					MatrizConversion mc = new MatrizConversion();
					ArrayList<Par> lista = mc.getListaPares();
					
					for (Par p: lista)
						panelReal.agregaPuntoGrilla(p.getX(), p.getY());
					
					panelRobot.repaint();
				}
			}.start();
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource().equals(sldrEscala))
			panelRobot.setEscala(sldrEscala.getValue());
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		panelRobot.setOrigen(scrlRobotX.getValue(), scrlRobotY.getValue());
	}
}
