package UI;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static MainPackage.ApplicationRun.chatPanel;

public class OnlineCheckBox extends JCheckBox {

    public OnlineCheckBox(String text) {

        this.setText(text);
        this.setBounds(70,350, 100,30);
        this.setSelected(true);
        this.addItemListener(e -> checkChange(e));
    }

    public void checkChange(ItemEvent evento) {
        chatPanel.writeLog("State changed to "+ this.isSelected());
    }

}
