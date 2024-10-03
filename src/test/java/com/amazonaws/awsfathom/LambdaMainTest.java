package com.amazonaws.awsfathom.videoanalyticslogistics;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times; 
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.awsfathom.videoanalyticslogistics.util.aws.RequestProcessor;
import com.amazonaws.awsfathom.videoanalyticslogistics.util.aws.IdentityManager;
import com.amazonaws.awsfathom.videoanalyticslogistics.util.aws.Credentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LambdaMainTest {

    private LambdaMain lambdaMain;

    @Mock
    private RequestProcessor mockRequestProcessor;

    @Mock
    private IdentityManager mockIdentityManager;

    @Mock
    private Context mockContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lambdaMain = new LambdaMain();
        setField(lambdaMain, "requestProcessor", mockRequestProcessor);
        setField(lambdaMain, "identityManager", mockIdentityManager);
    }

    @Test
    void testHandleRequest() {

        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
        APIGatewayProxyRequestEvent.ProxyRequestContext requestContext = new APIGatewayProxyRequestEvent.ProxyRequestContext();
        requestContext.setRequestId("testRequestId");
        input.setRequestContext(requestContext);

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Account-Id", "testAccountId");
        headers.put("X-Access-Key", "testAccessKey");
        headers.put("X-Secret-Key", "testSecretKey");
        headers.put("X-Session-Token", "testSessionToken");
        input.setHeaders(headers);

        APIGatewayProxyResponseEvent expectedResponse = new APIGatewayProxyResponseEvent();
        when(mockRequestProcessor.processRequest(any(), any())).thenReturn(expectedResponse);

        APIGatewayProxyResponseEvent result = lambdaMain.handleRequest(input, mockContext);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedResponse, result);
        verify(mockIdentityManager).setAccountId("testAccountId");
        verify(mockIdentityManager).setCredentials(any(Credentials.class));
        //  clearIdentity is called twice
        verify(mockIdentityManager, times(2)).clearIdentity(); 
    }

    @Test
    void testHandleRequestWithNullHeaders() {

        APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
        APIGatewayProxyRequestEvent.ProxyRequestContext requestContext = new APIGatewayProxyRequestEvent.ProxyRequestContext();
        requestContext.setRequestId("testRequestId");
        input.setRequestContext(requestContext);
        input.setHeaders(null);

        APIGatewayProxyResponseEvent expectedResponse = new APIGatewayProxyResponseEvent();
        when(mockRequestProcessor.processRequest(any(), any())).thenReturn(expectedResponse);


        APIGatewayProxyResponseEvent result = lambdaMain.handleRequest(input, mockContext);


        assertNotNull(result, "Result should not be null");
        assertEquals(expectedResponse, result);
        verify(mockIdentityManager).setAccountId("");
        verify(mockIdentityManager).setCredentials(any(Credentials.class));
        verify(mockIdentityManager, times(2)).clearIdentity(); 
    }


    private void setField(Object object, String fieldName, Object fieldValue) {
        try {
            java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error setting field " + fieldName, e);
        }
    }
}
