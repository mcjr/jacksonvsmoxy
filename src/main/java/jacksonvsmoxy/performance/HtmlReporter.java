package jacksonvsmoxy.performance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class HtmlReporter implements Reporter {

    private static final String[] COLORS = {"red", "blue", "green", "aqua", "fuchsia", "lime", "maroon", "navy", "olive", "purple", "red", "silver", "teal"};

    private static final int WIDTH = 600;
    private static final int HEIGHT = 200;

    private FileWriter fileWriter;
    private int width;
    private int height;
    private int color = 0;

    public HtmlReporter(String path) throws IOException {
        this(path, WIDTH, HEIGHT);
    }

    public HtmlReporter(String path, int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.fileWriter = new FileWriter(path);
        fileWriter.write("<!DOCTYPE html>\n<html>\n<head>\n");
        fileWriter.write("<style>\n" +
                "      table, th, td { border: 1px solid; }\n" +
                "      table         { border-collapse: collapse; }\n" +
                "      caption       { text-align: left; }\n" +
                "</style>");
        fileWriter.write("</head>\n<body>\n");
    }

    public void create(String title) {
        try {
            fileWriter.write("<h1>" + title + "</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addDataSeries(DataSeries... dataSeries) {
        try {
            writeTable(dataSeries);
            writeGraph(dataSeries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            fileWriter.write("</body>\n</html>");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeTable(DataSeries... dataSeries) throws IOException {
        resetColor();

        fileWriter.write("<table><tr><th>Test</th><th>#success</th><th>min</th><th>max</th><th>avg</th><th>median</th><th>color</th></tr>");
        for (DataSeries data : dataSeries) {
            fileWriter.write("<tr><td>" + data.getName() +
                    "</td><td>" + data.getCount() +
                    "</td><td>" + data.getMin() +
                    "</td><td>" + data.getMax() +
                    "</td><td>" + data.getAvg() +
                    "</td><td>" + data.getMedian() +
                    "</td><td>" + "<svg width='32' height='3'><line x1='1' y1='2' x2='32' y2='2' style='stroke:" + currentColor() + "; stroke-width:3px;'/></svg>" +
                    "</td></tr>");
            nextColor();
        }
        fileWriter.write("</table>");
    }

    private void writeGraph(DataSeries... dataSeries) throws IOException {
        resetColor();

        double yMax = Arrays.asList(dataSeries).stream().map(x -> x.getMax()).max(Long::compare).get();

        fileWriter.write("<svg width='" + width + "' height='" + height + "'>\n");
        fileWriter.write("    <line x1='1' y1='" + height + "' x2='" + width + "' y2='" + height + "' style='stroke:gray; stroke-width:2px;' />\n");
        fileWriter.write("    <line x1='1' y1='" + height + "' x2='1'   y2='1'   style='stroke:gray; stroke-width:2px;' />\n\n");
        for (DataSeries data : dataSeries) {
            writeSVGDataSeries(data, yMax);
            nextColor();
        }
        fileWriter.write("</svg>\n");
    }

    private void writeSVGDataSeries(DataSeries data, double yMax) throws IOException {
        double dX = (double) width / data.getCount();
        double dY = (double) height / yMax;
        fileWriter.write("    <polyline fill='none' stroke='" + currentColor() + "' stroke-width='1px' points='\n");
        for (int i = 0; i < data.getCount(); i++) {
            fileWriter.write(" " + dX * i + " " + (height - dY * data.getData().get(i)) + ",");
        }
        fileWriter.write(" ' />\n");
    }

    private void resetColor() {
        color = 0;
    }

    private String currentColor() {
        return COLORS[color];
    }

    private String nextColor() {
        return COLORS[color++ % COLORS.length];
    }
}
