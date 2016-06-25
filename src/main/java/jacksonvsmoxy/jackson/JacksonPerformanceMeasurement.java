package jacksonvsmoxy.jackson;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import jacksonvsmoxy.common.AbstractPerformanceMeasurement;
import jacksonvsmoxy.common.MediaType;
import jacksonvsmoxy.common.SizeType;
import jacksonvsmoxy.common.TestType;
import jacksonvsmoxy.domain.Customer;


public class JacksonPerformanceMeasurement extends AbstractPerformanceMeasurement {

    private ObjectMapper mapper;

    public JacksonPerformanceMeasurement(TestType testType, SizeType sizeType, MediaType mediaType) {
        super(testType, sizeType, mediaType);
    }

    @Override
    protected void prepare() throws Exception {
        mapper = getMediaType().equals(MediaType.XML) ? new XmlMapper() : new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.enable(MapperFeature.AUTO_DETECT_GETTERS);
        mapper.registerModule(new Jdk8Module());
    }

    protected Object executeRead() throws Exception {
        return mapper.readValue(customerFactory.createSerializedCustomer(getMediaType(), getSizeType()), Customer.class);
    }

    protected String executeWrite() throws Exception {
        return mapper.writeValueAsString(customerFactory.createCustomer(getSizeType()));
    }
}
