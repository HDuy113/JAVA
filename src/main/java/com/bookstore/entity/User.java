package com.bookstore.entity;

import com.bookstore.validator.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.security.PrivateKey;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false,unique = true)
    @NotBlank(message = "Tên đăng nhập không được đẻ trống")
    @ValidUsername
    @Size(max = 50, message = "Tên đăng nhập phải ít hơn 50 ký tự")
    private String username;

    @Column(name= "password", length = 250, nullable = false)
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Email phải ít hơn 50 ký tự")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @NotBlank(message = "Tên của bạn không được đẻ trống")
    @Size(max = 50, message = "Tên của bạn phải ít hơn 50 ký tự")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> books;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
