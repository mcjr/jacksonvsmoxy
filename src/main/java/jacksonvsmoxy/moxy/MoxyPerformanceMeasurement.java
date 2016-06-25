package jacksonvsmoxy.moxy;

import jacksonvsmoxy.common.SizeType;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import jacksonvsmoxy.common.AbstractPerformanceMeasurement;
import jacksonvsmoxy.common.MediaType;
import jacksonvsmoxy.common.TestType;
import jacksonvsmoxy.domain.Customer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class MoxyPerformanceMeasurement extends AbstractPerformanceMeasurement {

    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    public MoxyPerformanceMeasurement(TestType testType, SizeType sizeType, MediaType mediaType) {
        super(testType, sizeType, mediaType);
    }

    @Override
    protected void prepare() throws Exception {
        JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{Customer.class}, null);
        unmarshaller = jaxbContext.createUnmarshaller();
        if (getMediaType().equals(MediaType.JSON)) {
            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            unmarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
        }
        marshaller = jaxbContext.createMarshaller();
        if (getMediaType().equals(MediaType.JSON)) {
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        }
    }

    protected Object executeRead() throws Exception {
        StreamSource json = new StreamSource(new StringReader(customerFactory.createSerializedCustomer(getMediaType(), getSizeType())));
        return unmarshaller.unmarshal(json, Customer.class).getValue();
    }

    protected String executeWrite() throws Exception {
        Customer customer = customerFactory.createCustomer(getSizeType());
        JAXBElement<Customer> jaxbElement = new JAXBElement<>(new QName(null, "customer"), Customer.class, customer);
        StringWriter writer = new StringWriter();
        marshaller.marshal(jaxbElement, writer);
        return writer.toString();
    }

}
