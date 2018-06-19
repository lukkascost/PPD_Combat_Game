package UI;

import MainPackage.ApplicationRun;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static MainPackage.ApplicationRun.chatPanel;

public class FriendList extends JList {

    private ListSelectionListener listener ;


    public FriendList() {
        listener = e -> FriendList.this.valueChanged(e);

        this.addListSelectionListener(listener);
        this.setBounds(10,40, 360,300);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setModel(new DefaultListModel());
    }


    public void valueChanged(ListSelectionEvent listSelectionEvent){
        JList list = (JList) listSelectionEvent.getSource();
        String selectedValue = (String) list.getSelectedValue();
        int selectedIndex = list.getSelectedIndex();
        if(selectedIndex!= -1) {
            chatPanel.writeLog(selectedValue + " " + selectedIndex);
        }
    }
}
