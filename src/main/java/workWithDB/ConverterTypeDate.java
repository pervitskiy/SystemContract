package workWithDB;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class ConverterTypeDate extends XmlAdapter<String, LocalDate> {

    /**
     * Convert String to LocalDate
     *
     * @param v the value to be converted
     * @return LocalDate date
     * @throws Exception -if error during the conversion
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * Convert LocalDate in String
     *
     * @param v the value to be converted
     * @return String date
     * @throws Exception -if error during the conversion
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
