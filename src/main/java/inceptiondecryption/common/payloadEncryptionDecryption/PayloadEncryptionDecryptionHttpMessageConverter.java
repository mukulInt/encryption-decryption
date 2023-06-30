package inceptiondecryption.common.payloadEncryptionDecryption;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import inceptiondecryption.common.encryptionDecryption.EncryptionDecryptionWithAES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class PayloadEncryptionDecryptionHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    @Autowired
    private EncryptionDecryptionWithAES payloadEncryptorDecryptor;



    List<String> whitelistedClasses = Arrays.asList("LinkedHashMap","ResponseData"
            //common
            , "IdDto"
            // here we will write all white listed urls

    );

    public PayloadEncryptionDecryptionHttpMessageConverter(){
        super(MediaType.ALL);

    }

    @Override
    protected boolean supports(Class<?> clazz) {

        return whitelistedClasses.contains(clazz.getSimpleName());
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        Instant nowInstant = CommonUtils.startTime();
        System.out.println("+++++++++++51"+inputMessage.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

//        EncDataRequest encryptData=  objectMapper.readValue(inputMessage.getBody(), EncDataRequest.class);
        EncDataRequest encryptData = null;
        try{
             encryptData = objectMapper.readValue(inputMessage.getBody(), EncDataRequest.class);

        }
        catch (Exception e){
            e.printStackTrace();
        }


        String encData= encryptData.getEncData();
        Object obj = null;
        try {
            obj = objectMapper.readValue(payloadEncryptorDecryptor.decrypt(encData),clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//        Instant nowInstant = CommonUtils.startTime();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String encryptData = null;
        try {
            encryptData = payloadEncryptorDecryptor.encrypt(objectMapper.writeValueAsString(o));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        outputMessage.getBody().write(encryptData.getBytes());
    }
}
