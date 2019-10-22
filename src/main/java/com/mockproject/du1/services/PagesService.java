package com.mockproject.du1.services;

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
import com.mockproject.du1.common.DataUtil;
import com.mockproject.du1.mapper.PagesMapper;
import com.mockproject.du1.model.Pages;

@Service
public class PagesService {

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
	private static final Logger logger = LoggerFactory.getLogger(PagesService.class);

	/**
	 * 
	 */
	@Autowired
	private PagesMapper pageMapper;

	/**
	 * 
	 */
	public List<Pages> getAllListPages() {
		try {
			return pageMapper.sqlGetAllPagesSelect();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;

	}

	/**
	 * 
	 */
	public Pages getPagesById(int pageId) {
		try {
			return pageMapper.sqlGetPagesByIdSelect(pageId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public List<Pages> getPagesByNameOrCodeSelect(String extractCondition) {
		try {
			return pageMapper.sqlGetPagesByNameOrCodeSelect(extractCondition);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/** 
	 * 
	 */
	public int editPagesInfoUpdate(Pages page) {

		try {
			HttpSession session = request.getSession(false);
			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			// Check duplicate Pages Name
			if (pageMapper.sqlCountPagesByNameSelect(page.getPagesName()) == 0) {
				// Check duplicate Pages Code
				if (pageMapper.sqlCountPagesByCodeSelect(page.getPagesCode()) == 0) {

					// set value for field public.page.updated_by
					page.setUpdateBy(usernameLogin);
					// set value for field public.page.created_timestamp
					page.setCreateTimestamp(DataUtil.getCurrentTimestamp().toString());
					// set value for field public.page.updated_timestamp
					page.setUpdateTimestamp(DataUtil.getCurrentTimestamp().toString());

					// return result update database query
					return pageMapper.sqlEditPagesInfoUpdate(page);

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
	public int addNewPages(Pages page) throws DuplicateKeyException {
		try {
			HttpSession session = request.getSession(false);
			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			// Check duplicate Pages Name
			if (pageMapper.sqlCountPagesByNameSelect(page.getPagesName()) == 0) {
				// Check duplicate Pages Code
				if (pageMapper.sqlCountPagesByCodeSelect(page.getPagesCode()) == 0) {

					// set value for field public.page.updated_by
					page.setUpdateBy(usernameLogin);
					// set value for field public.page.created_timestamp
					page.setCreateTimestamp(currentTimestamp);
					// set value for field public.page.updated_timestamp
					page.setUpdateTimestamp(currentTimestamp);

					// return result insert database query
					return pageMapper.sqlPagesInsert(page);

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
