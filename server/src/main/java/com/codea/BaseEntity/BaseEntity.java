package com.codea.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
<<<<<<< HEAD
    private LocalDateTime created_at;
=======
    private LocalDateTime createdAt;
>>>>>>> be-feat/member

    @LastModifiedDate
    @Setter
    @Column(updatable = false)
<<<<<<< HEAD
    private LocalDateTime modified_at;
}
=======
    private LocalDateTime modifiedAt;
}
>>>>>>> be-feat/member
