package tn.teamwill.tnrfx.model;

import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class UiTestDetails {
	private Long id;
	private String uuid;
	private Date startDate;
	private Date endDate;
	private Boolean result;
	private String type;
	private UITest uiTest;
	private String database;

	private StringProperty startDateOf;
	private StringProperty endDateOf;
	private StringProperty typeOf;
	private BooleanProperty resultOf;

	public UiTestDetails(UiTestDetailsEntity uiTestDetailsEntity) {
		super();
		this.id = uiTestDetailsEntity.getId();
		this.uuid = uiTestDetailsEntity.getUuid();
		this.startDate = uiTestDetailsEntity.getStartDate();
		this.endDate = uiTestDetailsEntity.getEndDate();
		this.result = uiTestDetailsEntity.getResult();
		this.type = uiTestDetailsEntity.getType();
		this.uiTest = uiTestDetailsEntity.getUiTest();
		this.database = uiTestDetailsEntity.getDatabase();
	}

	public UiTestDetails(Long id, String uuid, Date startDate, Date endDate, Boolean result, String type, UITest uiTest,
			String database) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.result = result;
		this.type = type;
		this.uiTest = uiTest;
		this.database = database;
	}

	public UiTestDetails() {
		super();
	}

	public UiTestDetails(Boolean result, String type) {
		super();
		this.result = result;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UITest getUiTest() {
		return uiTest;
	}

	public void setUiTest(UITest uiTest) {
		this.uiTest = uiTest;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public StringProperty getStartDateOf() {
		return startDateOf;
	}

	public void setStartDateOf(StringProperty startDateOf) {
		this.startDateOf = startDateOf;
	}

	public StringProperty getEndDateOf() {
		return endDateOf;
	}

	public void setEndDateOf(StringProperty endDateOf) {
		this.endDateOf = endDateOf;
	}

	public StringProperty getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(StringProperty typeOf) {
		this.typeOf = typeOf;
	}

	public BooleanProperty getResultOf() {
		return resultOf;
	}

	public void setResultOf(BooleanProperty resultOf) {
		this.resultOf = resultOf;
	}

}
