package com.frknpg.hoaxifybend.hoax;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Hoax {

    @Id
    @GeneratedValue
    private long id;

    @Size(min = 1, max = 300)
    @Column(length = 300)
    private String content;

    @CreationTimestamp
    private Date timeStamp;
}
