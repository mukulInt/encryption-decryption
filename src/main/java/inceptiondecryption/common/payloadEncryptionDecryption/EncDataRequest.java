package inceptiondecryption.common.payloadEncryptionDecryption;



public class EncDataRequest {
    String encData;

    public String getEncData() {
        return encData;
    }

    public void setEncData(String encData) {
        this.encData = encData;
    }

    public EncDataRequest(String encData) {
        this.encData = encData;
    }

    public EncDataRequest() {
    }

    @Override
    public String toString() {
        return "EncDataRequest{" +
                "encData='" + encData + '\'' +
                '}';
    }
}
