package com.masaigai.springcodeconverterApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private CodeConverterService codeConverterService;


    @PostMapping("/convert")
    public String codeConvert(@RequestBody CodeConversionRequest prompt){

       return codeConverterService.convertCode(prompt.getSourceCode(), prompt.getSourceLanguage(), prompt.getTargetLanguage());
    }

    @PostMapping("/quality")
    public String codeQualityCheck(@RequestBody CodeConversionRequest prompt){

        return codeConverterService.qualityCheck(prompt.getSourceCode(), prompt.getSourceLanguage(), prompt.getTargetLanguage());
    }

}
