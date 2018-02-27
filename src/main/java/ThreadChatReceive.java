
import Communication.CommonStatic;

public class ThreadChatReceive extends Thread{
    public boolean loop = true;

    @Override
    public void run() {
        while(loop) {
            try {
                CommonStatic.onDataReceive.acquire();
                ApplicationRun.chatPannel.getChatTextLog().setText(ApplicationRun.chatPannel.getChatTextLog().getText() + "\n" + "INIMIGO: "+CommonStatic.protocolMsgReceived);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopLoop(){
        this.loop = false;
    }
}
