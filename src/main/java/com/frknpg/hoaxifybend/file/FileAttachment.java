package com.frknpg.hoaxifybend.file;

import com.frknpg.hoaxifybend.hoax.Hoax;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String fileType;

    @CreationTimestamp
    private Date date;

    @OneToOne
    private Hoax hoax;
}
