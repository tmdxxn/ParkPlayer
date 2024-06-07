package com.movie.parkplayer.component;

import com.movie.parkplayer.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
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

    /**
     * 사용자의 권한을 반환합니다.
     *
     * @return 사용자의 권한 컬렉션
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 현재 권한이 없는 상태로 반환하고 있으나, 실제 권한 로직을 추가할 수 있습니다.
        return Collections.emptyList();
    }

    /**
     * 사용자의 비밀번호를 반환합니다.
     *
     * @return 비밀번호
     */
    @Override
    public String getPassword() {
        return userEntity.getMemPassword();
    }

    /**
     * 사용자의 이름(ID)을 반환합니다.
     *
     * @return 사용자 이름(ID)
     */
    @Override
    public String getUsername() {
        return userEntity.getMemId();
    }

    /**
     * 사용자의 계정이 만료되지 않았는지 여부를 반환합니다.
     *
     * @return 계정 만료 여부
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 사용자의 계정이 잠겨있지 않은지 여부를 반환합니다.
     *
     * @return 계정 잠김 여부
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 사용자의 자격 증명이 만료되지 않았는지 여부를 반환합니다.
     *
     * @return 자격 증명 만료 여부
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자의 계정이 활성화되어 있는지 여부를 반환합니다.
     *
     * @return 계정 활성화 여부
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
