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
import java.awt.Font;
import javax.swing.JSpinner;

public class PanelPrincipal extends JPanel implements ListenerPosicion, ActionListener, ChangeListener, AdjustmentListener{

	private static final long serialVersionUID = -5869553409971473557L;
	
	private PanelPosicion panelPosicion = new PanelPosicion();
	private PanelRobot panelRobot = new PanelRobot();

	private CinematicaInversa cinematica;
	private JTextField txtPosRealX;
	private JTextField txtPosRealY;
	private JTextField txtAnguloDerecho;
	private JTextField txtCoordenadaRealX;
	private JTextField txtCoordenadaRealY;
	private JTextField txtD1;
	private JTextField txtD2;
	private JTextField txtD3;
	private JTextField txtD4;
	private JButton btnLimpia;
	private JButton btnDibuja;
	
	private JSlider sldrEscala;
	private JScrollBar scrlRobotY;
	private JScrollBar scrlRobotX;
	
	private JSpinner spnServo1;
	private JSpinner spnServo2;
	private JSpinner spnPosX;
	private JSpinner spnPosY;
	
	public PanelPrincipal() {
		setLayout(null);
		
		panelPosicion.setListener(this);
		panelPosicion.setBounds(10, 11, 705, 660);
		add(panelPosicion);
		
		scrlRobotY = new JScrollBar();
		scrlRobotY.addAdjustmentListener(this);
	//	scrlRobotY.setValue(100);
		scrlRobotY.setBounds(1132, 252, 17, 402);
		add(scrlRobotY);
		
		scrlRobotX = new JScrollBar();
		scrlRobotX.setOrientation(JScrollBar.HORIZONTAL);
		scrlRobotX.addAdjustmentListener(this);
	//	scrlRobotX.setValue(100);
		scrlRobotX.setBounds(725, 654, 408, 17);
		add(scrlRobotX);
		
		txtPosRealX = new JTextField();
		txtPosRealX.setBounds(809, 106, 74, 20);
		add(txtPosRealX);
		txtPosRealX.setColumns(10);
		
		txtPosRealY = new JTextField();
		txtPosRealY.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPosRealY.setColumns(10);
		txtPosRealY.setBounds(725, 78, 74, 48);
		add(txtPosRealY);
		
		txtAnguloDerecho = new JTextField();
		txtAnguloDerecho.setColumns(10);
		txtAnguloDerecho.setBounds(1041, 65, 92, 20);
		add(txtAnguloDerecho);
		
		txtCoordenadaRealX = new JTextField();
		txtCoordenadaRealX.setColumns(10);
		txtCoordenadaRealX.setBounds(893, 75, 74, 20);
		add(txtCoordenadaRealX);
		
		txtCoordenadaRealY = new JTextField();
		txtCoordenadaRealY.setColumns(10);
		txtCoordenadaRealY.setBounds(809, 75, 74, 20);
		add(txtCoordenadaRealY);

		panelRobot.setBackground(Color.BLACK);
		panelRobot.setBounds(725, 252, 400, 400);
		add(panelRobot);
		
		btnDibuja = new JButton("Dibuja Numeros");
		btnDibuja.setBounds(722, 171, 113, 23);
		btnDibuja.addActionListener(this);
		add(btnDibuja);
		
		txtD1 = new JTextField();
		txtD1.setText("5");
		txtD1.setBounds(858, 174, 28, 20);
		add(txtD1);
		txtD1.setColumns(10);
		
		txtD2 = new JTextField();
		txtD2.setText("6");
		txtD2.setColumns(10);
		txtD2.setBounds(897, 174, 28, 20);
		add(txtD2);
		
		txtD3 = new JTextField();
		txtD3.setText("7");
		txtD3.setColumns(10);
		txtD3.setBounds(935, 174, 28, 20);
		add(txtD3);
		
		txtD4 = new JTextField();
		txtD4.setText("9");
		txtD4.setColumns(10);
		txtD4.setBounds(971, 174, 28, 20);
		add(txtD4);
		
		btnLimpia = new JButton("Limpia");
		btnLimpia.setBounds(1020, 171, 74, 23);
		btnLimpia.addActionListener(this);
		add(btnLimpia);
		
		sldrEscala = new JSlider();
		sldrEscala.setBounds(725, 225, 408, 23);
		sldrEscala.addChangeListener(this);
		sldrEscala.setMinimum(10);
		sldrEscala.setMaximum(500);
		//sldrEscala.setValue(70);
		sldrEscala.setValue(200);
		add(sldrEscala);

		cinematica = new CinematicaInversa();
		cinematica.setPanelPrincipal(this);
		cinematica.setPanelRobot(panelRobot);
		
		spnServo1 = new JSpinner();
		spnServo1.addChangeListener(this);
		spnServo1.setFont(new Font("Tahoma", Font.BOLD, 18));
		spnServo1.setBounds(932, 21, 92, 33);
		add(spnServo1);
		
		spnServo2 = new JSpinner();
		spnServo2.addChangeListener(this);
		spnServo2.setFont(new Font("Tahoma", Font.BOLD, 18));
		spnServo2.setBounds(1041, 21, 92, 33);
		add(spnServo2);
		
		spnPosX = new JSpinner();
		spnPosX.addChangeListener(this);
		spnPosX.setFont(new Font("Tahoma", Font.BOLD, 18));
		spnPosX.setBounds(725, 21, 62, 33);
		add(spnPosX);
		
		spnPosY = new JSpinner();
		spnPosY.addChangeListener(this);
		spnPosY.setFont(new Font("Tahoma", Font.BOLD, 18));
		spnPosY.setBounds(797, 21, 62, 33);
		add(spnPosY);
		
		panelPosicion.setCinematica(cinematica);
	}
	
