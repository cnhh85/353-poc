package com.sedex.spectrum.appsvc.user.management.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "USER_PREFERENCES")
public class UserPreferences implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Integer preferenceId;

    @Size(min = 0, max = 50)
    @Column(name = "user_code")
    private String userCode;

    @Column(name = "preference_field_id")
    private Integer preferenceFieldId;

    @Column(name = "user_choice")
    private Boolean userChoice;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime lastModifiedOn;

    @Column(name = "notification_type_id")
    private Integer notificationTypeId;

    public UserPreferences() {
    }

    public UserPreferences(String userCode, Integer notificationTypeId, Integer preferenceFieldId, Boolean userChoice, String lastModifiedBy) {
        this.userCode = userCode;
        this.preferenceFieldId = preferenceFieldId;
        this.userChoice = userChoice;
        this.lastModifiedBy = lastModifiedBy;
        this.notificationTypeId = notificationTypeId;
    }

    public Integer getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Integer preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getPreferenceFieldId() {
        return preferenceFieldId;
    }

    public void setPreferenceFieldId(Integer preferenceFieldId) {
        this.preferenceFieldId = preferenceFieldId;
    }

    public Integer getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Integer notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Boolean getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Boolean userChoice) {
        this.userChoice = userChoice;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    @PrePersist
    public void setLastModifiedOn() {
        this.lastModifiedOn = LocalDateTime.now();
    }

	@Override
	public String toString() {
		return "UserPreferences [preferenceId=" + preferenceId + ", userCode=" + userCode + ", preferenceFieldId="
				+ preferenceFieldId + ", userChoice=" + userChoice + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedOn=" + lastModifiedOn + ", notificationTypeId=" + notificationTypeId + "]";
	}
    
}
