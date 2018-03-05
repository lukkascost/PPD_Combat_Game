package Threads;

import Communication.CommonStatic;
import MainPackage.ApplicationRun;

public class ThreadChatReceive extends Thread{
    private boolean loop = true;

    @Override
    public void run() {
        while(ApplicationRun.run) {
            try {
                CommonStatic.onDataReceive.acquire();
                String received = CommonStatic.protocolMsgReceived;

                if (received.substring(0,2).equals("01")) {
                    received = received.substring(3,received.length());
                    ApplicationRun.chatPanel.getChatTextLog().setText(ApplicationRun.chatPanel.getChatTextLog().getText() + "\n" + "INIMIGO: " + received);
                }
                if(received.substring(0,2).equals("02")){
                    ApplicationRun.gameBackEnd.receivedString(received.split(" "));
                    ApplicationRun.gamePanel.updateTable(ApplicationRun.gameBackEnd);
                }
                if(received.substring(0,2).equals("03")){
                    ApplicationRun.gamePanel.winMessage();
                    this.loop = false;
                }
                if(received.substring(0,2).equals("04")){
                    ApplicationRun.gamePanel.loseMessage();
                    this.loop = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
