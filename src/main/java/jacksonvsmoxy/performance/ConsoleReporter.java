package jacksonvsmoxy.performance;


public class ConsoleReporter implements Reporter {

    @Override
    public void create(String title) {
        System.out.println(title);
    }

    @Override
    public void addDataSeries(DataSeries... dataSeries) {
        for (DataSeries data : dataSeries) {
            System.out.println(data.getName() + ": #success=" + data.getCount() + ", min=" + data.getMin() + ", max=" + data.getMax() + ", avg=" + data.getAvg()+ ", median=" + data.getMedian());
        }
    }

    @Override
    public void close() {
    }

}
