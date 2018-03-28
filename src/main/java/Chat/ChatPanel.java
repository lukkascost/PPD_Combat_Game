package Chat;

import MainPackage.ApplicationRun;
import RMI.IChat;
import Threads.ThreadChatReceive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ChatPanel extends JPanel{

    private JTextField writedText;
    private JButton send;
    private ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;
    private IChat chat ;


    public ChatPanel() throws RemoteException, NotBoundException, MalformedURLException {
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

//        chat = (IChat) Naming.lookup("rmi://localhost/Chat");
        chat = (IChat) LocateRegistry.getRegistry("localhost").lookup("Chat");
        chat.setChatText(this.chatTextLog.getText());
        new ThreadChatReceive().start();

    }

    private void onClick(){
        if(!this.writedText.equals("")) {
            try {
                this.chat.writeMessage(this.writedText.getText(), ApplicationRun.player);
                this.writedText.setText("");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeLog(String msg){
        this.log.append(msg+"\n");
        this.log.setCaretPosition(this.log.getDocument().getLength());

    }
    public ChatText getChatTextLog() {
        return chatTextLog;
    }

    public void setChatTextLog(ChatText chatTextLog) {
        this.chatTextLog = chatTextLog;
    }

    public IChat getChat() {
        return chat;
    }
}
