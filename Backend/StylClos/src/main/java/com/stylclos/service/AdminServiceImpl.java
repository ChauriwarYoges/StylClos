package com.stylclos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stylclos.dao.AdminRepository;
import com.stylclos.dao.CategoryRepository;
import com.stylclos.dao.TypeRepository;
import com.stylclos.dao.UserRepository;
import com.stylclos.dto.TypeResponseDTO;
import com.stylclos.pojos.Category;
import com.stylclos.pojos.Type;
import com.stylclos.pojos.User;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Value("${image.upload.location}")
	private String location;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private UserRepository usrRepo;

	@Autowired
	private TypeRepository typeRepo;
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public int countUserByRole(String role) {
		// FILTER USER BASED ON ROLES
		long count = usrRepo.countByRoleContaining(role);
		System.out.println("count : " + count);
		return (int)count;
	}

	@Override
	public List<TypeResponseDTO> getAllTypes() {
		// Retrieve all types for admin
		List<Type> types = typeRepo.getAllTypesinDB();

		if (types.isEmpty())
			return null;

		List<TypeResponseDTO> trds = new ArrayList<>();

		types.forEach(t -> {
			
			TypeResponseDTO trd = new TypeResponseDTO();
			trd.setId(t.getId());
			trd.setImage(t.getImage());
			trd.setCategoryId(t.getCat().getId());
			trd.setTypeName(t.getTypeName());

			// STORE IMAGE
			Path path = Paths.get(location, t.getImage());
			trd.setName(t.getImage());
			try {
				trd.setImgData(Files.readAllBytes(path));
				trd.setImgType(Files.probeContentType(path));
			} catch (IOException e) {
				
				System.out.println("error while reading image of type id " + t.getId() + "\n" + e);
			}
			
			trds.add(trd);
		});

		return trds;
	}

	@Override
	public int getCountOfCategory() {
		// COUNT CATOGORIES IN DB
		List<Category> cats = catRepo.findAll();
		
		return cats.size();
	}

	@Override
	public int getCountOfTypes() {
		// COUNT TYPES IN DB
		List<Type> types = typeRepo.findAll();
		return types.size();
	}

}
