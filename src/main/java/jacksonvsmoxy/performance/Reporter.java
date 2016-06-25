package jacksonvsmoxy.performance;

public interface Reporter {
    void create(String title);

    void addDataSeries(DataSeries... dataSeries);

    void close();
}