	private String redondea(double d){
		d = ((int)(d*100.0))/100.0;
		return d+"";
	}
	
	@Override	
	public void mover(double x, double y, boolean fake){
		mover(x, y, fake, false); 
	}

	public void mover(double x, double y, boolean fake, boolean fake2) {
		Punto p = new Punto (x, y);
		CinematicaInversa.pantalla2Real(p);
		
		txtPosRealX.setText(redondea(x)+","+redondea(y));
		txtPosRealY.setText(p.getIx()+","+p.getIy());
		
		if (!fake){
			if (!fake2){
				spnPosX.removeChangeListener(this);
				spnPosY.removeChangeListener(this);
				spnPosX.setValue(p.getIx());
				spnPosY.setValue(p.getIy());
				spnPosX.addChangeListener(this);
				spnPosY.addChangeListener(this);
			}
			cinematica.moverA(x, y);
			txtCoordenadaRealX.setText(redondea(cinematica.getCoordenadaRealX()));
			txtCoordenadaRealY.setText(redondea(cinematica.getCoordenadaRealY()));
			
			spnServo1.removeChangeListener(this);
			spnServo2.removeChangeListener(this);
			spnServo1.setValue((int)CinematicaInversa.servoIzquerdo.getAngulo());
			spnServo2.setValue((int)CinematicaInversa.servoDerecho.getAngulo());
			spnServo1.addChangeListener(this);
			spnServo2.addChangeListener(this);
			txtAnguloDerecho.setText(CinematicaInversa.servoIzquerdo.getEstado()+","+CinematicaInversa.servoDerecho.getEstado());
		}
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
					panelRobot.repaint();
				}
			}.start();
			
		if (arg0.getSource().equals(btnLimpia)){
			panelRobot.limpia();
			panelPosicion.limpia();
			this.repaint();
		}		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource().equals(sldrEscala))
			panelRobot.setEscala(sldrEscala.getValue());
	
		if (arg0.getSource().equals(spnServo1) || arg0.getSource().equals(spnServo2))
			cinematica.moverHasta((int)spnServo1.getValue(), (int)spnServo2.getValue());
	
		//if (arg0.getSource().equals(spnPosX) || arg0.getSource().equals(spnPosY))
		//	mover((int)spnPosX.getValue(), (int)spnPosY.getValue(), false, true);
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
	//	System.out.println(scrlRobotX.getValue()+","+ scrlRobotY.getValue());
		panelRobot.setOrigen(scrlRobotX.getValue(), scrlRobotY.getValue());
	}
}
