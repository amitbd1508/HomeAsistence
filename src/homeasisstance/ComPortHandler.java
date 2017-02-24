package homeasisstance;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Created by BlackFlag on 2/21/2017.
 */
public class ComPortHandler {

    public static String[] getComPortsList() {
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        int len = serialPorts.length;
        String[] ports = new String[len];
        for (int i = 0; i < len; ++i) {
            ports[i] = serialPorts[i].getSystemPortName();
        }
        return ports;
    }
    public static SerialPort getSerialPort(String deviceName) {
        return SerialPort.getCommPort(deviceName);
    }

    public static void readPort(final String comPort) {
        Thread t = new Thread(){

            @Override
            public void run() {
                SerialPort serialPort = SerialPort.getCommPort(getComPortsList()[0]);
                if (serialPort == null) {
                    return;
                }
                serialPort.setBaudRate(9600);
                serialPort.setComPortTimeouts(0, 0, 0);
                if (serialPort.openPort()) {
                    try {
                        do {
                            byte[] bytes = new byte[serialPort.bytesAvailable()];
                            serialPort.readBytes(bytes, bytes.length);
                            String portData = new String(bytes);
                            if (portData.trim().isEmpty()) continue;
                            System.out.println(portData);
                        } while (true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ComPortHandler.sendToConsole("Port Closed");
                }
                serialPort.closePort();
            }
        };
        t.start();
    }
    public static void sendToConsole(String msg) {
        System.out.println(msg);
    }

}
