package com.example.wantedpreonboardingbackend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
public class Post {
    @Id
    private Long Id;

    private Long userId;

    private String title;

    private String content;

    private Date created_At;

    public void updateUserId(Long userId){this.userId = userId;}
    public void updateTitle(String title){this.title = title;}
    public void updateContent(String content){this.content = content;}

}
