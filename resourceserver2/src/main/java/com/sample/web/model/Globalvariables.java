package com.sample.web.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Globalvariables implements Serializable {

	private Long personInfoID;

	private String personCode;

	private String personName;

	private Long businessGroup;

	private Long plEntityHierID;

	private Long slEntityHierID;

	private Long companyEntityHierID;

	private Long branchEntityHierID;

	private Long locationEntityHierID;

	private Long finYearID;

	private ZonedDateTime finYearStartDate;

	private ZonedDateTime finYearEndDate;

	private String finYearStartDateStr;

	private String finYearEndDateStr;

	private Long calenderYearID;

	private ZonedDateTime lastTransConfigDate;
	
	private String lastTransConfigDateStr;

	private String transDateStr;

	private ZonedDateTime transDateTime;
	
	private ZonedDateTime transDate;
	
	private String currentDateStr;

	private ZonedDateTime currentDateTime;
	
	private ZonedDateTime currentDate;

	private Long role;

//	private Preference preference;

	private Character userSlotType;

	private Character entitySlotType;

	private Long languageID;

	private Long machineID;

	private Long loginLogID;

	private Boolean viewNotValidatedEntities;
	
	private List<Long> userRoles;
	
	private Long hostTypeID;
		
	private Long loginLevelID;
		
	private String counterCode;
	
	public String getTransDateStr() {
		return transDateStr;
	}

	public void setTransDateStr(String transDateStr) {
		this.transDateStr = transDateStr;
	}

	public Long getPersonInfoID() {
		return personInfoID;
	}

	public void setPersonInfoID(Long personInfoID) {
		this.personInfoID = personInfoID;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Long getBusinessGroup() {
		return businessGroup;
	}

	public void setBusinessGroup(Long businessGroup) {
		this.businessGroup = businessGroup;
	}

	public Long getPlEntityHierID() {
		return plEntityHierID;
	}

	public void setPlEntityHierID(Long plEntityHierID) {
		this.plEntityHierID = plEntityHierID;
	}

	public Long getSlEntityHierID() {
		return slEntityHierID;
	}

	public void setSlEntityHierID(Long slEntityHierID) {
		this.slEntityHierID = slEntityHierID;
	}

	public Long getCompanyEntityHierID() {
		return companyEntityHierID;
	}

	public void setCompanyEntityHierID(Long companyEntityHierID) {
		this.companyEntityHierID = companyEntityHierID;
	}

	public Long getBranchEntityHierID() {
		return branchEntityHierID;
	}

	public void setBranchEntityHierID(Long branchEntityHierID) {
		this.branchEntityHierID = branchEntityHierID;
	}

	public Long getLocationEntityHierID() {
		return locationEntityHierID;
	}

	public void setLocationEntityHierID(Long locationEntityHierID) {
		this.locationEntityHierID = locationEntityHierID;
	}

	public Long getFinYearID() {
		return finYearID;
	}

	public void setFinYearID(Long finYearID) {
		this.finYearID = finYearID;
	}

	public Long getCalenderYearID() {
		return calenderYearID;
	}

	public void setCalenderYearID(Long calenderYearID) {
		this.calenderYearID = calenderYearID;
	}

	public ZonedDateTime getLastTransConfigDate() {
		return lastTransConfigDate;
	}

	public void setLastTransConfigDate(ZonedDateTime lastTransConfigDate) {
		this.lastTransConfigDate = lastTransConfigDate;
	}

	public ZonedDateTime getTransDateTime() {
		return transDateTime;
	}

	public void setTransDateTime(ZonedDateTime transDateTime) {
		this.transDateTime = transDateTime;
	}

	public ZonedDateTime getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(ZonedDateTime currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	/*public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}*/

	public Long getLanguageID() {
		return languageID;
	}

	public void setLanguageID(Long languageID) {
		this.languageID = languageID;
	}

	public Character getUserSlotType() {
		return userSlotType;
	}

	public void setUserSlotType(Character userSlotType) {
		this.userSlotType = userSlotType;
	}

	public Character getEntitySlotType() {
		return entitySlotType;
	}

	public void setEntitySlotType(Character entitySlotType) {
		this.entitySlotType = entitySlotType;
	}

	public Long getMachineID() {
		return machineID;
	}

	public void setMachineID(Long machineID) {
		this.machineID = machineID;
	}

	public Long getLoginLogID() {
		return loginLogID;
	}

	public void setLoginLogID(Long loginLogID) {
		this.loginLogID = loginLogID;
	}

	public Boolean getViewNotValidatedEntities() {
		return viewNotValidatedEntities;
	}

	public void setViewNotValidatedEntities(Boolean viewNotValidatedEntities) {
		this.viewNotValidatedEntities = viewNotValidatedEntities;
	}

	public ZonedDateTime getFinYearStartDate() {
		return finYearStartDate;
	}

	public void setFinYearStartDate(ZonedDateTime finYearStartDate) {
		this.finYearStartDate = finYearStartDate;
	}

	public ZonedDateTime getFinYearEndDate() {
		return finYearEndDate;
	}

	public void setFinYearEndDate(ZonedDateTime finYearEndDate) {
		this.finYearEndDate = finYearEndDate;
	}

	public String getFinYearStartDateStr() {
		return finYearStartDateStr;
	}

	public void setFinYearStartDateStr(String finYearStartDateStr) {
		this.finYearStartDateStr = finYearStartDateStr;
	}

	public String getFinYearEndDateStr() {
		return finYearEndDateStr;
	}

	public void setFinYearEndDateStr(String finYearEndDateStr) {
		this.finYearEndDateStr = finYearEndDateStr;
	}

	public ZonedDateTime getTransDate() {
		return transDate;
	}

	public void setTransDate(ZonedDateTime transDate) {
		this.transDate = transDate;
	}

	public ZonedDateTime getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(ZonedDateTime currentDate) {
		this.currentDate = currentDate;
	}

	public String getLastTransConfigDateStr() {
		return lastTransConfigDateStr;
	}

	public void setLastTransConfigDateStr(String lastTransConfigDateStr) {
		this.lastTransConfigDateStr = lastTransConfigDateStr;
	}

	public String getCurrentDateStr() {
		return currentDateStr;
	}

	public void setCurrentDateStr(String currentDateStr) {
		this.currentDateStr = currentDateStr;
	}

	public List<Long> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Long> userRoles) {
		this.userRoles = userRoles;
	}

	public Long getHostTypeID() {
		return hostTypeID;
	}

	public void setHostTypeID(Long hostTypeID) {
		this.hostTypeID = hostTypeID;
	}

	public Long getLoginLevelID() {
		return loginLevelID;
	}

	public void setLoginLevelID(Long loginLevelID) {
		this.loginLevelID = loginLevelID;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	
}
