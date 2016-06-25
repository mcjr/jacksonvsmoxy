package jacksonvsmoxy.common;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String marshal(Date v) throws Exception {
        return v == null ? null : dateFormat.format(v);
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return v == null ? null : dateFormat.parse(v);
    }

}