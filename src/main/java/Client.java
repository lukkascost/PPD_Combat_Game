import javax.swing.*;

public class Client {
    private JPanel panelPrincipal;
    private JTextArea txtChat;
    private JTextArea txtEscrita;
    private JButton btnEnviar;
    private JButton button1;
    private JButton button7;
    private JButton button6;
    private JButton button2;
    private JPanel PANEL;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().PANEL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
