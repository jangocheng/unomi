package org.oasis_open.contextserver.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Session extends Item implements TimestampedItem {

    public static final String ITEM_TYPE = "session";

    private String profileId;

    private Profile profile;

    private Map<String,Object> properties;

    private Date timeStamp;

    private String scope;

    private Date lastEventDate;

    private int duration = 0;

    public Session() {
    }

    public Session(String itemId, Profile profile, Date timeStamp, String scope) {
        super(itemId);
        this.profile = profile;
        this.profileId = profile.getItemId();
        properties = new HashMap<String, Object>();
        this.timeStamp = timeStamp;
        this.scope = scope;
    }

    public String getProfileId() {
        return profileId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profileId = profile.getItemId();
        this.profile = profile;
    }

    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }

    public Object getProperty(String name) {
        return properties.get(name);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Date getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(Date lastEventDate) {
        this.lastEventDate = lastEventDate;
        if (lastEventDate != null) {
            duration = (int) (lastEventDate.getTime() - timeStamp.getTime());
        }
    }

    public int getDuration() {
        return duration;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
