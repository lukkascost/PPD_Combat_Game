package RMI;

import java.rmi.registry.LocateRegistry;

import static MainPackage.ApplicationRun.ip;

public class RMIMethods {
    public static IChat getRMIChatObject(String name ) throws Exception {
        try {
            return (IChat) LocateRegistry.getRegistry(ip,4040).lookup(name+"-chat");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void setRMIChatObject(IChat newChat, String nick) throws Exception {
        LocateRegistry.getRegistry(ip,4040).rebind(nick+"-chat",newChat);
    }

    public static boolean existFriendObject(String name){
        try {
            LocateRegistry.getRegistry(ip,4040).lookup(name+"-chat");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFriendChatOnline(String selectedValue) {
        try {
            IChat friend = RMIMethods.getRMIChatObject(selectedValue);
            return friend.isOnlineChecked();
        } catch (Exception e) {
            return false;
        }
    }



}
