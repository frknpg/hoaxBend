package com.frknpg.hoaxifybend.hoax.vm;

import com.frknpg.hoaxifybend.hoax.Hoax;
import com.frknpg.hoaxifybend.user.vm.UserVM;
import lombok.Data;

@Data
public class HoaxVM {

    private long id;

    private String content;

    private long timeStamp;

    private UserVM user;

    public HoaxVM(Hoax hoax) {
        this.setId(hoax.getId());
        this.setContent(hoax.getContent());
        this.setTimeStamp(hoax.getTimeStamp().getTime());
        this.setUser(new UserVM(hoax.getUser()));
    }
}
