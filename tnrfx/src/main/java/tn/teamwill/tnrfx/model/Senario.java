package tn.teamwill.tnrfx.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Senario {
	private StringProperty nameFX;
	private StringProperty contentName;
	private BooleanProperty idUsed = new SimpleBooleanProperty();

	private Long id;
	private String uuid;
	private String content;
	private String name;
	private String type;

	public Senario() {
	}

	public Senario(String name, String contentName) {
		this.nameFX = new SimpleStringProperty(name);
		this.contentName = new SimpleStringProperty(contentName);
	}

	public Senario(String name, String contentName, Boolean isUsed) {
		this.nameFX = new SimpleStringProperty(name);
		this.contentName = new SimpleStringProperty(contentName);
		this.idUsed = new SimpleBooleanProperty(isUsed);
	}

	public Senario(StringProperty name2, String string) {
		this.nameFX = name2;
	}

	public StringProperty getNameFX() {
		return nameFX;
	}

	public StringProperty getContentName() {
		return contentName;
	}

	@Override
	public String toString() {
		return "Senario [nameFX=" + nameFX + ", contentName=" + contentName + "]";
	}

	public BooleanProperty getIdUsed() {
		return idUsed;
	}

	public void setIdUsed(BooleanProperty idUsed) {
		this.idUsed = idUsed;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNameFX(StringProperty nameFX) {
		this.nameFX = nameFX;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
