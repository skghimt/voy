package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-28.
 */
public class SystemPropertiesResponse extends BaseRespones {

    public static class SystemProperty{
        private long id;
        private String value;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private List<SystemProperty> properties;

    public List<SystemProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SystemProperty> properties) {
        this.properties = properties;
    }
}
