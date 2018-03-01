package Protocol;

import MainPackage.ApplicationRun;

import javax.swing.*;
import java.util.ArrayList;

public class Protocol {
    private MessageType messageType;
    private ArrayList<Fields> fields = new ArrayList<Fields>();


    public Protocol(ArrayList<JTextField> textFields){
        this.messageType = MessageType.MOVE_GAME;
        for (JTextField textField: textFields) {
            this.fields.add(new Fields(textField.getText(), ApplicationRun.colors.indexOf(textField.getBackground())));
        }
    }

    public Protocol() {

    }

    public Protocol(String message) {
        String[] splitString = message.split(" ");
        if (splitString[0].equals("02")) this.messageType = MessageType.MOVE_GAME;
    }


    @Override
    public String toString() {
        String retunable = messageType.getType()+ " ";
        for (Fields field: fields) {
            retunable+= field.toString()+" ";
        }
        return retunable;
    }
}
