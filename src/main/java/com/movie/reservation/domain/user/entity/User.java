package com.movie.reservation.domain.user.entity;

import com.movie.reservation.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IsWithDraw isWithDraw;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한 반환, 예: 역할에 기반한 권한
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isWithDraw.equals(IsWithDraw.WITHDRAW);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isWithDraw.equals(IsWithDraw.WITHDRAW);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isWithDraw.equals(IsWithDraw.WITHDRAW);
    }

    @Override
    public boolean isEnabled() {
        return !isWithDraw.equals(IsWithDraw.WITHDRAW);
    }

    public void withdraw() {
        this.isWithDraw = IsWithDraw.WITHDRAW;
    }

    public void logout() {
        this.refreshToken = "";
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }
}
