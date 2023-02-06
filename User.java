package com.sedex.spectrum.appsvc.user.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.sedex.spectrum.appsvc.user.management.api.dto.UserLogDTO;
import com.sedex.spectrum.common.domain.user.management.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

@Entity
@Table(name = "T_USER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {

    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String STATUS = "status";
    public static final String ROLES = "roles";
    public static final String ORGANISATION_CODE = "organisationCode";

    @Size(min = 1)
    @Column(name = "organisation_code")
    private String organisationCode;

    @Id
    @Column(name = "user_code")
    private String userCode;

    @JsonIgnore
    @Size(min = 0, max = 100)
    private String password;

    @JsonIgnore
    @Size(min = 0, max = 100)
    @Column(name = "dummy_password")
    private String dummyPassword;

    @Size(min = 0, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 0, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Email
    @Size(min = 0, max = 100)
    private String email;

    @Column(name="impersonated_user_code")
    private String impersonatedUserCode;

    @Column(name = "number_attempts_login")
    private Integer numberFailedAttemptsLogin;

    @Column(name = "number_attempts_resend_registration_email")
    private Integer numberAttemptsResendRegistrationEmail;

    @Column(name = "number_attempts_resend_registration_email_by_sedex_admin")
    private Integer numberAttemptsResendRegistrationEmailBySedexAdmin;

    @Column(name = "locked_until")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lockedUntil;

    @Enumerated(EnumType.STRING)
    @Column(name = "sedex_status")
    private UserStatus memberUserStatus;

    @Column(name = "email_notification_language")
    private String emailNotificationLanguage;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "user_code", referencedColumnName = "user_code")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    @Fetch(FetchMode.JOIN)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Authority> authorities = newHashSet();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PersistentToken> persistentTokens = newHashSet();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<UserPasswordHistory> passwordHistories = newArrayList();

    @Column(name = "psw_modified_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime pswModifiedOn;

    @JsonIgnore
    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @Column(name = "\"language\"")
    @Size(min = 0, max = 100)
    private String language;

    @Column(name = "phone", updatable = false)
    private String phone;

    @Column(name = "fax", updatable = false)
    private String fax;

    @Column(name = "created_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime modifiedOn;

    @Column(name = "last_logged_in_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime lastLoggedInTime;

    @Column(name = "previous_logged_in_time ")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime previousLoggedInTime;

    @Size(min = 0, max = 100)
    @Column(name = "job_title")
    private String jobTitle;

    @Size(min = 0, max = 100)
    @Column(name = "department")
    private String department;

    @Size(min = 0, max = 100)
    @Column(name = "apsca_number")
    private String apscaNumber;

    @Size(min = 0, max = 100)
    @Column(name = "apsca_status")
    private String apscaStatus;

    @Column(name = "suspend_auditor")
    private Boolean suspendAuditor;

    @Column(name = "elearning_code")
    private String elearningCode;

    @Column(name = "created_by_connect")
    private Boolean createdByConnect;

    @Column(name = "is_mastered_by_connect")
    private Boolean isMasteredByConnect;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Optional<String> getOrganisationCode() {
        return Optional.ofNullable(organisationCode);
    }

    public void setOrganisationCode(String organisationCode) {
        this.organisationCode = organisationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<PersistentToken> getPersistentTokens() {
        return persistentTokens;
    }

    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {
        this.persistentTokens = persistentTokens;
    }

    public LocalDateTime getPswModifiedOn() {
        return pswModifiedOn;
    }

    public void setPswModifiedOn(LocalDateTime pswModifiedOn) {
        this.pswModifiedOn = pswModifiedOn;
    }

    public List<UserPasswordHistory> getPasswordHistories() {
        return passwordHistories;
    }

    public void setPasswordHistories(List<UserPasswordHistory> passwordHistories) {
        this.passwordHistories = passwordHistories;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getNumberFailedAttemptsLogin() {
        return numberFailedAttemptsLogin;
    }

    public void setNumberFailedAttemptsLogin(Integer numberFailedAttemptsLogin) {
        this.numberFailedAttemptsLogin = numberFailedAttemptsLogin;
    }

    public void setLockedUntil(DateTime lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

    public DateTime getLockedUntil() {

        return lockedUntil;
    }

    public UserStatus getMemberUserStatus() {
        return memberUserStatus;
    }

    public void setMemberUserStatus(UserStatus memberUserStatus) {
        this.memberUserStatus = memberUserStatus;
    }

    public String getImpersonatedUserCode() {
        return impersonatedUserCode;
    }

    public void setImpersonatedUserCode(String impersonatedUserCode) {
        this.impersonatedUserCode = impersonatedUserCode;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Boolean getCreatedByConnect() {
        return Optional.ofNullable(createdByConnect).orElse(Boolean.FALSE);
    }

    public void setCreatedByConnect(Boolean createdByConnect) { this.createdByConnect = createdByConnect; }

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDateTime.now();
        this.modifiedOn = LocalDateTime.now();
    }

    @PreUpdate
    public void setModifiedOn() {
        this.modifiedOn = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return user.getUserCode().equals(this.getUserCode());
    }

    @Override
    public int hashCode() {
        return userCode.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add(FIRST_NAME, firstName)
                .add(LAST_NAME, lastName)
                .add(EMAIL, email)
                .add(STATUS, memberUserStatus)
                .add(ROLES, authorities)
                .add(ORGANISATION_CODE, organisationCode)
                .toString();
    }

    public void addAuthorities(final Set<Authority> authorities) {
        this.authorities.addAll(authorities);
    }

    public void addPasswordHistory() {
        if (passwordHistories == null) {
            passwordHistories = newArrayList();
        }

        passwordHistories.add(new UserPasswordHistory(this));
    }

    public Integer getNumberAttemptsResendRegistrationEmail() {
        return numberAttemptsResendRegistrationEmail;
    }

    public void setNumberAttemptsResendRegistrationEmail(Integer numberAttemptsResendRegistrationEmail) {
        this.numberAttemptsResendRegistrationEmail = numberAttemptsResendRegistrationEmail;
    }

    public Integer getNumberAttemptsResendRegistrationEmailBySedexAdmin() {
        return numberAttemptsResendRegistrationEmailBySedexAdmin;
    }

    public void setNumberAttemptsResendRegistrationEmailBySedexAdmin(Integer numberAttemptsResendRegistrationEmailBySedexAdmin) {
        this.numberAttemptsResendRegistrationEmailBySedexAdmin = numberAttemptsResendRegistrationEmailBySedexAdmin;
    }

    public String getEmailNotificationLanguage() {
        return emailNotificationLanguage;
    }

    public void setEmailNotificationLanguage(String emailNotificationLanguage) {
        this.emailNotificationLanguage = emailNotificationLanguage;
    }

    public LocalDateTime getLastLoggedInTime() {
        return lastLoggedInTime;
    }

    public void setLastLoggedInTime(LocalDateTime lastLoggedInTime) {
        this.lastLoggedInTime = lastLoggedInTime;
    }

    public LocalDateTime getPreviousLoggedInTime() {
        return previousLoggedInTime;
    }

    public void setPreviousLoggedInTime(LocalDateTime previousLoggedInTime) {
        this.previousLoggedInTime = previousLoggedInTime;
    }

    public UserLogDTO convertToUserLogDTO() {
        UserLogDTO userLogDTO = new UserLogDTO();

        userLogDTO.setEmail(this.email);
        String authorityUserAud = StringUtils.EMPTY;
        if (this.authorities != null && this.authorities.size() > 0) {

            // fix error related to text contain text eg: SUPER_SEDEX_ADMIN contain SEDEX_ADMIN
            authorityUserAud = this.authorities.stream().map(e-> "|" + e.getName() + "|").collect(Collectors.joining(", "));
        }

        userLogDTO.setAuthorities(authorityUserAud);
        userLogDTO.setFirstName(this.firstName);
        userLogDTO.setLastName(this.lastName);
        userLogDTO.setUserStatus(this.memberUserStatus.name());
        userLogDTO.setOrganisationCode(this.organisationCode);
        userLogDTO.setUserCode(this.userCode);
        userLogDTO.setModifiedOn(LocalDateTime.now());
        userLogDTO.setCreatedByConnect(this.createdByConnect);

        return userLogDTO;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getApscaNumber() {
        return apscaNumber;
    }

    public void setApscaNumber(String apscaNumber) {
        this.apscaNumber = apscaNumber;
    }

    public String getApscaStatus() {
        return apscaStatus;
    }

    public void setApscaStatus(String apscaStatus) {
        this.apscaStatus = apscaStatus;
    }

    public Boolean isSuspendAuditor() {
        return suspendAuditor;
    }

    public void setSuspendAuditor(Boolean suspendAuditor) {
        this.suspendAuditor = suspendAuditor;
    }

    public String getElearningCode() {
        return elearningCode;
    }

    public void setElearningCode(String elearningCode) {
        this.elearningCode = elearningCode;
    }

    public String getDummyPassword() {
        return dummyPassword;
    }

    public void setDummyPassword(String dummyPassword) {
        this.dummyPassword = dummyPassword;
    }

    public Boolean getIsMasteredByConnect() {
        return Optional.ofNullable(isMasteredByConnect).orElse(Boolean.FALSE);
    }

    public void setIsMasteredByConnect(Boolean isMasteredByConnect) {
        this.isMasteredByConnect = isMasteredByConnect;
    }
}
