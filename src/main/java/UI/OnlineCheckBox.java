package UI;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.List;

import static MainPackage.ApplicationRun.chatPanel;
import static MainPackage.ApplicationRun.iChatService;
import static MainPackage.ApplicationRun.playerName;

public class OnlineCheckBox extends JCheckBox {

    public OnlineCheckBox(String text) {

        this.setText(text);
        this.setBounds(70,350, 100,30);
        this.setSelected(true);
        this.addItemListener(e -> checkChange(e));
    }

    public void checkChange(ItemEvent evento) {
        if (this.isSelected()){
            List<String> result = iChatService.getAllMessages(playerName);
            for (String res: result) {
                String[] stringArray = res.split(":");
                try {
                    chatPanel.chat.writeMessage(stringArray[1],stringArray[0],stringArray[0]);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
