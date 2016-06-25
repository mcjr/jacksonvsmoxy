package jacksonvsmoxy.moxy;

import jacksonvsmoxy.AbstractPerformanceMeasurementTest;
import jacksonvsmoxy.common.MediaType;
import jacksonvsmoxy.common.SizeType;
import jacksonvsmoxy.common.TestType;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MoxyPerformanceMeasurementTest extends AbstractPerformanceMeasurementTest {

    MoxyPerformanceMeasurement cut;

    @Test
    public void testReadSmallerJson() throws Exception {
        cut = createPerformanceTest(TestType.READ, SizeType.SMALLER, MediaType.JSON);
        Object result = cut.executeRead();
        assertNotEmptyCustomer(result);
    }

    @Test
    public void testReadBiggerJson() throws Exception {
        cut = createPerformanceTest(TestType.READ, SizeType.BIGGER, MediaType.JSON);
        Object result = cut.executeRead();
        assertNotEmptyCustomer(result);
    }

    @Test
    public void testWriteSmallerJson() throws Exception {
        cut = createPerformanceTest(TestType.WRITE, SizeType.SMALLER, MediaType.JSON);
        String result = cut.executeWrite();
        assertNotNull(result);
    }

    @Test
    public void testWriteBiggerJson() throws Exception {
        cut = createPerformanceTest(TestType.WRITE, SizeType.BIGGER, MediaType.JSON);
        for (int i = 0; i < 200; i++) {
            String result = cut.executeWrite();
            assertNotNull(result);
        }
    }

    private MoxyPerformanceMeasurement createPerformanceTest(TestType testType, SizeType sizeType, MediaType mediaType) throws Exception {
        MoxyPerformanceMeasurement result = new MoxyPerformanceMeasurement(testType, sizeType, mediaType);
        result.prepare();
        return result;
    }
}