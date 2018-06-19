package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OptionsPanel extends JPanel {

    private FriendList friendList;
    private AbstractAction abstractAction;


    public OptionsPanel(){
        this.setLayout(null);
        this.setBounds(0,0,380,560);
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                    onClick();
               
            }
        };


        this.friendList = new FriendList();
        JScrollPane sp = new JScrollPane(friendList);
        sp.setBounds(10,40, 360,300);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        JButton addFriend = new JButton("+");
        addFriend.setBounds(10,370, 30,30);
        addFriend.addActionListener(abstractAction);

        JLabel friendLabel = new JLabel("Amigos adicionados: ");
        friendLabel.setBounds(10,10,360,30);


        this.add(sp);
        this.add(friendLabel);
        this.add(addFriend);
    }

    private void onClick() {
        DefaultListModel model = (DefaultListModel) this.friendList.getModel();
        model.addElement("AnotherFriend");
        this.friendList.setModel(model);
    }
}
