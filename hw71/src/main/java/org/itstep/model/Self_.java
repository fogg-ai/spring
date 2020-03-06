
package org.itstep.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "templated"
})
public class Self_ {

    @JsonProperty("href")
    private String href;
    @JsonProperty("templated")
    private Boolean templated;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("templated")
    public Boolean getTemplated() {
        return templated;
    }

    @JsonProperty("templated")
    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }


}
