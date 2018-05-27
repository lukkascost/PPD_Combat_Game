package UI;


import static Main.ChatUserUI.chatPannel;
public class ThreadChat extends Thread {
    @Override
    public void run() {
        while(true){
            try{
                chatPannel.updateChat();
                Thread.currentThread().sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
