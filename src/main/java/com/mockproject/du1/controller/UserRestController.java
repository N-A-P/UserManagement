package com.mockproject.du1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.mapper.RoleMapper;
import com.mockproject.du1.mapper.UsersMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.services.DepartmentService;
import com.mockproject.du1.services.EmailService;
import com.mockproject.du1.services.JwtService;
import com.mockproject.du1.services.UsersService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/rest")
public class UserRestController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	EmailService emailService;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UsersMapper usersMapper;

	/* ---------------- GET ALL USER ------------------------ */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> getAllUser() {
		return new ResponseEntity<List<Users>>(usersService.getAllUser(), HttpStatus.OK);
	}

//
//    /* ---------------- GET USER BY ID ------------------------ */
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Object> getUserById(@PathVariable int id) {
//        User user = userService.findById(id);
//        if (user != null) {
//            return new ResponseEntity<Object>(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
//    }
//
	/* ---------------- REGISTRATION ------------------------ */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<String> registerNewCustomer() {
//	(@RequestBody Users user) {
//        if (usersService.registerNewCustomer(user)) {
//            return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<String>("Username or Email Existed!", HttpStatus.BAD_REQUEST);
//        }
		if (usersService.registerNewCustomer(new Users("first", "last", "email1", "username1", "password", "10-8-1992",
				"10-8-1992", "10-8-1992", 0, 0))) {
			return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Username or Email Existed!", HttpStatus.BAD_REQUEST);
		}
	}

//
//    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
//    public ResponseEntity<String> createRole(@RequestBody User user, @RequestParam("role") int role) {
//        userrolerepo.save(Userrole.builder().userid(user.getId()).roleid(role).count(0).build());
//        return new ResponseEntity<String>("createRole", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
//        userService.delete(id);
//        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/usersfull", method = RequestMethod.GET)
//    public ResponseEntity<List<Userfull>> findalluserandrole() {
//        List<Userfull> users = new ArrayList<>();
//        for (User user : userService.findAll()) {
//            users.add(Userfull.builder().user(user).Role(rolerepo.getRole(user.getUsername())).build());
//        }
//        ;
//
//        return new ResponseEntity<List<Userfull>>(users, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/usersfull", method = RequestMethod.POST)
//    public ResponseEntity<String> updateRole(@RequestBody Userfull userfull) {
//        int id = rolerepo.findByRolename(userfull.getRole()).getRoleid();
//        Optional<Userrole> userrole = userrolerepo.findById((long) id);
//        rolerepo.setRole(userfull.getUser().getId());
//        if (userrole.isPresent()) {
//            userrole.get().setStatus(1);
//        } else
//            userrolerepo.save(Userrole.builder().userroleid(userfull.getUser().getId() + id).count(0).status(1).roleid(id).userid(userfull.getUser().getId()).build());
//
//        return new ResponseEntity<String>("ok", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/email", method = RequestMethod.POST)
//    public ResponseEntity<String> sendmail(@RequestBody List<User> user) {
//        emailService.sendEmailToAll((List<User>) user, "");
//        return new ResponseEntity<String>("success", HttpStatus.OK);
//    }
	/**
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Users user) {
		String result = "";
		HttpStatus httpStatus = null;
		try {
			if (usersService.checkLogin(user)) {
				result = jwtService.generateTokenLogin(user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				result = "Wrong username or password";
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, httpStatus);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getAllListDepartment", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getAllListDepartment() {
		return new ResponseEntity<List<Department>>(departmentService.getAllListDepartment(), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getListDepartmentActive", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getListDepartmentActive() {
		return new ResponseEntity<List<Department>>(departmentService.getListDepartmentActive(), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getDepartmentById/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<Department> getDepartmentById(@PathVariable int departmentId) {
		return new ResponseEntity<Department>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getListEmployeeOfDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeOfDepartment(@PathVariable int departmentId) {
		return new ResponseEntity<List<EmployeeOfDepartment>>(
				departmentService.getListEmployeeOfDepartment(departmentId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getListEmployeeNotInDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeNotInDepartment(@PathVariable int departmentId) {
		return new ResponseEntity<List<EmployeeOfDepartment>>(
				departmentService.getListEmployeeNotInDepartment(departmentId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/updateDepartmentInfomation", method = RequestMethod.POST)
	public ResponseEntity<String> updateDepartmentInfomation(@RequestBody Department department) {
		if (departmentService.departmentInfoUpdate(department) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/activeDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> activeDepartment(@RequestBody Department department) {
		if (departmentService.activeDepartment(department.getDepartmentId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/inActiveDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> inActiveDepartment(@RequestBody Department department) {
		if (departmentService.inActiveDepartment(department.getDepartmentId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/insertDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> insertDepartment(@RequestBody Department department) {
		if (departmentService.departmentInsert(department) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/addNewEmployeeToDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> addNewEmployeeToDepartment(@RequestBody EmployeeOfDepartment employeeOfDepartment) {
		if (departmentService.newEmployeeForDeparmentInsert(employeeOfDepartment) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/removeEmployeeFromDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> removeEmployeeFromDepartment(@RequestBody EmployeeOfDepartment employeeOfDepartment) {
		if (departmentService.removeEmployeeFromDepartment(employeeOfDepartment.getDepartmentDetailId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

}