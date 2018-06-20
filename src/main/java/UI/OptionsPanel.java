package UI;

import MainPackage.ApplicationRun;
import RMI.IChat;
import RMI.RMIMethods;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static MainPackage.ApplicationRun.*;

public class OptionsPanel extends JPanel {

    public FriendList friendList;
    private AbstractAction abstractActionAdd;
    private AbstractAction abstractActionRemove;
    private int count =0;

    public OptionsPanel(){
        this.setLayout(null);
        this.setBounds(0,0,380,560);
        this.abstractActionAdd =  new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                addFriend(e);
            }
        };
        this.abstractActionRemove = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                removeFriend(e);
            }
        };

        this.friendList = new FriendList();
        JScrollPane sp = new JScrollPane(friendList);
        sp.setBounds(10,40, 360,300);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton addFriend = new JButton("+");
        addFriend.setBounds(10,350, 30,30);
        addFriend.addActionListener(abstractActionAdd);

        JButton removeFriend = new JButton("-");
        removeFriend.setBounds(40,350, 30,30);
        removeFriend.addActionListener(abstractActionRemove);

        JLabel friendLabel = new JLabel("Amigos adicionados: ");
        friendLabel.setBounds(10,10,360,30);


        this.add(sp);
        this.add(friendLabel);
        this.add(addFriend);
        this.add(removeFriend);
    }

    public void addInList(String name){
        DefaultListModel model = (DefaultListModel) this.friendList.getModel();
        model.addElement(name);
        this.friendList.setModel(model);
    }
    private void addFriend(ActionEvent e) {
        count+=1;
        String newFriend;
        newFriend = JOptionPane.showInputDialog("Digite o nick do amigo.");
        if (!RMIMethods.existFriendObject(newFriend)) {
            chatPanel.writeLog("USUARIO NAO EXISTE!!!");
            return;
        }
        try {
            IChat friend = RMIMethods.getRMIChatObject(newFriend);
            addInList(newFriend);

            if(!ApplicationRun.friendChatContent.containsKey(newFriend)){
                ApplicationRun.friendChatContent.put(newFriend,"");
            }
            ApplicationRun.friendChatContent.replace(newFriend, friend.getChatWith(ApplicationRun.playerName));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    private void removeFriend(ActionEvent e) {
        if (this.friendList.getSelectedIndex() == -1){
            chatPanel.writeLog("Selecione um amigo para remover!");
            return;
        }
        DefaultListModel model = (DefaultListModel) this.friendList.getModel();
        model.removeElement(this.friendList.getSelectedValue());
        this.friendList.setModel(model);

        activeChat.setText("Nome: --");
        activeChatStatus.setSelected(false);
    }





}
