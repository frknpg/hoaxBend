package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Hoax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 300)
    @Column(length = 300)
    private String content;

    @CreationTimestamp
    private Date timeStamp;

    @ManyToOne
    private User user;
}
