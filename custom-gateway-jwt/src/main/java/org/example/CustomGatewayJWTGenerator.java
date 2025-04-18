package org.example;

import org.osgi.service.component.annotations.Component;
import org.wso2.carbon.apimgt.common.gateway.dto.JWTInfoDto;
import org.wso2.carbon.apimgt.common.gateway.jwtgenerator.APIMgtGatewayJWTGeneratorImpl;
import java.util.Map;

@Component(
        service = org.wso2.carbon.apimgt.common.gateway.jwtgenerator.AbstractAPIMgtGatewayJWTGenerator.class,
        name = "customGatewayJWTGenerator",
        immediate = true
)
public class CustomGatewayJWTGenerator extends APIMgtGatewayJWTGeneratorImpl {

    @Override
    public Map<String, Object> populateStandardClaims(JWTInfoDto jwtInfoDto) {
        Map<String, Object> claims = super.populateStandardClaims(jwtInfoDto);
        // Example: Strip "@carbon.super" from the enduser claim
        String dialect = getDialectURI();
        String key = dialect + "/enduser";
        Object o = claims.get(key);
        if (o instanceof String && ((String)o).endsWith("@carbon.super")) {
            String trimmed = ((String)o).replace("@carbon.super", "");
            claims.put(key, trimmed);
        }
        return claims;
    }

    @Override
    public Map<String, Object> populateCustomClaims(JWTInfoDto jwtInfoDto) {
        return super.populateCustomClaims(jwtInfoDto);
    }
}