package com.mockproject.du1.services;

import com.mockproject.du1.mapper.PagesMapper;
import com.mockproject.du1.mapper.RolePagesMapper;
import com.mockproject.du1.model.Pages;
import com.mockproject.du1.model.RoleForPages;
import com.mockproject.du1.model.RolePages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePagesService {

	HttpServletRequest request = null;
	String usernameLogin;
	String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

	/**
	 * fsdfd.
	 */
	@Autowired
	private PagesMapper pagesMapper;
	/**
	 * 
	 */
	@Autowired
	private RolePagesMapper rolePagesMapper;

	public List<RoleForPages> loadPagesSelected(int roleId) {

		List<RoleForPages> listSeleced = new ArrayList<RoleForPages>();
		RoleForPages roleForPages = new RoleForPages();

		List<Pages> listPages = pagesMapper.sqlGetAllPagesSelect();
		List<RolePages> listRolePages = rolePagesMapper.sqlGetRolePagesByRoleIdSelect(roleId);

		for (RolePages rolePages : listRolePages) {
			for (Pages pages : listPages) {
				if (pages.getPagesId() == rolePages.getPagesId()) {
					roleForPages.setSelectedFlg(1);
					roleForPages.setRolePagesId(rolePages.getRolePagesId());
					roleForPages.setRoleId(rolePages.getRoleId());
					roleForPages.setPagesId(pages.getPagesId());
					roleForPages.setPagesCode(pages.getPagesCode());
					roleForPages.setPagesName(pages.getPagesName());
					roleForPages.setPagesUrl(pages.getPagesUrl());
					listSeleced.add(roleForPages);
					break;
				}
			}
		}
		return listSeleced;
	}

	public int editRolePages(List<RoleForPages> roleForPages) {
		RolePages rolePages = new RolePages();

		for (RoleForPages items : roleForPages) {
			if (rolePagesMapper.sqlCheckRolePagesExistSelect(items.getRoleId(), items.getPagesId()) == 0
					&& items.getSelectedFlg() == 1) {

				rolePages.setRoleId(items.getRoleId());
				rolePages.setPagesId(items.getPagesId());
				rolePages.setUpdateBy(usernameLogin);
				rolePages.setCreateTimestamp(currentTimestamp);
				rolePages.setUpdateTimestamp(currentTimestamp);

				return rolePagesMapper.sqlRolePagesInsert(rolePages);
			} else if (rolePagesMapper.sqlCheckRolePagesExistSelect(items.getRoleId(), items.getPagesId()) == 1
					&& items.getSelectedFlg() == 0) {
				
				return rolePagesMapper.sqlRolePagesDelete(items.getRoleId(), items.getPagesId());
			}

		}

		return 0;
	}
}
