package com.frknpg.hoaxifybend.hoax;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Hoax {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String content;

    @CreationTimestamp
    private Date timeStamp;
}
