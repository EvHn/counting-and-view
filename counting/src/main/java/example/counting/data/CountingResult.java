package example.counting.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.Objects;

/**
 * Bean containing count data
 * @author evgkhan
 */
@XmlType(name = "CountingResult")
@XmlRootElement
public class CountingResult {
    public CountingResult() {
    }

    public CountingResult(Type type, int count, Date date) {
        this.type = type;
        this.count = count;
        this.date = date;
    }

    @XmlAttribute
    private Type type;
    @XmlAttribute
    private int count;
    @XmlAttribute
    private Date date;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        CountingResult that = (CountingResult) o;

        if(count != that.count) return false;
        if(type != that.type) return false;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + count;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
