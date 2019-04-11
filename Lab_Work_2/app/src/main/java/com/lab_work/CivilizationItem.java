package com.lab_work;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CivilizationItem implements Serializable {

    @Override
    public String toString() {
        return "Civilization [" +
                "description: " + description +
                ", formatVersion: " + formatVersion +
                ", options: " + options +
                ", flags: " + flags +
                ", graphic: " + graphic +
                ", graphicAlt: " + graphicAlt +
                ", name: " + name +
                ", req1: " + req1 +
                ", req2: " + req2 +
                ", helptext: " + helptext +
                ", bonusMessage: " + bonusMessage +
                ']';
    }

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("format_version")
    @Expose
    private String formatVersion;
    @SerializedName("options")
    @Expose
    private String options;
    @SerializedName("flags")
    @Expose
    private String flags;
    @SerializedName("graphic")
    @Expose
    private String graphic;
    @SerializedName("graphic_alt")
    @Expose
    private String graphicAlt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("req1")
    @Expose
    private String req1;
    @SerializedName("req2")
    @Expose
    private String req2;
    @SerializedName("helptext")
    @Expose
    private String helptext;
    @SerializedName("bonus_message")
    @Expose
    private String bonusMessage;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public String getGraphicAlt() {
        return graphicAlt;
    }

    public void setGraphicAlt(String graphicAlt) {
        this.graphicAlt = graphicAlt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReq1() {
        return req1;
    }

    public void setReq1(String req1) {
        this.req1 = req1;
    }

    public String getReq2() {
        return req2;
    }

    public void setReq2(String req2) {
        this.req2 = req2;
    }

    public String getHelptext() {
        return helptext;
    }

    public void setHelptext(String helptext) {
        this.helptext = helptext;
    }

    public String getBonusMessage() {
        return bonusMessage;
    }

    public void setBonusMessage(String bonusMessage) {
        this.bonusMessage = bonusMessage;
    }

}