package com.masaigai.springcodeconverterApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CodeConverterService {

    // Inject any necessary dependencies, e.g., RestClient to interact with ChatGPT API
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    public String convertCode(String sourceCode, String sourceLanguage, String targetLanguage) throws CodeConversionException {

        String prompt = "can you please convert this  \n "+sourceCode+" to \n"+targetLanguage+"\n and don't write anything except converted code and in proper format";
        ChatRequest request = new ChatRequest(model,prompt,10);
        ChatResponse response = restTemplate.postForObject(apiUrl,request,ChatResponse.class);
        if(response == null || response.getChoices() == null){
            return "No Response";

        }
        return  response.getChoices().get(0).getMessage().getContent();


    }

    public String qualityCheck(String sourceCode, String sourceLanguage, String targetLanguage) {

        String prompt = "this is the source code \n "+sourceCode +"\n  " +
                "Please provide a code quality assessment for the given code. Consider the following parameters:\n" +
                "\n" +
                "1. Code Consistency: Evaluate the code for consistent coding style, naming conventions, and formatting.\n" +
                "2. Code Performance: Assess the code for efficient algorithms, optimized data structures, and overall performance considerations.\n" +
                "3. Code Documentation: Review the code for appropriate comments, inline documentation, and clear explanations of complex logic.\n" +
                "4. Error Handling: Examine the code for proper error handling and graceful error recovery mechanisms.\n" +
                "5. Code Testability: Evaluate the code for ease of unit testing, mocking, and overall testability.\n" +
                "6. Code Modularity: Assess the code for modular design, separation of concerns, and reusability of components.\n" +
                "7. Code Complexity: Analyze the code for excessive complexity, convoluted logic, and potential code smells.\n" +
                "8. Code Duplication: Identify any code duplication and assess its impact on maintainability and readability.\n" +
                "9. Code Readability: Evaluate the code for readability, clarity, and adherence to coding best practices.\n" +
                "\n" +
                "Please provide a summary of the code quality assessment and a report showing the percentage-wise evaluation for each parameter mentioned above.\n";
        ChatRequest request = new ChatRequest(model,prompt,10);
        ChatResponse response = restTemplate.postForObject(apiUrl,request,ChatResponse.class);
        if(response == null || response.getChoices() == null){
            return "No Response";

        }
        return  response.getChoices().get(0).getMessage().getContent();
    }
}

