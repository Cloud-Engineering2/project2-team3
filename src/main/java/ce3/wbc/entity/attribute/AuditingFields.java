package ce3.wbc.entity.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public class AuditingFields {
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    protected LocalDateTime modifiedDate;
}
