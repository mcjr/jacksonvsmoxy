# Jackson versus MOXy

Performance comparison of popular java serialization frameworks.

## Performance Measuring

### Scope

Writing and reading of smaller and bigger XML or Json objects is measured.
The measurement encapsulates the reading or writing of 1000 objects.
Furthermore to minimize external effects every measurement is repeated.


### Reports

Test performance result are shown via a ConsoleReporter and a HtmlReporter.

The ConsoleReporter shows the count of successful test runs, the minimum, maximum, average and median value.

The HtmlReporter renders the results in the file "test-results.html".
Additionally to the values of the ConsoleReporter a distribution histogram is created (as svg graph).

All time based values are measured in milliseconds.


## Running

The performance comparison can be executed via

```
mvn exec:java -Dexec.mainClass="jacksonvsmoxy.performance.Comparison"
```

### Sample html output

[Test results](./test-results.html)

### Sample console output

```
ReadSmallerJSON1000
jacksonInJSON: #success=100, min=1, max=94, avg=4, median=2
moxyInJSON: #success=100, min=4, max=146, avg=10, median=5

ReadSmallerXML1000
jacksonInXML: #success=100, min=7, max=102, avg=13, median=12
moxyInXML: #success=100, min=5, max=91, avg=9, median=6

ReadBiggerJSON1000
jacksonInJSON: #success=100, min=5, max=70, avg=7, median=6
moxyInJSON: #success=100, min=53, max=202, avg=57, median=54

ReadBiggerXML1000
jacksonInXML: #success=100, min=14, max=39, avg=16, median=15
moxyInXML: #success=100, min=28, max=136, avg=30, median=29

WriteSmallerJSON1000
jacksonOutJSON: #success=100, min=0, max=51, avg=2, median=2
moxyOutJSON: #success=100, min=3, max=61, avg=8, median=6

WriteSmallerXML1000
jacksonOutXML: #success=100, min=3, max=58, avg=6, median=5
moxyOutXML: #success=100, min=2, max=34, avg=4, median=3
jdkJaxbOutXML: #success=100, min=3, max=51, avg=7, median=7

WriteBiggerJSON1000
jacksonOutJSON: #success=100, min=8, max=40, avg=9, median=9
moxyOutJSON: #success=100, min=17, max=65, avg=19, median=18

WriteBiggerXML1000
jacksonOutXML: #success=100, min=15, max=39, avg=16, median=16
moxyOutXML: #success=100, min=17, max=42, avg=18, median=18
jdkJaxbOutXML: #success=100, min=15, max=36, avg=16, median=16
```



## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details


