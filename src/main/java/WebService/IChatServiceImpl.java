package WebService;
import org.exolab.jms.administration.AdminConnectionFactory;
import org.exolab.jms.administration.JmsAdminServerIfc;

import javax.jms.*;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

@WebService(endpointInterface = "WebService.IChatService")
public class IChatServiceImpl implements IChatService {

    private static final String tcpAddress = "tcp://localhost:3035/";

    public boolean createQueue(String name) {
        try {
            JmsAdminServerIfc admin = AdminConnectionFactory.create(tcpAddress);
            Boolean isQueue = Boolean.TRUE;
            if (!admin.addDestination(name, isQueue)) {
                System.err.println("Failed to create queue " + name);
                return false;
            }
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<String> getAllMessages(String name) {
        List<String> result = new ArrayList<>();
        try{
            JmsAdminServerIfc admin = AdminConnectionFactory.create(tcpAddress);
            int total = admin.getQueueMessageCount(name);

            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, tcpAddress);

            Context context = new InitialContext(properties);

            QueueConnectionFactory qfactory = (QueueConnectionFactory) context.lookup("ConnectionFactory");


            QueueConnection qconnection = qfactory.createQueueConnection();
            QueueSession qsession = qconnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            qconnection.start();

            javax.jms.Queue dest = (javax.jms.Queue)context.lookup(name);
            QueueReceiver qreceiver = qsession.createReceiver(dest);


            TextMessage textMessage = null;

            while (total > 0) {
                textMessage = (TextMessage) qreceiver.receive();
                result.add(textMessage.getText());
                total--;
            }
            return result;

        }catch(Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public String writeMessage(String messageSend, String destination) {
        try {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.exolab.jms.jndi.InitialContextFactory");
            properties.put(Context.PROVIDER_URL, tcpAddress);

            Context context = null;
            context = new InitialContext(properties);

            QueueConnectionFactory qfactory = (QueueConnectionFactory) context.lookup("ConnectionFactory");

            QueueConnection qconnection = qfactory.createQueueConnection();
            QueueSession qsession = qconnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            TextMessage message = qsession.createTextMessage();
            message.setText(messageSend);

            javax.jms.Queue dest = (javax.jms.Queue) context.lookup(destination);
            QueueSender sender = qsession.createSender(dest);
            sender.send(message);

            context.close();
            qconnection.close();
        } catch (NamingException e) {
            e.printStackTrace();
            return "ERROR";
        } catch (JMSException e) {
            e.printStackTrace();
            return "ERROR";
        }

        return "OK";
    }
}
