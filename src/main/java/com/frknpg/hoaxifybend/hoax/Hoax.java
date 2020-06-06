package com.frknpg.hoaxifybend.hoax;

import com.frknpg.hoaxifybend.file.FileAttachment;
import com.frknpg.hoaxifybend.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Hoax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 300)
    private String content;

    @CreationTimestamp
    private Date timeStamp;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "hoax")
    private FileAttachment fileAttachment;
}
