
package WebService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WebService package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WriteMessage_QNAME = new QName("http://WebService/", "writeMessage");
    private final static QName _CreateQueue_QNAME = new QName("http://WebService/", "createQueue");
    private final static QName _WriteMessageResponse_QNAME = new QName("http://WebService/", "writeMessageResponse");
    private final static QName _GetAllMessagesResponse_QNAME = new QName("http://WebService/", "getAllMessagesResponse");
    private final static QName _CreateQueueResponse_QNAME = new QName("http://WebService/", "createQueueResponse");
    private final static QName _GetAllMessages_QNAME = new QName("http://WebService/", "getAllMessages");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WebService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllMessages }
     * 
     */
    public GetAllMessages createGetAllMessages() {
        return new GetAllMessages();
    }

    /**
     * Create an instance of {@link CreateQueueResponse }
     * 
     */
    public CreateQueueResponse createCreateQueueResponse() {
        return new CreateQueueResponse();
    }

    /**
     * Create an instance of {@link GetAllMessagesResponse }
     * 
     */
    public GetAllMessagesResponse createGetAllMessagesResponse() {
        return new GetAllMessagesResponse();
    }

    /**
     * Create an instance of {@link CreateQueue }
     * 
     */
    public CreateQueue createCreateQueue() {
        return new CreateQueue();
    }

    /**
     * Create an instance of {@link WriteMessageResponse }
     * 
     */
    public WriteMessageResponse createWriteMessageResponse() {
        return new WriteMessageResponse();
    }

    /**
     * Create an instance of {@link WriteMessage }
     * 
     */
    public WriteMessage createWriteMessage() {
        return new WriteMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "writeMessage")
    public JAXBElement<WriteMessage> createWriteMessage(WriteMessage value) {
        return new JAXBElement<WriteMessage>(_WriteMessage_QNAME, WriteMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQueue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "createQueue")
    public JAXBElement<CreateQueue> createCreateQueue(CreateQueue value) {
        return new JAXBElement<CreateQueue>(_CreateQueue_QNAME, CreateQueue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "writeMessageResponse")
    public JAXBElement<WriteMessageResponse> createWriteMessageResponse(WriteMessageResponse value) {
        return new JAXBElement<WriteMessageResponse>(_WriteMessageResponse_QNAME, WriteMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMessagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "getAllMessagesResponse")
    public JAXBElement<GetAllMessagesResponse> createGetAllMessagesResponse(GetAllMessagesResponse value) {
        return new JAXBElement<GetAllMessagesResponse>(_GetAllMessagesResponse_QNAME, GetAllMessagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQueueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "createQueueResponse")
    public JAXBElement<CreateQueueResponse> createCreateQueueResponse(CreateQueueResponse value) {
        return new JAXBElement<CreateQueueResponse>(_CreateQueueResponse_QNAME, CreateQueueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "getAllMessages")
    public JAXBElement<GetAllMessages> createGetAllMessages(GetAllMessages value) {
        return new JAXBElement<GetAllMessages>(_GetAllMessages_QNAME, GetAllMessages.class, null, value);
    }

}
