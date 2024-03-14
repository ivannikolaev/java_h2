package com.example.simple.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@Table(name = "COMMENT")
//@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    private long id;

    @Column(name = "MESSAGE")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;


//    @CreatedDate
//    @Column(name = "created_date", nullable = false, updatable = false)
//    private Instant createdDate;
//
//    @LastModifiedDate
//    @Column(name = "last_modified_date", nullable = false)
//    private Instant lastModifiedDate;
//
//    @Version
//    @Column(name = "version", nullable = false)
//    private Integer version;
}
