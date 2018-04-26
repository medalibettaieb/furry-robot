package tn.teamwill.tnrfx.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class UiTestDetails implements Serializable {
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

	private static final long serialVersionUID = 1L;

	public UiTestDetails() {
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
