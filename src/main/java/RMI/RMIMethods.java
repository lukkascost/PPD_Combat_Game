package RMI;

import java.rmi.registry.LocateRegistry;

import static MainPackage.ApplicationRun.ip;
import static MainPackage.ApplicationRun.port;

public class RMIMethods {
    public static IChat getRMIChatObject(String name ) throws Exception {
        try {
            return (IChat) LocateRegistry.getRegistry(ip,port).lookup(name+"-chat");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void setRMIChatObject(IChat newChat, String name) throws Exception {
        LocateRegistry.getRegistry(ip,port).rebind(name+"-chat",newChat);
    }

    public static boolean existFriendObject(String name){
        try {
            LocateRegistry.getRegistry(ip,port).lookup(name+"-chat");
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
