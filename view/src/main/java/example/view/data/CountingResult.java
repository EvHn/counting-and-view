package example.view.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.Objects;

/**
 * Bean for receiving and storing counting result data
 * @author evgkhan
 */
@Entity
@Table(name = "counting_result")
@XmlType(name = "CountingResult")
@XmlRootElement
public class CountingResult {

    public static final String DATE = "date";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private int count;
    private Date date;

    public CountingResult() {
    }

    public CountingResult(int id, String type, int count, Date date) {
        this.id = id;
        this.type = type;
        this.count = count;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }
    @XmlAttribute
    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }
    @XmlAttribute
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        CountingResult that = (CountingResult) o;

        if(id != that.id) return false;
        if(count != that.count) return false;
        if(!Objects.equals(type, that.type)) return false;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + count;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
