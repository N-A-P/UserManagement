package com.mockproject.du1.services;

import com.mockproject.du1.mapper.PagesMapper;
import com.mockproject.du1.mapper.RolePagesMapper;
import com.mockproject.du1.model.Pages;
import com.mockproject.du1.model.RoleForPages;
import com.mockproject.du1.model.RolePages;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePagesService {

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
}
