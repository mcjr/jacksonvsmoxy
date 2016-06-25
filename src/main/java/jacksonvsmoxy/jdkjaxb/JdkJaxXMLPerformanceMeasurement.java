package jacksonvsmoxy.jdkjaxb;

import jacksonvsmoxy.common.AbstractPerformanceMeasurement;
import jacksonvsmoxy.common.MediaType;
import jacksonvsmoxy.common.SizeType;
import jacksonvsmoxy.common.TestType;
import jacksonvsmoxy.domain.Customer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;

public class JdkJaxXMLPerformanceMeasurement extends AbstractPerformanceMeasurement {

    private Marshaller marshaller;

    public JdkJaxXMLPerformanceMeasurement(TestType testType, SizeType sizeType, MediaType mediaType) {
        super(testType, sizeType, mediaType);
    }

    @Override
    protected void prepare() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        marshaller = jaxbContext.createMarshaller();
    }

    @Override
    protected Object executeRead() throws Exception {
        // TODO add implementation
        return null;
    }

    @Override
    protected String executeWrite() throws Exception {
        if (getMediaType().equals(MediaType.JSON)) {
            throw new RuntimeException("Unsupported media type");
        }

        Customer customer = customerFactory.createCustomer(getSizeType());
        JAXBElement<Customer> jaxbElement = new JAXBElement<>(new QName(null, "customer"), Customer.class, customer);
        StringWriter writer = new StringWriter();
        marshaller.marshal(jaxbElement, writer);
        return writer.toString();

    }
}
