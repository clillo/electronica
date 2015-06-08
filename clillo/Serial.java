package com.clillo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Serial  extends JFrame  implements SerialPortEventListener,  MouseMotionListener, ChangeListener {
	
	private OutputStream outputStream;
	private SerialPort serialPort;
	
	private int ancho;
	private int alto;
	
	private static final int MOTOR_X_MIN = 900;
	private static final int MOTOR_X_MAX = 1900;

	private static final int MOTOR_Y_MIN = 1130;
	private static final int MOTOR_Y_MAX = 2100;

	private JSlider slider ;
	private JSlider slider_1;
	
	public Serial() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(343, 187));
		JPanel p =  new JPanel();
		p.addMouseMotionListener(this);
		ancho = 343;
		alto = 187;
		setContentPane(p);
		p.setLayout(null);
		
		 slider = new JSlider();
		slider.setBounds(45, 11, 200, 23);

		slider.setMaximum(MOTOR_X_MAX);

		slider.setMinimum(MOTOR_X_MIN);
		slider.addChangeListener(this);

		p.add(slider);
		
		 slider_1 = new JSlider();
		 slider_1.setMaximum(MOTOR_Y_MAX);

		 slider_1.setMinimum(MOTOR_Y_MIN);

		 slider_1.addChangeListener(this);
		slider_1.setBounds(45, 63, 200, 23);
		p.add(slider_1);
	}
	
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == 201){
			dispose();
			System.exit(0);
		}
	}
	public void inicio() throws Exception{
		String port = "COM11";
		String appName = "wireless";
		int timeout = 0;
		int baudRate = 9600;
		
		SerialPort serialPort;
		InputStream inputStream;
		
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portId = null;
		boolean found = false;
		
		while (portList.hasMoreElements()) {
			portId = portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				//log.debug("Found port: " + portId.getName());
				if (portId.getName().equals(port)) {
					//log.debug("Using Port: " + portId.getName());
					found = true;
					break;
				}
			}
		}

		if (!found) {
			throw new Exception("Could not find port: " + port);
		}
		
		serialPort = (SerialPort) portId.open(appName, timeout);
		
		serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		serialPort.notifyOnDataAvailable(true);
		
		// activate the OUTPUT_BUFFER_EMPTY notifier
		//serialPort.notifyOnOutputEmpty(true);
		
		serialPort.addEventListener(this);
		
		inputStream = serialPort.getInputStream();
		outputStream = new BufferedOutputStream(serialPort.getOutputStream());
	/*	
		for (int i=10; i<55; i++){
			punto (10, i);
			Thread.sleep(100);
		}

		for (int i=10; i<55; i++){
			punto (55, i);
			Thread.sleep(100);
		}
		try {
			serialPort.getInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			serialPort.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			serialPort.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	private void punto (int x, int y) throws IOException{
//		int rx = x * 45 /ancho + 15;
//		int ry = (alto-y) * 50 / alto + 10;

//		int rx = x * (MOTOR_X_MAX-MOTOR_X_MIN) /ancho +  MOTOR_X_MIN;
//		int ry = y * (MOTOR_Y_MAX-MOTOR_Y_MIN) / alto + MOTOR_Y_MIN;

		int rx = x ;
		int ry = y;

	
		motor (rx);
		motor (ry);
		outputStream.flush();
		System.out.println(rx+","+ry);
	}
	
	private void motor (int microsegundo) throws IOException{
		int b1 = microsegundo / 255;
		int b0 = microsegundo % 255;

		outputStream.write(b0);
		outputStream.write(b1);
	}
	

	public static void main(String[] args) throws Exception {
		Serial s = new Serial();
		s.validate();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = s.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		s.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		s.setVisible(true);

		s.inicio();
	}


	@Override
	public void serialEvent(SerialPortEvent arg0) {
		System.out.println(arg0.getEventType());
		
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	/*	try {
			punto (e.getX(), e.getY());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		*/
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		try {
			punto(slider.getValue(), slider_1.getValue());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
