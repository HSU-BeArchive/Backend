package com.likelion.moamoa.domain.auth.entity;


import com.likelion.moamoa.domain.folder.entity.Folder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Column(name = "PASSWORD")
    private String password;

//    @OneToMany(mappedBy = "user")
//    private List<Folder> folders = new ArrayList<>();

}
