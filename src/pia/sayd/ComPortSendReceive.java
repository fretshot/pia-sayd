package pia.sayd;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;


public class ComPortSendReceive {

    private static SerialPort serialPort;
    

    public static void main(String[] args) {
        
        
        serialPort = new SerialPort("COM3");
        
        try {
            // opening port
            serialPort.openPort();
            
            serialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
            
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            
            // writing string to port
            serialPort.writeString("Hurrah!!!");
            
            System.out.println("String wrote to port, waiting for response..");
        }
        catch (SerialPortException ex) {
            System.out.println("Error in writing data to port: " + ex);
        }
    }
    
    // receiving response from port
    private static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                   
                    //String receivedData = serialPort.readString(event.getEventValue());
                    //System.out.println("Received response from port: " + receivedData);
                    
                    byte[] buffer = serialPort.readBytes(5);//Leer 10 bytes
                    String stringTemperaturaActual = new String(buffer, "UTF-8"); // Parseando los bytes a objeto String
                    System.out.println(stringTemperaturaActual);


                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                    
                } catch (UnsupportedEncodingException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
}
