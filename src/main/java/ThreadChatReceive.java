
import Communication.CommonStatic;

public class ThreadChatReceive extends Thread{
    public boolean loop = true;

    @Override
    public void run() {
        while(loop) {
            try {
                CommonStatic.onDataReceive.acquire();
                String received = CommonStatic.protocolMsgReceived;

                if (received.substring(0,1).equals("1")) {
                    received = received.substring(1,received.length());
                    ApplicationRun.chatPannel.getChatTextLog().setText(ApplicationRun.chatPannel.getChatTextLog().getText() + "\n" + "INIMIGO: " + received);
                }
                else{
                    System.out.println(received);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopLoop(){
        this.loop = false;
    }
}
