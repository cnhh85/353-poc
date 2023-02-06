package com.sedex.spectrum.appsvc.user.management.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class FeatureToggleId implements Serializable {
    @Column(name = "user_code")
    private String userCode;

    @Column(name = "feature_name")
    private String featureName;

    public FeatureToggleId(final String userCode, final String featureName) {
        this.userCode = userCode;
        this.featureName = featureName;
    }

    public FeatureToggleId() {}

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
