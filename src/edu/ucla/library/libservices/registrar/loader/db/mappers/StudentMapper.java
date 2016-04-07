package edu.ucla.library.libservices.registrar.loader.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucla.library.libservices.registrar.loader.beans.StudentBean;

public class StudentMapper
  implements RowMapper
{
  public StudentMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    StudentBean bean;
    bean = new StudentBean();
    
    bean.setBar_asmt_cd(rs.getString("BAR_ASMT_CD"));
    bean.setBirthdt(rs.getString("BIRTHDT"));
    bean.setCareer(rs.getString("CAREER"));
    bean.setCit_cntry_cd(rs.getString("CIT_CNTRY_CD"));
    bean.setDeg_exp_term(rs.getString("DEG_EXP_TERM"));
    bean.setDoh(rs.getString("DOH"));
    bean.setEap(rs.getString("EAP"));
    bean.setEap_cur(rs.getString("EAP_CUR"));
    bean.setEmail_privacy_flag(rs.getString("EMAIL_PRIVACY_FLAG"));
    bean.setEmail_return_flag(rs.getString("EMAIL_RETURN_FLAG"));
    bean.setEmail_upd_dte(rs.getString("EMAIL_UPD_DTE"));
    bean.setFerpa(rs.getString("FERPA"));
    bean.setGrad_cd(rs.getString("GRAD_CD"));
    bean.setHold(rs.getString("HOLD"));
    bean.setHonors(rs.getString("HONORS"));
    bean.setM_addr_cd(rs.getString("M_ADDR_CD"));
    bean.setM_adr_priv_flag(rs.getString("M_ADR_PRIV_FLAG"));
    bean.setM_city_name(rs.getString("M_CITY_NAME"));
    bean.setM_cntry7(rs.getString("M_CNTRY7"));
    bean.setM_pho_priv_flag(rs.getString("M_PHO_PRIV_FLAG"));
    bean.setM_phone_no(rs.getString("M_PHONE_NO"));
    bean.setM_prov_cd(rs.getString("M_PROV_CD"));
    bean.setM_state_cd(rs.getString("M_STATE_CD"));
    bean.setM_street_1(rs.getString("M_STREET_1"));
    bean.setM_street_2(rs.getString("M_STREET_2"));
    bean.setM_zip_cd(rs.getString("M_ZIP_CD"));
    bean.setOther_maj_min(rs.getString("OTHER_MAJ_MIN"));
    bean.setP_addr_cd(rs.getString("P_ADDR_CD"));
    bean.setP_adr_priv_flag(rs.getString("P_ADR_PRIV_FLAG"));
    bean.setP_city_name(rs.getString("P_CITY_NAME"));
    bean.setP_cntry7(rs.getString("P_CNTRY7"));
    bean.setP_pho_priv_flag(rs.getString("P_PHO_PRIV_FLAG"));
    bean.setP_phone_no(rs.getString("P_PHONE_NO"));
    bean.setP_prov_cd(rs.getString("P_PROV_CD"));
    bean.setP_state_cd(rs.getString("P_STATE_CD"));
    bean.setP_street_1(rs.getString("P_STREET_1"));
    bean.setP_street_2(rs.getString("P_STREET_2"));
    bean.setP_zip_cd(rs.getString("P_ZIP_CD"));
    bean.setPay_stat(rs.getString("PAY_STAT"));
    bean.setPerm_dis_blnk(rs.getString("PERM_DIS_BLNK"));
    bean.setReg_status(rs.getString("REG_STATUS"));
    bean.setReg_typ_cd(rs.getString("REG_TYP_CD"));
    bean.setSex(rs.getString("SEX"));
    bean.setSp_pgm1(rs.getString("SP_PGM1"));
    bean.setSp_pgm2(rs.getString("SP_PGM2"));
    bean.setSs_email_addr(rs.getString("SS_EMAIL_ADDR"));
    bean.setSsn_mask(rs.getString("SSN_MASK"));
    bean.setStu_class(rs.getString("CLASS"));
    bean.setStu_id(rs.getString("STU_ID"));
    bean.setStu_id2(rs.getString("STU_ID"));
    bean.setStu_nm(rs.getString("STU_NM"));
    bean.setTemp_dis_blnk(rs.getString("TEMP_DIS_BLNK"));
    bean.setTerm_cd(rs.getString("TERM_CD"));
    bean.setVisa_trm(rs.getString("VISA_TRM"));
    bean.setWith_cd(rs.getString("WITH_CD"));
    bean.setWith_dt(rs.getString("WITH_DT"));
    bean.setColl_cd(rs.getString("COLL_CD"));
    bean.setDeg_cd(rs.getString("DEG_CD"));
    bean.setMajor_cd(rs.getString("MAJOR_CD"));
    bean.setMajor_abbr(rs.getString("MAJOR_ABBR"));
    bean.setSr_dept_cd(rs.getString("SR_DEPT_CD"));
    bean.setSr_div_cd(rs.getString("SR_DIV_CD"));
    
    return bean;
  }
}
