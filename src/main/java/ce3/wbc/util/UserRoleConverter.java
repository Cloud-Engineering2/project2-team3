package ce3.wbc.util;

import ce3.wbc.entity.attribute.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return attribute.getRoleType();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return UserRole.getInstance(dbData);
    }
}
