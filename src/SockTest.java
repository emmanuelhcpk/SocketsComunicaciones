import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SockTest {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("127.0.0.1", 1234);

            // Your Java Code Verbatim:
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String test = br.readLine();

            System.out.println(test);
            sock.close();
        } catch (Exception ex) {}
    }
}
