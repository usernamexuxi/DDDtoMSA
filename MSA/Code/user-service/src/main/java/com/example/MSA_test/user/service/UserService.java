package com.example.MSA_test.user.service;
import com.example.MSA_test.common.dto.UserDto;
import com.example.MSA_test.common.exception.BusinessException;
import com.example.MSA_test.common.util.BeanCopyUtils;
import com.example.MSA_test.user.entity.UserEntity;
import com.example.MSA_test.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional @RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public UserDto create(UserDto dto){
        if(repo.existsByEmail(dto.getEmail()))
            throw new BusinessException("Email duplicated");
        UserEntity saved = repo.save(BeanCopyUtils.toEntity(dto, UserEntity.class));
        return BeanCopyUtils.toDto(saved, UserDto.class);
    }

    public List<UserDto> list(){
        return repo.findAll().stream()
                .map(e -> BeanCopyUtils.toDto(e, UserDto.class))
                .toList();
    }
}