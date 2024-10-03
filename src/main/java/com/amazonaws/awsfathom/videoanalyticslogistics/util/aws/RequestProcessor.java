package com.amazonaws.awsfathom.videoanalyticslogistics.util.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class RequestProcessor {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public APIGatewayProxyResponseEvent processRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        
        try {
            Map<String, String> body = objectMapper.readValue(input.getBody(), HashMap.class);
            
            // some mock up validation
            if (!body.containsKey("message")) {
                return createErrorResponse(400, "Missing 'message' in the request body");
            }
            
            String message = body.get("message");
            
            // mock up for now in this example, we'll just reverse it
            String processedMessage = new StringBuilder(message).reverse().toString();
            
            // create a response body
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("originalMessage", message);
            responseBody.put("processedMessage", processedMessage);
            
            response.setStatusCode(200);
            response.setBody(objectMapper.writeValueAsString(responseBody));
            
        } catch (Exception e) {
            context.getLogger().log("Error processing request: " + e.getMessage());
            return createErrorResponse(500, "Internal server error");
        }
        
        return response;
    }
    
    private APIGatewayProxyResponseEvent createErrorResponse(int statusCode, String errorMessage) {
        APIGatewayProxyResponseEvent errorResponse = new APIGatewayProxyResponseEvent();
        errorResponse.setStatusCode(statusCode);
        
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", errorMessage);
        
        try {
            errorResponse.setBody(objectMapper.writeValueAsString(errorBody));
        } catch (Exception e) {
            errorResponse.setBody("{\"error\":\"Error creating error response\"}");
        }
        
        return errorResponse;
    }
}