package com.masaigai.springcodeconverterApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeConversionRequest {

    private String sourceCode;
    private String sourceLanguage;
    private String targetLanguage;

    // getters and setters
}

