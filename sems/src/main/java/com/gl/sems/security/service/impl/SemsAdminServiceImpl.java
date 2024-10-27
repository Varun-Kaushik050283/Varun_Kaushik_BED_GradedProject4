/**
 *
 */
package com.gl.sems.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.sems.domain.ResponseDto;
import com.gl.sems.domain.RoleDto;
import com.gl.sems.domain.UserDto;
import com.gl.sems.security.entity.Role;
import com.gl.sems.security.entity.User;
import com.gl.sems.security.entity.UsersRoles;
import com.gl.sems.security.repository.RoleRepository;
import com.gl.sems.security.repository.UserRepository;
import com.gl.sems.security.repository.UsersRolesRepository;
import com.gl.sems.security.service.SemsAdminService;

/**
 * Sems Role Service Implementation class
 *
 * @author Varun Kaushik
 * @apiNote Employee Management System(SEMS) API
 */
@Service
public class SemsAdminServiceImpl implements SemsAdminService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UsersRolesRepository usersRolesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseDto addRole(Role role) {
		Role existingRole = roleRepository.getRoleByName(role.getName());
		boolean roleAlreadyExists = (null != existingRole);

		if (!roleAlreadyExists) {
			Role roleSaved = roleRepository.save(role);
			if (null != roleSaved) {
				return new ResponseDto(roleSaved.getName()
						+ " role has been added successfully in the SEMS system with the entered name.");
			} else {
				return new ResponseDto(" Due to some reason " + role.getName()
						+ " has not been added in the SEMS system with the entered name.");
			}
		} else {
			return new ResponseDto("Role already exists with the entered name, please try with another name.");
		}
	}

	@Override
	public ResponseDto addUser(UserDto userDto) {

		User existingUser = userRepository.getUserByUsername(userDto.getUsername());
		boolean userAlreadyExists = (null != existingUser);

		if (!userAlreadyExists) {

			// encrypt password by BCryptPasswordEncoder
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

			// convert user DTO to user entity
			User user = new User();
			BeanUtils.copyProperties(userDto, user);

			// Save user record.
			User userSaved = userRepository.save(user);

			// Save all assigned roles records
			List<Role> savedRoles = new ArrayList<>();
			if (null != userSaved) {

				for (RoleDto roleDto : userDto.getRoles()) {

					Role existingRole = roleRepository.getRoleByName(roleDto.getName());
					Role roleSaved = null;

					if (null == existingRole) {

						// convert role DTO to role entity
						Role role = new Role();
						BeanUtils.copyProperties(roleDto, role);

						// Save role record
						roleSaved = roleRepository.save(role);

					} else {
						roleSaved = existingRole;
					}

					if (null != roleSaved) {

						// Save users_roles bridge relation record
						UsersRoles usersRolesBridge = new UsersRoles(userSaved.getId(), roleSaved.getId());
						UsersRoles usersRolesBridgeRelationSaved = usersRolesRepository.save(usersRolesBridge);

						if (null != usersRolesBridgeRelationSaved) {
							savedRoles.add(roleSaved);
						}
					}
				}
			}

			if (savedRoles.size() == userDto.getRoles().size()) {
				return new ResponseDto(
						user.getUsername() + " added successfully in the SEMS system with the entered username.");
			} else {
				return new ResponseDto(" Due to some reason " + user.getUsername()
						+ " not added in the SEMS system with the entered username.");
			}

		} else {
			return new ResponseDto("User already exists with the entered username, please try with another username.");
		}
	}
}
