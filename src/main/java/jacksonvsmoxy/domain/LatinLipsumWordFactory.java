package jacksonvsmoxy.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class LatinLipsumWordFactory {

    private static final String RESOURCE_FILE = "LatinLipsum.txt";

    private Random random = new Random();
    private String text;

    public LatinLipsumWordFactory() {
        InputStream input = getClass().getClassLoader().getResourceAsStream(RESOURCE_FILE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();
        String currLine;
        try {
            while ((currLine = bufferedReader.readLine()) != null) {
                sb.append(currLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading from " + RESOURCE_FILE + " failed!", e);
        }
        text = sb.toString();
    }


    public String createdWord() {
        int position = random.nextInt(text.length() - 100);
        StringTokenizer tokenizer = new StringTokenizer(text.substring(position, position + 100), " ,.");
        return tokenizer.nextToken();
    }
}
