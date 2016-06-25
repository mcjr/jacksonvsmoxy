package jacksonvsmoxy.performance;

import jacksonvsmoxy.common.SizeType;
import jacksonvsmoxy.common.TestType;
import jacksonvsmoxy.jackson.JacksonPerformanceMeasurement;
import jacksonvsmoxy.moxy.MoxyPerformanceMeasurement;
import jacksonvsmoxy.jdkjaxb.JdkJaxXMLPerformanceMeasurement;

import java.io.IOException;

import static jacksonvsmoxy.common.MediaType.JSON;
import static jacksonvsmoxy.common.MediaType.XML;

public class Comparison {
    public static final int MAX_REPEAT = 1000;
    public static final int COUNT = 100;
    
    public static void main(String[] args) throws IOException {
        Reporter reporter = new CompositeReporter(new ConsoleReporter(), new HtmlReporter("test-results.html"));

        reporter.create("ReadSmallerJSON" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonInJSON", new JacksonPerformanceMeasurement(TestType.READ, SizeType.SMALLER, JSON).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyInJSON", new MoxyPerformanceMeasurement(TestType.READ, SizeType.SMALLER, JSON).measure(COUNT, MAX_REPEAT)));

        reporter.create("ReadSmallerXML" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonInXML", new JacksonPerformanceMeasurement(TestType.READ, SizeType.SMALLER, XML).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyInXML", new MoxyPerformanceMeasurement(TestType.READ, SizeType.SMALLER, XML).measure(COUNT, MAX_REPEAT)));

        reporter.create("ReadBiggerJSON" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonInJSON", new JacksonPerformanceMeasurement(TestType.READ, SizeType.BIGGER, JSON).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyInJSON", new MoxyPerformanceMeasurement(TestType.READ, SizeType.BIGGER, JSON).measure(COUNT, MAX_REPEAT)));

        reporter.create("ReadBiggerXML" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonInXML", new JacksonPerformanceMeasurement(TestType.READ, SizeType.BIGGER, XML).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyInXML", new MoxyPerformanceMeasurement(TestType.READ, SizeType.BIGGER, XML).measure(COUNT, MAX_REPEAT)));

        reporter.create("WriteSmallerJSON" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonOutJSON", new JacksonPerformanceMeasurement(TestType.WRITE, SizeType.SMALLER, JSON).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyOutJSON", new MoxyPerformanceMeasurement(TestType.WRITE, SizeType.SMALLER, JSON).measure(COUNT, MAX_REPEAT)));

        reporter.create("WriteSmallerXML" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonOutXML", new JacksonPerformanceMeasurement(TestType.WRITE, SizeType.SMALLER, XML).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyOutXML", new MoxyPerformanceMeasurement(TestType.WRITE, SizeType.SMALLER, XML).measure(COUNT, MAX_REPEAT)),
                new DataSeries("jdkJaxbOutXML", new JdkJaxXMLPerformanceMeasurement(TestType.WRITE, SizeType.SMALLER, XML).measure(COUNT, MAX_REPEAT)));

        reporter.create("WriteBiggerJSON" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonOutJSON", new JacksonPerformanceMeasurement(TestType.WRITE, SizeType.BIGGER, JSON).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyOutJSON", new MoxyPerformanceMeasurement(TestType.WRITE, SizeType.BIGGER, JSON).measure(COUNT, MAX_REPEAT)));

        reporter.create("WriteBiggerXML" + MAX_REPEAT);
        reporter.addDataSeries(
                new DataSeries("jacksonOutXML", new JacksonPerformanceMeasurement(TestType.WRITE, SizeType.BIGGER, XML).measure(COUNT, MAX_REPEAT)),
                new DataSeries("moxyOutXML", new MoxyPerformanceMeasurement(TestType.WRITE, SizeType.BIGGER, XML).measure(COUNT, MAX_REPEAT)),
                new DataSeries("jdkJaxbOutXML", new JdkJaxXMLPerformanceMeasurement(TestType.WRITE, SizeType.BIGGER, XML).measure(COUNT, MAX_REPEAT)));

        reporter.close();
    }
}
