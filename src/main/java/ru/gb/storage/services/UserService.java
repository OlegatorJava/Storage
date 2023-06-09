package ru.gb.storage.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.storage.converters.UserMapper;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.entities.Role;
import ru.gb.storage.entities.User;
import ru.gb.storage.exceptions.ResourceNotFoundExceptions;
import ru.gb.storage.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String name) {
        return userRepository.findByName(name);

    }
    @Transactional
    public List<UserDetails> findAllUsersDetails(){
        List<User> allUsers = userRepository.findAll();
        List<UserDetails> usersDetails = new ArrayList<>();
        for(User user: allUsers){
            UserDetails users = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
            usersDetails.add(users);
        }
        return usersDetails;
    }
    public List<UserDto> findAllUsersDto(){
        List<User> allUsers = userRepository.findAll();
        return UserMapper.USER_MAPPER.fromListUsers(allUsers);
    }

    public Long findIdByName(String name){
        return userRepository.findIdByName(name);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found",username)));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }
}
