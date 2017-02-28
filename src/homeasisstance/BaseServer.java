package homeasisstance;

import com.fazecast.jSerialComm.SerialPort;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.WebSocketHandler;

import java.io.DataOutputStream;
import java.net.UnknownHostException;

/**
 * Created by BlackFlag on 2/21/2017.
 */
public class BaseServer  implements WebSocketHandler {
    private WebServer baseServer = WebServers.createWebServer(7000);
    protected static WebSocketConnection baseConnection;
    private SerialPort xbox = null;
    private DataOutputStream xboxOutputStream = null;


    private boolean isFirstTime = true;


    public BaseServer() throws UnknownHostException {
        this.baseServer.add("/BaseConnection", this);
        this.baseServer.start();
        this.sendToConsole("Server opened  ");
    }
//    public static void main(String[] args) {
//        new BaseServer();
//    }
    @Override
    public void onOpen(WebSocketConnection webSocketConnection) throws Throwable {
        baseConnection = webSocketConnection;
        this.sendToConsole("New Client : " + baseConnection.httpRequest().remoteAddress());
        this.isFirstTime = true;

    }


    @Override
    public void onClose(WebSocketConnection webSocketConnection) throws Throwable {
        this.sendToConsole("Client Disconnected : " + baseConnection.httpRequest().remoteAddress());

    }

    @Override
    public void onMessage(WebSocketConnection webSocketConnection, String s) throws Throwable {


        try{
            if (this.isFirstTime) {
                this.xbox = SerialPort.getCommPort(this.init());
                this.xbox.setBaudRate(9600);
                boolean isOpen = this.xbox.openPort();
                this.sendToConsole("isOpen = " + isOpen);
                if (isOpen) {
                    this.xboxOutputStream = new DataOutputStream(this.xbox.getOutputStream());
                    this.xboxOutputStream.writeUTF(s);
                    this.xboxOutputStream.flush();
                    this.isFirstTime = false;
                    this.sendToConsole("Data Write "+s);
                }
            } else {
                this.xboxOutputStream.writeUTF(s);
                this.xboxOutputStream.flush();
                this.sendToConsole("Data Write "+s);
            }
            if (this.xbox.isOpen() && this.xboxOutputStream == null) {
                // empty if block
                System.out.print("XBOCX IS NULL \n");
            }
        }catch (Exception ex)
        {
            System.out.print("Cannot write Serial Port \n");
            ex.printStackTrace();

        }


    }

    @Override
    public void onMessage(WebSocketConnection webSocketConnection, byte[] bytes) throws Throwable {


    }

    @Override
    public void onPing(WebSocketConnection webSocketConnection, byte[] bytes) throws Throwable {

    }

    @Override
    public void onPong(WebSocketConnection webSocketConnection, byte[] bytes) throws Throwable {

    }
    public String init() throws Throwable {
        SerialPort serialPort = SerialPort.getCommPorts()[1];
        System.out.println("Found = " + serialPort.getSystemPortName());
        return "ttyACM0";
    }

    public void sendToConsole(String msg) {
        System.out.println(msg);
    }
}
