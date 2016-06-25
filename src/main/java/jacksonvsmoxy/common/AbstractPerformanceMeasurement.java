package jacksonvsmoxy.common;

import jacksonvsmoxy.domain.CustomerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractPerformanceMeasurement {

    final Logger LOG = Logger.getLogger(getClass().getSimpleName());

    protected final CustomerFactory customerFactory = new CustomerFactory();

    private TestType testType;
    private SizeType sizeType;
    private MediaType mediaType;

    public AbstractPerformanceMeasurement(TestType testType, SizeType sizeType, MediaType mediaType) {
        this.testType = testType;
        this.sizeType = sizeType;
        this.mediaType = mediaType;
    }

    public List<Long> measure(int count, int repeat) {
        List<Long> result = new ArrayList<>(count);
        try {
            prepare();
            for (int i = 0; i < count; i++) {
                long startTime = System.currentTimeMillis();
                execute(repeat);
                result.add(System.currentTimeMillis() - startTime);
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Measure failed!", e);
        }
        return result;
    }

    protected void execute(int repeat) throws Exception {
        for (int i = 0; i < repeat; i++) {
            switch (getTestType()) {
                case READ:
                    executeRead();
                    break;
                case WRITE:
                    executeWrite();
                    break;
                default:
            }
        }
    }

    protected abstract void prepare() throws Exception;

    protected abstract Object executeRead() throws Exception;

    protected abstract String executeWrite() throws Exception;

    public TestType getTestType() {
        return testType;
    }

    public SizeType getSizeType() {
        return sizeType;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
}
