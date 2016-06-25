package jacksonvsmoxy.domain;

import jacksonvsmoxy.common.SizeType;
import jacksonvsmoxy.common.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class CustomerFactory {
    private static LatinLipsumWordFactory wordFactory = new LatinLipsumWordFactory();
    private Random random = new Random();
    private List<String> jsonCustomer = new ArrayList<>();

    public CustomerFactory() {
        load("CustomerJson.txt", this.jsonCustomer);
    }

    private void load(String resourceByName, Collection<String> intoCollection) {
        InputStream is = CustomerFactory.class.getClassLoader().getResourceAsStream(resourceByName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                intoCollection.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading resource " + resourceByName + " failed!", e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public Customer createCustomer(SizeType sizeType) {
        return sizeType.equals(SizeType.SMALLER) ? createSmallerCustomer() : createBiggerCustomer();
    }

    public Customer createSmallerCustomer() {
        Customer customer = new Customer();
        customer.setId(random.nextLong());
        customer.setName(wordFactory.createdWord());
        return customer;
    }

    public Customer createBiggerCustomer() {
        Customer customer = new Customer();
        customer.setId(random.nextLong());
        customer.setName(wordFactory.createdWord());
        customer.setLastContact(new Date());
        customer.setRating(BigDecimal.valueOf(random.nextDouble()));

        Address address = new Address();
        address.setStreet(wordFactory.createdWord());
        address.setCity(wordFactory.createdWord());
        customer.setAddress(address);

        for (int i = 0; i < 10; i++) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setCode(wordFactory.createdWord());
            phoneNumber.setValue(Long.toString(random.nextLong()));
            customer.getPhoneNumbers().add(phoneNumber);
        }

        return customer;
    }

    public String createSerializedCustomer(MediaType mediaType, SizeType sizeType) {
        if (MediaType.JSON.equals(mediaType) && SizeType.SMALLER.equals(sizeType)) {
            return createSmallerJson();
        }
        if (MediaType.XML.equals(mediaType) && SizeType.SMALLER.equals(sizeType)) {
            return createSmallerXml();
        }
        if (MediaType.JSON.equals(mediaType) && SizeType.BIGGER.equals(sizeType)) {
            return createBiggerJson();
        }
        if (MediaType.XML.equals(mediaType) && SizeType.BIGGER.equals(sizeType)) {
            return createBiggerXml();
        }
        throw new IllegalArgumentException();
    }

    public String createSmallerJson() {
        return "{\"id\":-1308343919879811736,\"name\":\"a_very_long_name_with_underscores\"}";
    }

    public String createSmallerXml() {
        return "<Customer><id>-2040255198962613316</id><name>lisi</name></Customer>";
    }

    public String createBiggerJson() {
        return jsonCustomer.get(random.nextInt(jsonCustomer.size()));
   }

    public String createBiggerXml() {
        return "<Customer><id>-2040255198962613316</id><name>lisi</name><address><street>tum</street><city>ico</city></address><phoneNumbers><phoneNumbers><code>work</code><value>2516937838071725254</value></phoneNumbers><phoneNumbers><code>cell</code><value>-7614816501068279363</value></phoneNumbers></phoneNumbers><rating>0.5951273924908492</rating></Customer>";
    }
}