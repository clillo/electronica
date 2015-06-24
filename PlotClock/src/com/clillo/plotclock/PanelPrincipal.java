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
import javax.swing.SpinnerNumberModel;

public class PanelPrincipal extends JPanel implements ListenerPosicion, ActionListener, ChangeListener, AdjustmentListener{

	private static final long serialVersionUID = -5869553409971473557L;
	
	private PanelPosicion panelPosicion = new PanelPosicion();
	private PanelRobot panelRobot = new PanelRobot();

	private CinematicaInversa cinematica;
	private Configuracion configuracion = new Configuracion();

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
	private JSpinner spnAngulo3;
	private JSpinner spnL1;
	private JSpinner spnL2;
	private JSpinner spnL3;

	private JButton btnTest;
	
	public PanelPrincipal() {
		setLayout(null);
		
		Calculo.setConfiguracion(configuracion);
		
		panelPosicion.setListener(this);
		panelPosicion.setBounds(10, 11, 662, 660);
		add(panelPosicion);
		
		scrlRobotY = new JScrollBar();
		scrlRobotY.addAdjustmentListener(this);
	//	scrlRobotY.setValue(100);
		scrlRobotY.setBounds(1158, 130, 17, 48);
		add(scrlRobotY);
		
		scrlRobotX = new JScrollBar();
		scrlRobotX.setOrientation(JScrollBar.HORIZONTAL);
		scrlRobotX.addAdjustmentListener(this);
	//	scrlRobotX.setValue(100);
		scrlRobotX.setBounds(666, 668, 408, 17);
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
		panelRobot.setBounds(682, 190, 483, 481);
		add(panelRobot);
		
		btnDibuja = new JButton("Dibuja Numeros");
		btnDibuja.setBounds(725, 137, 113, 23);
		btnDibuja.addActionListener(this);
		add(btnDibuja);
		
		txtD1 = new JTextField();
		txtD1.setText("5");
		txtD1.setBounds(861, 140, 28, 20);
		add(txtD1);
		txtD1.setColumns(10);
		
		txtD2 = new JTextField();
		txtD2.setText("6");
		txtD2.setColumns(10);
		txtD2.setBounds(900, 140, 28, 20);
		add(txtD2);
		
		txtD3 = new JTextField();
		txtD3.setText("7");
		txtD3.setColumns(10);
		txtD3.setBounds(938, 140, 28, 20);
		add(txtD3);
		
		txtD4 = new JTextField();
		txtD4.setText("9");
		txtD4.setColumns(10);
		txtD4.setBounds(974, 140, 28, 20);
		add(txtD4);
		
		btnLimpia = new JButton("Limpia");
		btnLimpia.setBounds(1023, 137, 74, 23);
		btnLimpia.addActionListener(this);
		add(btnLimpia);
		
		sldrEscala = new JSlider();
		sldrEscala.setBounds(725, 171, 408, 23);
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
		spnServo1.setValue(1400);
		spnServo1.addChangeListener(this);
		spnServo1.setFont(new Font("Tahoma", Font.BOLD, 18));
		spnServo1.setBounds(1041, 21, 92, 33);
		add(spnServo1);
		
		spnServo2 = new JSpinner();
		spnServo2.setValue(1400);
		spnServo2.addChangeListener(this);
		spnServo2.setFont(new Font("Tahoma", Font.BOLD, 18));
		spnServo2.setBounds(939, 21, 92, 33);
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
		
		spnAngulo3 = new JSpinner();
		spnAngulo3.setModel(new SpinnerNumberModel(configuracion.getAnguloBrazo5(), -3.0, 6.0, 0.05));
		spnAngulo3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnAngulo3.addChangeListener(this);
		spnAngulo3.setBounds(1202, 27, 69, 23);
		add(spnAngulo3);
		
		JLabel lblAngulo = new JLabel("Angulo 3");
		lblAngulo.setBounds(1202, 11, 46, 14);
		add(lblAngulo);
		
		spnL1 = new JSpinner();
		spnL1.setModel(new SpinnerNumberModel(configuracion.getLargoBrazo1(), 0.0, 100.0, 0.5));
		spnL1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnL1.setBounds(1202, 75, 69, 23);
		spnL1.addChangeListener(this);
		add(spnL1);
		
		spnL2 = new JSpinner();
		spnL2.setModel(new SpinnerNumberModel(configuracion.getLargoBrazo3(), 0.0, 100.0, 0.5));
		spnL2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnL2.setBounds(1202, 124, 69, 23);
		spnL2.addChangeListener(this);
		add(spnL2);
		
		spnL3 = new JSpinner();
		spnL3.setModel(new SpinnerNumberModel(configuracion.getLargoBrazo5(), 0.0, 100.0, 0.5));
		spnL3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnL3.setBounds(1202, 171, 69, 23);
		spnL3.addChangeListener(this);
		add(spnL3);
		
		JLabel lblL = new JLabel("L1");
		lblL.setBounds(1202, 61, 46, 14);
		add(lblL);
		
		JLabel lblL_1 = new JLabel("L2");
		lblL_1.setBounds(1202, 109, 46, 14);
		add(lblL_1);
		
		JLabel lblL_2 = new JLabel("L3");
		lblL_2.setBounds(1202, 158, 46, 14);
		add(lblL_2);
		
		btnTest = new JButton("Test");
		btnTest.addActionListener(this);
		btnTest.setBounds(1051, 94, 74, 23);
		add(btnTest);
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
			spnServo2.setValue((int)CinematicaInversa.servoIzquerdo.getAngulo());
			spnServo1.setValue((int)CinematicaInversa.servoDerecho.getAngulo());
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
			
		if (arg0.getSource().equals(btnTest))
			new Thread(){
				@Override
				public void run() {
					super.run();
					panelRobot.test();
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
	
		if (arg0.getSource().equals(spnServo1) || arg0.getSource().equals(spnServo2)){
			cinematica.moverHasta((int)spnServo1.getValue(), (int)spnServo2.getValue());
			this.repaint();
		}

		if (spnL1!=null){
			configuracion.setLargoBrazo1((double)spnL1.getValue());
			configuracion.setLargoBrazo2((double)spnL1.getValue());
		}
		if (spnL2!=null){
			configuracion.setLargoBrazo3((double)spnL2.getValue());
			configuracion.setLargoBrazo4((double)spnL2.getValue());
		}
		if (spnL3!=null)
			configuracion.setLargoBrazo5((double)spnL3.getValue());

		if (spnAngulo3!=null)
			configuracion.setAnguloBrazo5((double)spnAngulo3.getValue());

		this.repaint();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
	//	System.out.println(scrlRobotX.getValue()+","+ scrlRobotY.getValue());
		panelRobot.setOrigen(scrlRobotX.getValue(), scrlRobotY.getValue());
	}
}
