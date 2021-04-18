package ar.com.ada.second.library.service;

import ar.com.ada.second.library.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.library.model.dto.AuthorDTO;
import ar.com.ada.second.library.model.dto.UserDTO;
import ar.com.ada.second.library.model.entity.Author;
import ar.com.ada.second.library.model.entity.User;
import ar.com.ada.second.library.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.library.model.mapper.UserMapper;
import ar.com.ada.second.library.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Services<UserDTO, User>{

    private UserMapper userMapper = UserMapper.MAPPER;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createNew(UserDTO dto) {

        User user = userMapper.toEntity(dto, context);

        userRepository.save(user);

        UserDTO userSaved = userMapper.toDTO(user,context);

        return userSaved;
    }

    @Override
    public List<UserDTO> getAll() {

        List<User> userList = userRepository.findAll();

        List<UserDTO> userDTOS = userMapper.toDTO(userList, context);

        return userDTOS;
    }

    @Override
    public UserDTO getById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Author", id));

        UserDTO userDTO = userMapper.toDTO(user, context);

        return userDTO;

    }

    @Override
    public UserDTO update(UserDTO dto, Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        User userById = userOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Author", id));

        mergeData(userById, dto);

        userRepository.save(userById);

        UserDTO userUpdated = userMapper.toDTO(userById,context);

        return userUpdated;
    }

    @Override
    public void remove(Long id) {

        Optional<User> userByIdToDelete = userRepository.findById(id);

        User user = userByIdToDelete
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Author", id));

        userRepository.deleteById(id);
    }

    @Override
    public void mergeData(User entity, UserDTO dto) {

        if (dto.hasNullOrEmptyAttributes())
            throw logicExceptionComponent.getExceptionEntityEmptyValues("Author");

        if (!entity.getName().equals(dto.getName()))
            entity.setName(dto.getName());

        if (!entity.getLastName().equals(dto.getLastName()))
            entity.setLastName(dto.getLastName());

        if (!entity.getBirthday().equals(dto.getBirthday()))
            entity.setBirthday(dto.getBirthday());

        if (!entity.getAddress().equals(dto.getAddress()))
            entity.setAddress(dto.getAddress());

        if (!entity.getEducationLevel().equals(dto.getEducationLevel()))
            entity.setEducationLevel(dto.getEducationLevel());

        if (!entity.getIsStudying().equals(dto.getIsStudying()))
            entity.setIsStudying(dto.getIsStudying());

        if (!entity.getIsWorking().equals(dto.getIsWorking()))
            entity.setIsStudying(dto.getIsWorking());



    }
}
