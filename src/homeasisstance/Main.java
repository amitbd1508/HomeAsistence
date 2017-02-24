package homeasisstance;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        block0 : do {
//            String[] ports = ComPortHandler.getComPortsList();
//            int i = 0;
//            do {
//                if (i >= ports.length) continue block0;
//                System.out.println(ports[i]);
//                ComPortHandler.readPort(ports[i]);
//                i += 2;
//            } while (true);
//
//        } while (true);true

        try {
            new BaseServer();
            ComPortHandler.readPort("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
