package com.mockproject.du1.mapper;

import com.mockproject.du1.model.EmailTemplate;
import com.mockproject.du1.model.EmailTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailTemplateMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	long countByExample(EmailTemplateExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int deleteByExample(EmailTemplateExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int insert(EmailTemplate record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int insertSelective(EmailTemplate record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	List<EmailTemplate> selectByExample(EmailTemplateExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	EmailTemplate selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int updateByExampleSelective(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int updateByExample(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int updateByPrimaryKeySelective(EmailTemplate record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.email_template
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	int updateByPrimaryKey(EmailTemplate record);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.email_template
     *
     * @mbg.generated Mon Nov 04 21:33:16 ICT 2019
     */
    //long countByExample(EmailTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.email_template
     *
     * @mbg.generated Mon Nov 04 21:33:16 ICT 2019
     */
    //int deleteByExample(EmailTemplateExample example);

    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.email_template
     *
     * @mbg.generated Mon Nov 04 21:33:16 ICT 2019
     */
    //List<EmailTemplate> selectByExample(EmailTemplateExample example);

    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.email_template
     *
     * @mbg.generated Mon Nov 04 21:33:16 ICT 2019
     */
    //int updateByExampleSelective(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.email_template
     *
     * @mbg.generated Mon Nov 04 21:33:16 ICT 2019
     */
    //int updateByExample(@Param("record") EmailTemplate record, @Param("example") EmailTemplateExample example);

    
}