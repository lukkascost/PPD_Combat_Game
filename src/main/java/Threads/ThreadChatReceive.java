package Threads;

import Communication.CommonStatic;
import MainPackage.ApplicationRun;

public class ThreadChatReceive extends Thread{
    public boolean loop = true;

    @Override
    public void run() {
        while(loop) {
            try {
                CommonStatic.onDataReceive.acquire();
                String received = CommonStatic.protocolMsgReceived;

                if (received.substring(0,2).equals("01")) {
                    received = received.substring(3,received.length());
                    ApplicationRun.chatPannel.getChatTextLog().setText(ApplicationRun.chatPannel.getChatTextLog().getText() + "\n" + "INIMIGO: " + received);
                }
                else{
                    if(received.substring(0,2).equals("02")){
                        ApplicationRun.gamePannel.moveObject(received);
                    }
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
