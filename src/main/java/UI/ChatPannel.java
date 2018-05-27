package UI;

import Main.ChatUserUI;
import Tup.Environment;
import Tup.User;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static Main.ChatUserUI.space;

public class ChatPannel extends JPanel{
    private JTextField writedText;
    private JButton send;
    private ChatText chatTextLog;
    private AbstractAction abstractAction;
    private JComboBox comboBoxUser;


    public ChatPannel() {
        this.writedText = new JTextField();
        this.chatTextLog = new ChatText();
        this.send = new JButton("->",new ImageIcon("src/java/Chat/send.png"));
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        };
        send.setBounds(335,415,40,30);
        send.addActionListener(this.abstractAction);

        writedText.setBounds(10,415,320,30);
        writedText.addActionListener(this.abstractAction);


        JScrollPane sp = new JScrollPane(chatTextLog);   // JTextArea is placed in a JScrollPane.
        sp.setBounds(10,10,360,400);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.comboBoxUser = new JComboBox();
        comboBoxUser.setBounds(10,300,360,400);
        try {
            boxFill();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        }
        if (comboBoxUser.getItemCount() > 0) {
            comboBoxUser.setSelectedIndex(0);
            this.updateChat();
        }
        comboBoxUser.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(sp);
        this.add(writedText);
        this.add(send);
        this.add(comboBoxUser);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.setBounds(0,0,380,560);
        this.setVisible(true);
    }
    private void onClick() {

        if(comboBoxUser.getItemCount()  == 0 ){
            JOptionPane.showMessageDialog(null,"Nao existe Usuario cadastrado.");
            return;
        }
        if (this.writedText.getText().equals("")) return;

        User user = new User();
        user.name = comboBoxUser.getSelectedItem().toString();

        try {
            user = (User) space.readIfExists(user, null, Long.MAX_VALUE);
            Environment env = new Environment();
            env.name = user.environment;
            env = (Environment) space.takeIfExists(env, null, Long.MAX_VALUE);
            env.chat += "\n" +user.name+" - "+ this.writedText.getText();
            space.write(env,null,Lease.FOREVER);
            this.writedText.setText("");
            this.updateChat();
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void updateChat(){
        if(this.comboBoxUser.getItemCount()  == 0 ){
            return;
        }
        User user = new User();
        user.name = comboBoxUser.getSelectedItem().toString();

        try {
            user = (User) space.readIfExists(user, null, Long.MAX_VALUE);
            Environment env = new Environment();
            env.name = user.environment;
            env = (Environment) space.readIfExists(env, null, Long.MAX_VALUE);
            this.chatTextLog.setText(env.chat);
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void boxFill() throws RemoteException, TransactionException, InterruptedException, UnusableEntryException {
        this.comboBoxUser.removeAllItems();
        List<User> userList = new ArrayList<>();
        User result;
        do{
            result = new User();
            result = (User) space.takeIfExists(result, null, Long.MAX_VALUE);
            if (result != null){
                userList.add(result);
                comboBoxUser.addItem(result.name);
            }
        }while (result!= null);

        for (User u :userList) {
            space.write( u ,null, Lease.FOREVER);
        }
    }

}

