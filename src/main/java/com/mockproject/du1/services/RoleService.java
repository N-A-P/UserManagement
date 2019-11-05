package com.mockproject.du1.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.mockproject.du1.mapper.RoleMapper;
import com.mockproject.du1.model.Role;

@Service
public class RoleService {

	HttpServletRequest request = null;
	String usernameLogin;
	/**
	 * Get current timestamp
	 */
	String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	/**
	 * 
	 */
	private static final int CONSTANT_CHECK_DUPLICATED_CODE = -2;
	/**
	 * 
	 */
	private static final int CONSTANT_CHECK_DUPLICATED_NAME = -1;
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

	/**
	 * 
	 */
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 
	 */
	public List<Role> getAllListRole() {
		try {
			return roleMapper.sqlGetAllRoleSelect();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;

	}

	/**
	 * 
	 */
	public Role getRoleById(int roleId) {
		try {
			return roleMapper.sqlGetRoleByIdSelect(roleId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public List<Role> getRoleByNameOrCodeSelect(String extractCondition) {
		try {
			return roleMapper.sqlGetRoleByNameOrCodeSelect(extractCondition);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/** 
	 * 
	 */
	public int editRoleInfoUpdate(Role role) throws SQLException {

		try {
			HttpSession session = request.getSession(false);
			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			// Check duplicate Role Name
			if (roleMapper.sqlCountRoleByNameSelect(role.getName()) == 0) {
				// Check duplicate Role Code
				if (roleMapper.sqlCountRoleByCodeSelect(role.getCode()) == 0) {

					// set value for field public.role.updated_by
					role.setUpdatedBy(usernameLogin);
					// set value for field public.role.created_timestamp
					role.setCreatedTimestamp(currentTimestamp);
					// set value for field public.role.updated_timestamp
					role.setUpdatedTimestamp(currentTimestamp);

					// return result update database query
					return roleMapper.sqlEditRoleInfoUpdate(role);

				} else {
					return CONSTANT_CHECK_DUPLICATED_CODE;
				}
			} else {
				return CONSTANT_CHECK_DUPLICATED_NAME;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 
	 */
	public int addNewRole(Role role) throws DuplicateKeyException, SQLException {

		try {
			// Check duplicate Role Name
			if (roleMapper.sqlCountRoleByNameSelect(role.getName()) == 0) {
				// Check duplicate Role Code
				if (roleMapper.sqlCountRoleByCodeSelect(role.getCode()) == 0) {

					// set value for field public.role.updated_by
					role.setUpdatedBy(usernameLogin);
					// set value for field public.role.created_timestamp
					role.setCreatedTimestamp(currentTimestamp);
					// set value for field public.role.updated_timestamp
					role.setUpdatedTimestamp(currentTimestamp);

					// return result insert database query
					return roleMapper.sqlRoleInsert(role);

				} else {
					return CONSTANT_CHECK_DUPLICATED_CODE;
				}
			} else {
				return CONSTANT_CHECK_DUPLICATED_NAME;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
}
