package com.sfnvm.personalblog.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JpaAudit {

  @CreatedBy
  @Column(name = "created_by", length = 50, nullable = false, updatable = false)
  private String createdBy;

  @CreatedDate
  @Column(name = "created_date", nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedBy
  @Column(name = "last_modified_By", length = 50, nullable = false)
  private String lastModifiedBy;

  @LastModifiedDate
  @Column(name = "last_modified_date", nullable = false)
  private Instant lastModifiedDate;
}
