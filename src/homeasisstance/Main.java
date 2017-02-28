package homeasisstance;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
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




        /*try {
            //Kevin.Instance.speak("Server Opend");
            //new TTSTest();
            String s="Welcome";
            String command = "espeak \""+s+"\"";

            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            proc.waitFor();
            new BaseServer();
            //ComPortHandler.readPort("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/

        try {
            new BaseServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
