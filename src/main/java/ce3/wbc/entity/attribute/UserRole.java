package ce3.wbc.entity.attribute;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String roleType;

    UserRole(String roleType) {this.roleType = roleType;}

    public static UserRole getInstance(String roleType) {
        for (UserRole role : values()) {
            if (role.roleType.equals(roleType)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid roleType: " + roleType);
    }


}
