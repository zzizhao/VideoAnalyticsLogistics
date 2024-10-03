package com.amazonaws.awsfathom.videoanalyticslogistics;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import com.amazonaws.awsfathom.videoanalyticslogistics.util.aws.RequestProcessor;
import com.amazonaws.awsfathom.videoanalyticslogistics.util.aws.IdentityManager;
import com.amazonaws.awsfathom.videoanalyticslogistics.util.aws.Credentials;  
import java.util.HashMap;
import java.util.Map;

public class LambdaMain implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LogManager.getLogger(LambdaMain.class);
    private static final String X_ACCOUNT_ID_HEADER = "X-Account-Id";
    private static final String X_ACCESS_KEY_HEADER = "X-Access-Key";
    private static final String X_SECRET_KEY_HEADER = "X-Secret-Key";
    private static final String X_SESSION_TOKEN_HEADER = "X-Session-Token";

    private final RequestProcessor requestProcessor;
    private final IdentityManager identityManager;

    public LambdaMain() {
        this.requestProcessor = new RequestProcessor();
        this.identityManager = new IdentityManager();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        LOG.info("Entered request path");
        ThreadContext.put("ApiGatewayRequestId", input.getRequestContext().getRequestId());

        setIdentity(input);

        try {
            return requestProcessor.processRequest(input, context);
        } finally {
            identityManager.clearIdentity();
        }
    }

    private void setIdentity(APIGatewayProxyRequestEvent input) {
        identityManager.clearIdentity();

        Map<String, String> headers = input.getHeaders();
        if (headers == null) {
            headers = new HashMap<>();
        }

        String accountId = getHeader(X_ACCOUNT_ID_HEADER, headers);
        Credentials credentials = new Credentials(
            getHeader(X_ACCESS_KEY_HEADER, headers),
            getHeader(X_SECRET_KEY_HEADER, headers),
            getHeader(X_SESSION_TOKEN_HEADER, headers)
        );

        identityManager.setAccountId(accountId);
        identityManager.setCredentials(credentials);
    }

    private String getHeader(String headerName, Map<String, String> headers) {
        return headers.getOrDefault(headerName, "");
    }
}
