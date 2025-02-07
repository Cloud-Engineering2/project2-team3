package ce3.wbc.entity.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    @Column(length = 30)
    private String city;

    @Column(length = 50)
    private String street;

    @Column(length = 50)
    private String zipcode;

    public String fullAddress() {
        return getCity() + getStreet() + getZipcode();
    }
}
