package jacksonvsmoxy.performance;

import java.util.Arrays;
import java.util.List;

public class CompositeReporter implements Reporter {
    private List<Reporter> reporters;

    public CompositeReporter(Reporter... reporters) {
        this.reporters = Arrays.asList(reporters);
    }

    @Override
    public void create(String title) {
        reporters.forEach(reporter -> reporter.create(title));
    }

    @Override
    public void addDataSeries(DataSeries... dataSeries) {
        reporters.forEach(reporter -> reporter.addDataSeries(dataSeries));
    }

    @Override
    public void close() {
        reporters.forEach(Reporter::close);
    }
}
