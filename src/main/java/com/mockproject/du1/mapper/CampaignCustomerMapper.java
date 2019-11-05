package com.mockproject.du1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.CampaignCustomer;
import com.mockproject.du1.model.CampaignCustomerExample;

public interface CampaignCustomerMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	long countByExample(CampaignCustomerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int deleteByExample(CampaignCustomerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int insert(CampaignCustomer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int insertSelective(CampaignCustomer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	List<CampaignCustomer> selectByExample(CampaignCustomerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	CampaignCustomer selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByExampleSelective(@Param("record") CampaignCustomer record,
			@Param("example") CampaignCustomerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByExample(@Param("record") CampaignCustomer record, @Param("example") CampaignCustomerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByPrimaryKeySelective(CampaignCustomer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign_customer
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByPrimaryKey(CampaignCustomer record);

	List<CampaignCustomer> sqlGetAllMailHistorySelect();

    CampaignCustomer sqlGetMailHistorySelectByEmail(String email);

    int sqlInsertMailHistoryl(CampaignCustomer mailHistory);
}
