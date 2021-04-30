package workWithDB;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class ConverterTypeDate extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
