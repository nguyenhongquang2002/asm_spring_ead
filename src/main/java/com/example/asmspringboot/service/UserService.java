package com.example.asmspringboot.service;

import com.example.asmspringboot.entity.Credential;
import com.example.asmspringboot.entity.User;
import com.example.asmspringboot.entity.dto.UserLoginDto;
import com.example.asmspringboot.entity.dto.UserRegisterDto;
import com.example.asmspringboot.entity.entityEnum.UserRole;
import com.example.asmspringboot.entity.entityEnum.UserStatus;
import com.example.asmspringboot.repository.UserRepository;
import com.example.asmspringboot.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserRegisterDto register(UserRegisterDto userRegisterDto) {
        Optional<User> optionalUser = userRepository.findUserByUsername(userRegisterDto.getUsername());
        if (optionalUser.isPresent()) {
            return null;
        }
        User user = User.builder()
                .fullName(userRegisterDto.getFullName())
                .email(userRegisterDto.getEmail())
                .phone(userRegisterDto.getPhone())
                .address(userRegisterDto.getAddress())
                .avatar(userRegisterDto.getAvatar())
                .username(userRegisterDto.getUsername())
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .build();
        userRepository.save(user);
        UserRegisterDto userDto = UserRegisterDto.builder()
                .fullName(userRegisterDto.getFullName())
                .email(userRegisterDto.getEmail())
                .phone(userRegisterDto.getPhone())
                .address(userRegisterDto.getAddress())
                .avatar(userRegisterDto.getAvatar())
                .username(userRegisterDto.getUsername())
                .role(UserRole.USER)
                .id(user.getId())
                .build();
        return userDto;
    }

    public Credential login(UserLoginDto userLoginDto) {
        User user = (User) loadUserByUsername(userLoginDto.getUsername());
        boolean isMatched = passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
        Optional<User> optionalUser = userRepository.findUserByUsername(userLoginDto.getUsername());
        if(optionalUser.isPresent()) {
            User optionUser = optionalUser.get();
            int expiredAfterDay = 7;
            String accessToken = JwtUtil.generateTokenV2(optionUser, expiredAfterDay );
            String refreshToken = JwtUtil.generateTokenV2(optionUser, 14);
            return Credential.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expiredAt(System.currentTimeMillis() + expiredAfterDay * 24 * 60 * 60 * 60)
                    .scope("basic_user_info")
                    .build();
        }
        throw  new UsernameNotFoundException("User not found");
      /*  if(isMatched) {
            JwtUtil.generateToken(user.get);
        }*/
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole() == UserRole.ADMIN ? "ADMIN" : "USER");
        grantedAuthorityList.add(simpleGrantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);
//        return new MyUserPrincipal(user.get());
    }
}