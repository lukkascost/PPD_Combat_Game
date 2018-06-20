package UI;

import RMI.RMIMethods;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static MainPackage.ApplicationRun.*;


public class FriendList extends JList {


    public FriendList() {
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
            chatPanel.writedText.setEditable(true);
            activeChat.setText("Nome: "+selectedValue);
            activeChatStatus.setSelected(RMIMethods.isFriendChatOnline(selectedValue));
            chatPanel.updateActualChat();
        }
        else{
            chatPanel.writedText.setEditable(false);
        }
    }
    public void addInList(String name){
        DefaultListModel model = (DefaultListModel) this.getModel();
        model.addElement(name);
        this.setModel(model);
    }

}
