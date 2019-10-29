<<<<<<< HEAD
package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
public class UserRole {
	/**
	 * User Role Id
	 */
    private int userRoleId;
    /**
	 * User Id
	 */
    private int userId;
    /**
	 * Role Id
	 */
    private int roleId;
    /**
	 * Join Date
	 */
    private String joinDate;
    /**
	 * Leave Date
	 */
    private String leaveDate;
	/**
	 * Update By
	 */
	private String updateBy;
	/**
	 * Create Timestamp
	 */
	private String createTimestamp;
	/**
	 * Update Timestamp
	 */
	private String updateTimestamp;

}
=======
package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class UserRole {
	/**
	 * User Role Id
	 */
    private int userRoleId;
    /**
	 * User Id
	 */
    private int userId;
    /**
	 * Role Id
	 */
    private int roleId;
    /**
	 * Join Date
	 */
    private String joinDate;
    /**
	 * Leave Date
	 */
    private String leaveDate;
    private String updateBy;
    private String createTimestamp;
    private String updateTimestamp;

}
>>>>>>> refs/remotes/origin/huong_to_merge_later
