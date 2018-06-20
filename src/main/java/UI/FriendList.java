package UI;

import MainPackage.ApplicationRun;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static MainPackage.ApplicationRun.*;


class FriendList extends JList {


    FriendList() {
        ListSelectionListener listener = e -> valueChanged(e);

        this.addListSelectionListener(listener);
        this.setBounds(10,40, 360,300);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setModel(new DefaultListModel());
    }


    private void valueChanged(ListSelectionEvent listSelectionEvent){
        JList list = (JList) listSelectionEvent.getSource();
        String selectedValue = (String) list.getSelectedValue();
        int selectedIndex = list.getSelectedIndex();
        if(selectedIndex!= -1) {
            activeChat.setText("Nome: "+selectedValue);
            activeChatStatus.setSelected(chatPanel.isFriendChatOnline(selectedValue));
            chatPanel.updateActualChat();
        }
    }
}
