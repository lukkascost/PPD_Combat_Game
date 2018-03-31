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

import static MainPackage.ApplicationRun.*;

public class ChatPanel extends JPanel{

    private JTextField writedText;
    private JButton send;
    private ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;
    public IChat chat ;
    public IChat chatEnemy ;



    public ChatPanel() throws RemoteException {
        this.writedText = new JTextField();
        this.chatTextLog = new ChatText();
        this.send = new JButton("->",new ImageIcon("src/java/Chat/send.png"));
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onClick();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
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
        LocateRegistry.getRegistry(ip).rebind(ApplicationRun.player+"-chat",chat);

    }

    private void onClick() throws RemoteException {
        if(!this.writedText.getText().equals("")) {
            if (hasEnemy()) {
                try {
                    this.chat.writeMessage(this.writedText.getText(), ApplicationRun.player);
                    this.chatEnemy.writeMessage(this.writedText.getText(), ApplicationRun.player);
                    this.writedText.setText("");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else{
                this.writeLog("Inimigo ainda nao conectado! Aguarde sua conexão...");
                this.writedText.setText("");
            }
        }
    }

    public ChatText getChatTextLog() {
        return chatTextLog;
    }


    public boolean hasEnemy() throws RemoteException {
        if (this.chatEnemy !=  null) return true;
        try {
            chatEnemy = (IChat) LocateRegistry.getRegistry(ip).lookup(ApplicationRun.enemy+"-chat");
            gamePanel.restart.setEnabled(true);
            gamePanel.surrender.setEnabled(true);
            return true;
        }
        catch (NotBoundException ignored){
        } catch (AccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void writeLog(String s) {
        this.log.append(s+"\n");
        this.log.setCaretPosition(this.log.getDocument().getLength());

    }
    public void writeLogBoth(String s) throws RemoteException {
        this.log.append(player+": "+s+"\n");
        this.chatEnemy.writeLog(s,player);
    }
    public void iWin() throws RemoteException {
        chat.writeLog("Você ganhou!!",player);
        chatEnemy.writeLog("Você Perdeu!!",enemy);
    }
    public void iLose() throws RemoteException {
        chatEnemy.writeLog("Você ganhou!!",enemy);
        chat.writeLog("Você Perdeu!!",player);
    }
    public void restart() throws RemoteException, NotBoundException {
        int enemyResult = chatEnemy.restartMatch();
        if (enemyResult == 0){
            int myResult = chat.restartMatch();
            if(myResult == 0){
                ApplicationRun.frame.dispose();
                ApplicationRun.iniciaPartida();
                chatEnemy.atualizaGlobal();
                chat.writeLog("Partida Reiniciada.","System");
                chatEnemy.writeLog("Partida Reiniciada.","System");
            }else{
                chatEnemy.writeLog("Seu inimigo nao aceitou o reinicio.","System");
            }
        }else{
            chat.writeLog("Seu inimigo nao aceitou o reinicio.","System");
        }

    }
}
