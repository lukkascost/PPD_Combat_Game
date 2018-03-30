package Chat;

import MainPackage.ApplicationRun;
import RMI.ChatImplementation;
import RMI.IChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import static MainPackage.ApplicationRun.ip;

public class ChatPanel extends JPanel{

    private JTextField writedText;
    private JButton send;
    private ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;
    private IChat chat ;
    private IChat chatEnemy ;



    public ChatPanel() throws RemoteException {
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

        logSP.setBounds(10,450,360,100);
        logSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        log.setBackground(Color.lightGray);
        log.setEditable(false);


        this.add(logSP);
        this.add(sp);
        this.add(writedText);
        this.add(send);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.setBounds(500,0,380,560);
        this.setVisible(true);
        chat = new ChatImplementation();
        LocateRegistry.getRegistry(ip).rebind(ApplicationRun.player,chat);

    }

    private void onClick(){
        if(!this.writedText.equals("")) {
            if (hasEnemy()) {
                try {
                    this.chat.writeMessage(this.writedText.getText(), ApplicationRun.player);
                    this.chatEnemy.writeMessage(this.writedText.getText(), ApplicationRun.player);
                    this.writedText.setText("");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else{
                this.log.append("Inimigo ainda nao conectado! Aguarde sua conexão...\n");
                this.writedText.setText("");
            }
        }
    }

    public ChatText getChatTextLog() {
        return chatTextLog;
    }

    public boolean hasEnemy(){
        if (this.chatEnemy !=  null) return true;
        try {
            chatEnemy = (IChat) LocateRegistry.getRegistry(ip).lookup(ApplicationRun.enemy);
            return true;
        }
        catch (NotBoundException e){
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
