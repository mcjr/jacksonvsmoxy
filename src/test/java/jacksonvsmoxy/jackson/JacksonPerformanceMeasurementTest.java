package jacksonvsmoxy.jackson;

import jacksonvsmoxy.common.SizeType;
import org.junit.Test;
import jacksonvsmoxy.AbstractPerformanceMeasurementTest;
import jacksonvsmoxy.common.MediaType;
import jacksonvsmoxy.common.TestType;
import jacksonvsmoxy.domain.Customer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static jacksonvsmoxy.common.MediaType.JSON;
import static jacksonvsmoxy.common.TestType.READ;
import static jacksonvsmoxy.common.TestType.WRITE;

public class JacksonPerformanceMeasurementTest extends AbstractPerformanceMeasurementTest {

    JacksonPerformanceMeasurement cut;

    @Test
    public void testReadSmallerJson() throws Exception {
        cut = createPerformanceMeasurement(READ, SizeType.SMALLER, JSON);
        Object result = cut.executeRead();
        assertNotEmptyCustomer(result);
    }

    @Test
    public void testReadBiggerJson() throws Exception {
        cut = createPerformanceMeasurement(READ, SizeType.BIGGER, JSON);
        Object result = cut.executeRead();
        assertNotNull(result);
        assertTrue(result instanceof Customer);
        assertNotEmptyCustomer((Customer) result);
    }

    @Test
    public void testWriteSmallerJson() throws Exception {
        cut = createPerformanceMeasurement(WRITE, SizeType.SMALLER, JSON);
        String result = cut.executeWrite();
        assertNotNull(result);
    }

    @Test
    public void testWriteBiggerJson() throws Exception {
        cut = createPerformanceMeasurement(WRITE, SizeType.BIGGER, JSON);
        for (int i = 0; i < 2; i++) {
            String result = cut.executeWrite();
            assertNotNull(result);
        }
    }

    private JacksonPerformanceMeasurement createPerformanceMeasurement(TestType testType, SizeType sizeType, MediaType mediaType) throws Exception {
        JacksonPerformanceMeasurement result = new JacksonPerformanceMeasurement(testType, sizeType, mediaType);
        result.prepare();
        return result;
    }
}