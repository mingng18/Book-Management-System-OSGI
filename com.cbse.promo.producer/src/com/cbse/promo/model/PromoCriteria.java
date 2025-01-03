package com.cbse.promo.model;

public class PromoCriteria {
	private int id;
	private String description;
	private String conditionType;
	private String conditionValue;

	public PromoCriteria(int id, String description, String conditionType, String conditionValue) {
		this.id = id;
		this.description = description;
		this.conditionType = conditionType;
		this.conditionValue = conditionValue;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	@Override
	public String toString() {
		return "PromoCriteria{" + "id=" + id + ", description='" + description + '\'' + ", conditionType='"
				+ conditionType + '\'' + ", conditionValue='" + conditionValue + '\'' + '}';
	}

}
