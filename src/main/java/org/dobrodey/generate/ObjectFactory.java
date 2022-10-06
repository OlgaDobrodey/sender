
package org.dobrodey.generate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.dobrodey.generate package. 
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

    private final static QName _IOException_QNAME = new QName("http://router_web_service.example.com/", "IOException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.dobrodey.generate
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ListOfObject }
     * 
     */
    public ListOfObject createListOfObject() {
        return new ListOfObject();
    }

    /**
     * Create an instance of {@link ReportSender }
     * 
     */
    public ReportSender createReportSender() {
        return new ReportSender();
    }

    /**
     * Create an instance of {@link ListOfReportSender }
     * 
     */
    public ListOfReportSender createListOfReportSender() {
        return new ListOfReportSender();
    }

    /**
     * Create an instance of {@link ListOfString }
     * 
     */
    public ListOfString createListOfString() {
        return new ListOfString();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://router_web_service.example.com/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

}
