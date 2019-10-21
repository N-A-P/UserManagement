package com.mockproject.du1.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.du1.model.Pages;
import com.mockproject.du1.services.PagesService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/rest")
public class PagesRestController {
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
	@Autowired
	private PagesService pageService;

	/**
	 * Click button show list page
	 */
	@RequestMapping(value = "/getAllListPages", method = RequestMethod.GET)
	public ResponseEntity<List<Pages>> getAllListPages() {
		List<Pages> pages = pageService.getAllListPages();
		try {
			if (pages != null && !pages.isEmpty()) {
				return new ResponseEntity<List<Pages>>(pages, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 * Search Pages by Name or Code
	 */
	@RequestMapping(value = "/getPagesByNameOrCodeSelect", method = RequestMethod.GET)
	public ResponseEntity<List<Pages>> getPagesByNameOrCodeSelect(@Valid @PathVariable String extractCondition) {
		List<Pages> pages = pageService.getPagesByNameOrCodeSelect(extractCondition);
		try {
			if (pages != null && !pages.isEmpty()) {
				return new ResponseEntity<List<Pages>>(pages, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	@RequestMapping(value = "/getPagesById/{pageId}", method = RequestMethod.GET)
	public ResponseEntity<Pages> getPagesById(@PathVariable int pageId) {
		Pages page = pageService.getPagesById(pageId);
		try {
			if (page != null) {
				return new ResponseEntity<Pages>(page, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}

		return null;
	}

	@RequestMapping(value = "/updatePagesInfomation", method = RequestMethod.POST)
	public ResponseEntity<String> updatePagesInfomation(@Valid @RequestBody Pages page) {

		try {

			if (pageService.editPagesInfoUpdate(page) == CONSTANT_CHECK_DUPLICATED_CODE) {
				return new ResponseEntity<String>("Duplicated Pages code!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (pageService.editPagesInfoUpdate(page) == CONSTANT_CHECK_DUPLICATED_NAME) {
				return new ResponseEntity<String>("Duplicated Pages name!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (pageService.editPagesInfoUpdate(page) == 0) {
				return new ResponseEntity<String>("Database rollback!!! Modify Pages failed!!!", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
			}

		} catch (SQLException e) {
			return new ResponseEntity<String>("SQL Error Code: " + e.getErrorCode(), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 *
	 */
	@RequestMapping(value = "/insertPages", method = RequestMethod.POST)
	public ResponseEntity<String> insertPages(@Valid @RequestBody Pages page) {

		try {
			int checkInsertSuccess = pageService.addNewPages(page);

			if (checkInsertSuccess == CONSTANT_CHECK_DUPLICATED_CODE) {
				return new ResponseEntity<String>("Duplicated Pages code!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (checkInsertSuccess == CONSTANT_CHECK_DUPLICATED_NAME) {
				return new ResponseEntity<String>("Duplicated Pages name!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (checkInsertSuccess == 0) {
				return new ResponseEntity<String>("Database rollback!!! Adding new Pages failed!!!",
						HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
			}
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<String>("Duplicated Key!!! Database rollback!!! Adding new Pages failed!!!",
					HttpStatus.BAD_REQUEST);
		} catch (SQLException e) {
			return new ResponseEntity<String>("SQL Error Code: " + e.getErrorCode(), HttpStatus.BAD_REQUEST);
		}

	}

}
