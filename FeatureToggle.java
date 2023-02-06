package com.sedex.spectrum.appsvc.user.management.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "feature_toggle")
public class FeatureToggle {

    @EmbeddedId
    private FeatureToggleId featureToggleId;

    @Column(name = "activated")
    private boolean activated;

    public FeatureToggle(final FeatureToggleId featureToggleId, final boolean activated) {
        this.featureToggleId = featureToggleId;
        this.activated = activated;
    }

    private FeatureToggle() {}

    public FeatureToggleId getFeatureToggleId() {
        return featureToggleId;
    }

    public void setFeatureToggleId(final FeatureToggleId featureToggleId) {
        this.featureToggleId = featureToggleId;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(final boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this, obj, "activated");
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this, "activated");
    }

    public static class FeatureToggleId implements Serializable {
        @Column(name = "user_code")
        private String userCode;

        @Column(name = "feature_name")
        private String featureName;

        public FeatureToggleId(final String userCode, final String featureName) {
            this.userCode = userCode;
            this.featureName = featureName;
        }

        private FeatureToggleId() {}

        @Override
        public boolean equals(Object obj){
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        @Override
        public int hashCode(){
            return HashCodeBuilder.reflectionHashCode(this);
        }

        public String getUserCode() {
            return userCode;
        }

        public String getFeatureName() {
            return featureName;
        }
    }
}
