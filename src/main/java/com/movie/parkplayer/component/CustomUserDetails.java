package com.movie.parkplayer.component;

import com.movie.parkplayer.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// 사용자 권한 관련 ( * 아직 이해를 완벽히 못한 사항이라 잘못된 설명을 할수 있어 GPT설명으로 대체하겠습니다.. * )
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    /**
     * CustomUserDetails 생성자.
     *
     * @param userEntity 사용자 엔티티 객체
     */
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    // 권한 여부
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().name()));
    }

    // 사용자 pw 반환
    @Override
    public String getPassword() {
        return userEntity.getMemPassword();
    }

    // 사용자 id 반환
    @Override
    public String getUsername() {
        return userEntity.getMemId();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
