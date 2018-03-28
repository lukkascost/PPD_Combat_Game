package Threads;

import MainPackage.ApplicationRun;

import java.rmi.RemoteException;

public class ThreadChatReceive extends Thread{

    @Override
    public void run() {
        while(ApplicationRun.run) {
            try {
                ApplicationRun.chatPanel.getChatTextLog().setText(ApplicationRun.chatPanel.getChat().getChatText());
                Thread.currentThread().sleep(200);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
