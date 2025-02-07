package ce3.wbc.entity.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public String fullAddress() {
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

}
