package Chat;

import Communication.CommonStatic;

public class ThreadChat extends Thread{
    public boolean loop = true;
    //TODO implemtnar chamada e start dessa classe.
    @Override
    public void run() {
        while(loop) {
            try {
                if (CommonStatic.receivedString) {
                    //TODO
                }
                if (CommonStatic.changedString) {
                    //TODO
                }
                this.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopLoop(){
        this.loop = false;
    }
}
