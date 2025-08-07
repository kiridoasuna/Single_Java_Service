package com.sunjj.usermicroservices.service;


import com.sunjj.usermicroservices.dto.UserDTO;
import com.sunjj.usermicroservices.pojo.User;
import com.sunjj.usermicroservices.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User queryUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ConvertToDTO(queryUser);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (this.userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email has already existed!");
        }

        User newUser = ConvertToEntity(userDTO);
        User savedUser = userRepository.save(newUser);

        return ConvertToDTO(savedUser);

    }


    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(existingUser);
        return ConvertToDTO(savedUser);
    }

    @Override
    public void deleteUser(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        this.userRepository.deleteById(id);
    }

    private User ConvertToEntity(UserDTO userDTO) {
        User user = new User();

        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    private UserDTO ConvertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setId(user.getId());

        return userDTO;
    }
}
