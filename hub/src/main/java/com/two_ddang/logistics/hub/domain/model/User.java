package com.two_ddang.logistics.hub.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity(name = "p_users")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("사용자")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @Comment("사용자 로그인 ID")
    private String username;

    @Column(nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(nullable = false)
    @Comment("이름")
    private String name;

    @Column(unique = true)
    @Comment("이메일")
    private String email;

    @Column(nullable = false, unique = true)
    @Comment("연락처")
    private String contact;

    @Column(nullable = false)
    @Comment("계정 타입")
    @Enumerated(EnumType.STRING)
    private UserType role;

    @Column(nullable = false, unique = true)
    @Comment("계정 타입")
    private UUID slackId;

    private User(String username, String password, String name, String email, String contact, UserType role, UUID slackId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.role = role;
        this.slackId = slackId;
    }

    public static User of(String username, String password, String name, String email, String contact, UserType role) {
        return new User(username, password, name, email, contact, role, UUID.randomUUID());
    }

    public void modifyPassword(String password) {
        this.password = password;
    }

    public void modifyUserInfo(String username, String email, String contact) {
        this.username = username;
        this.email = email;
        this.contact = contact;
    }

}
