package com.clillo.plotclock;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class Serial  {
	
	private OutputStream outputStream;
	private SerialPort serialPort;
	
	public Serial() throws Exception{
		String port = "COM7";
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
		
		inputStream = serialPort.getInputStream();
		outputStream = new BufferedOutputStream(serialPort.getOutputStream());
	}	
	
	public void punto(Servo servo1, Servo servo2) throws IOException{
		
	//	System.out.println((int)servo1.getAngulo()+"\t"+(int)servo2.getAngulo());
		motor ((int)servo1.getAngulo());
		motor ((int)servo2.getAngulo());
	if (outputStream!=null)
			outputStream.flush();
	}
	
	private void motor (int microsegundo) throws IOException{
		int b1 = microsegundo / 255;
		int b0 = microsegundo % 255;

		outputStream.write(b0);
		outputStream.write(b1);
	}
}