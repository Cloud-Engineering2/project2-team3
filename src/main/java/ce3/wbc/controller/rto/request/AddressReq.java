package ce3.wbc.controller.rto.request;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AddressReq {
    @Nullable
    private String city;

    @Nullable
    private String street;

    @Nullable
    private String zipcode;

    public String getAddress() {
        return String.format("%s,%s,%s", city, street, zipcode);
    }

}

