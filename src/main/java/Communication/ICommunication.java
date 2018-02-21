package Communication;

import java.io.IOException;

public interface ICommunication {

    public void send(String msg);
    public String receivedMsg();
    public void connect() throws IOException;
}
