package jacksonvsmoxy.performance;

import java.util.List;

public class DataSeries {
    private String name;
    private List<Long> data;

    public DataSeries(String name, List<Long> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public List<Long> getData() {
        return data;
    }

    public Long getMax() {
        return data.stream().max(Long::compare).get();
    }

    public Long getMin() {
        return data.stream().min(Long::compare).get();
    }

    public Long getAvg() {
        return data.stream().mapToLong(Long::longValue).sum() / data.size();
    }

    public Long getMedian() {
        final int mid = data.size() / 2;
        if (data.size()%2>0) {
            return data.stream().sorted().skip(mid).findFirst().get();
        }
        return data.stream().sorted().skip(mid-1).limit(2).mapToLong(Long::longValue).sum() / 2;
    }

    public int getCount() {
        return data.size();
    }
}
