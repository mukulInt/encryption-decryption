package inceptiondecryption.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class IdDto {

    private String nameId;
    private String emailId;

    private String home;

    public String getNameId() {
        return nameId;
    }

    public IdDto(String nameId, String emailId, String home) {
        this.nameId = nameId;
        this.emailId = emailId;
        this.home = home;
    }

    public IdDto() {
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "IdDto{" +
                "nameId='" + nameId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
