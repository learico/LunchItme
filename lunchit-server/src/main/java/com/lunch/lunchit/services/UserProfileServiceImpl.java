package com.lunch.lunchit.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunch.lunchit.dtos.UserProfileDto;
import com.lunch.lunchit.entities.UserProfile;
import com.lunch.lunchit.repo.UserProfileRepository;



@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	private ModelMapper mapper;
	
	private final UserProfileRepository repo;
		

		protected UserProfileServiceImpl( UserProfileRepository repo, ModelMapper mapper) {
			this.repo = repo;
			this.mapper = mapper;
			
		}
		
		
		@Override
		public void create(@Valid UserProfileDto dto) {
			UserProfile user = mapper.map(dto, UserProfile.class);
			repo.save(user);
		}

		@Override
		public void update(Long id, UserProfileDto dto) {
		UserProfile user = repo.findById(dto.getId()).get();
		mapper.map(dto, user);
		repo.save(user);
			
		}

		@Override
		public void delete(Long id) {
			repo.deleteById(id);
		}

		@Override
		public UserProfile getOne(Long id) {
			return repo.getById(id);
		}
		

	    @Override
	    public List<UserProfileDto> findAllByEmail(String inputs) {
		List<UserProfile> entities = repo.findAllByEmail(inputs);
		List<UserProfileDto> dtos = new ArrayList<>();
		for (UserProfile user : entities) {
		    UserProfileDto dto = mapper.map(user, UserProfileDto.class);
		    dtos.add(dto);
		}
		return dtos;
	    }


}