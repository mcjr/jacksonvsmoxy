package jacksonvsmoxy;

import jacksonvsmoxy.domain.Customer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class AbstractPerformanceMeasurementTest {


    protected void assertNotEmptyCustomer(Object result) {
        assertNotNull(result);
        assertTrue(result instanceof Customer);

        Customer customer = (Customer) result;
        assertNotNull(customer.getId());
        assertNotNull(customer.getName());
    }

}
