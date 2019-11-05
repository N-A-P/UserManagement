package com.mockproject.du1.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mockproject.du1.model.Campaign;
import com.mockproject.du1.model.CampaignExample;
import com.mockproject.du1.model.Customer;

public interface CampaignMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	long countByExample(CampaignExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int deleteByExample(CampaignExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int insert(Campaign record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int insertSelective(Campaign record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	List<Campaign> selectByExample(CampaignExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	Campaign selectByPrimaryKey(Long id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByExampleSelective(@Param("record") Campaign record, @Param("example") CampaignExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByExample(@Param("record") Campaign record, @Param("example") CampaignExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByPrimaryKeySelective(Campaign record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.campaign
	 * @mbg.generated  Mon Nov 04 21:27:54 ICT 2019
	 */
	int updateByPrimaryKey(Campaign record);
	int sqlCreateCampaignInsert(Campaign campaign);
    List<Campaign> sqlGetAllCampain();
    int sqlCampaignInfoUpdate(Campaign campaign);
    Campaign sqlGetCampainById(Campaign campaign);

}
