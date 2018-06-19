package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static MainPackage.ApplicationRun.activeChat;
import static MainPackage.ApplicationRun.activeChatStatus;
import static MainPackage.ApplicationRun.chatPanel;

public class OptionsPanel extends JPanel {

    private FriendList friendList;
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

    private void addFriend(ActionEvent e) {
        DefaultListModel model = (DefaultListModel) this.friendList.getModel();
        count+=1;
        String newFriend = JOptionPane.showInputDialog("Digite o nick do amigo.");
        //TODO saber se o amigo existe ou nao.
        model.addElement(newFriend);
        this.friendList.setModel(model);
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

    public String getSelectedFriendName(){
        return (String) this.friendList.getSelectedValue();
    }

}
