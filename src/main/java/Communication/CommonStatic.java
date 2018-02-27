package Communication;

import java.util.concurrent.Semaphore;

public abstract class CommonStatic {

    public static Semaphore onDataSend = new Semaphore(0);
    public static Semaphore onDataReceive = new Semaphore(0);

    public static String protocolMsg2Send = "";
    public static String protocolMsgReceived = "";
}
