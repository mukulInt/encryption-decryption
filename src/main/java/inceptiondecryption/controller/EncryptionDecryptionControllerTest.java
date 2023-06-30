package inceptiondecryption.controller;

import inceptiondecryption.common.dto.IdDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class EncryptionDecryptionControllerTest {


    @GetMapping("get")
    public IdDto encDnc(){

        IdDto idDto  = new IdDto();
        idDto.setEmailId("mukul@gmail.com");
        idDto.setHome("kolkata");
        idDto.setNameId("name");

        return idDto;
    }

    @PostMapping("post")
    public IdDto encDncPost(@RequestBody IdDto idDto){

        System.out.println( "+++++++++++++++"+idDto);

        return idDto;
    }


}
