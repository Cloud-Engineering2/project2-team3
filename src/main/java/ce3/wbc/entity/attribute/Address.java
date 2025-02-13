package ce3.wbc.entity.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

    @Column(length = 30)
    private String city;

    @Column(length = 50)
    private String street;

    @Column(length = 50)
    private String zipcode;

    public String getAddress() {
        return String.format("%s,%s,%s", city, street, zipcode);
    }

    public boolean isEmpty() {
        return (city == null || city.isBlank()) &&
                (street == null || street.isBlank()) &&
                (zipcode == null || zipcode.isBlank());
    }

    public static Address of(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return getCity() != null && Objects.equals(getCity(), address.getCity())
                && getStreet() != null && Objects.equals(getStreet(), address.getStreet())
                && getZipcode() != null && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
