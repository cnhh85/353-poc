package com.sedex.spectrum.appsvc.user.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@MappedSuperclass
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConnectUser extends User {
    @JsonIgnore
    @OneToMany(mappedBy = "connectUser", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FeatureToggle> featureToggle;

    @JsonIgnore
    @OneToMany(mappedBy = "userCode", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserPreferences> userPreferences;

    @JsonIgnore
    @OneToMany(mappedBy = "userCode", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserPreferencesForSubsidiary> userPreferencesForSubsidiary;

    public Set<FeatureToggle> getFeatureToggle() {
        return featureToggle;
    }

    public void addFeatureToggle(Set<FeatureToggle> featureToggle) {
        this.featureToggle.addAll(featureToggle);
    }

    public Set<UserPreferences> getUserPreferences() {
        return userPreferences;
    }

    public void addUserPreferences(Set<UserPreferences> userPreferences) {
        this.userPreferences.addAll(userPreferences);
    }

    public Set<UserPreferencesForSubsidiary> getUserPreferencesForSubsidiary() {
        return userPreferencesForSubsidiary;
    }

    public void addUserPreferencesForSubsidiary(Set<UserPreferencesForSubsidiary> userPreferencesForSubsidiary) {
        this.userPreferencesForSubsidiary.addAll(userPreferencesForSubsidiary);
    }

}
