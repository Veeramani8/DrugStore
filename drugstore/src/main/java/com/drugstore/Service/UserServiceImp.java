package com.drugstore.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.DistributorResDTO;
import com.drugstore.DTO.LoginRequestDTO;
import com.drugstore.DTO.LoginResponseDTO;
import com.drugstore.DTO.UserRequestDTO;
import com.drugstore.DTO.UserResponseDTO;
import com.drugstore.Model.Distributor;
import com.drugstore.Model.User;
import com.drugstore.Repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserRequestDTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return "User already present";
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());

        user.setIsactive(false);
        user.setEnabled(false); 
        userRepository.save(user);
        return "OTP sent to registered email";
    }


   
    @Override
    public void deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
            User user = userOpt.get();
            userRepository.delete(user);
        
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        

        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());
        response.setMessage("Login successful");

        return response;
    }


	@Override
	public UserResponseDTO updateUser(long id, UserRequestDTO dto) {
		User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setFullName(dto.getFullName());
        existing.setEmail(dto.getEmail());
        existing.setUsername(dto.getUsername());
        existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        existing.setRole(dto.getRole());
        

        User saved = userRepository.save(existing);
        return convertToDTO(saved);
		
	}


	@Override
	public User findByUsername(String username) {
	    return userRepository.findByUsername(username)
	                         .orElse(null);
	}
	  public boolean isEmailExists(String email) {
	        return userRepository.findByEmail(email).isPresent();
	    }

	    public void registerUser(User user) {
	        userRepository.save(user);
	    }

	    public Optional<User> getUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    public Optional<User> validateUserCredentials(String email, String password) {
	        Optional<User> userOpt = userRepository.findByEmail(email);
	        return userOpt.filter(user -> user.getPassword().equals(password));
	    }


		@Override
		public void saveRegisteredUser(User user) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setEnabled(true);
			user.setIsactive(true);
		    userRepository.save(user);
		}


   
}
