package com.manoj.springsecurity;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.manoj.springsecurity.oauth.model.AuthTokenInfo;
import com.manoj.springsecurity.oauth.model.User;

/*
 *  Endpoints and their purpose
Attempt to access resources [REST API] without any authorization [will fail of-course].
GET http://localhost:8080/springsecurityoauth2/user/

Ask for tokens[access+refresh] using HTTP POST on /oauth/token, with grant_type=password,and resource owners credentials as req-params. Additionally, send client credentials in Authorization header.
POST http://localhost:8080/springsecurityoauth2/oauth/token?grant_type=password&username=bill&password=abc123

Ask for a new access token via valid refresh-token, using HTTP POST on /oauth/token, with grant_type=refresh_token,and sending actual refresh token. Additionally, send client credentials in Authorization header.
POST http://localhost:8080/springsecurityoauth2/oauth/token?grant_type=refresh_token&refresh_token=094b7d23-973f-4cc1-83ad-8ffd43de1845

Access the resource by providing an access token using access_token query param with request.
GET http://localhost:8080/springsecurityoauth2/user/?access_token=3525d0e4-d881-49e7-9f91-bcfd18259109


<bean id="restTemplate" class="org.springframework.web.client.RestTemplate">
        <property name="errorHandler" ref="externalServiceResponseErrorHandler"></property>
        <constructor-arg>
            <bean class="org.springframework.http.client.HttpComponentsClientHttpRequestFactory">
                <constructor-arg ref="httpClient"/>
                <property name="readTimeout" value="180000" />
				<property name="connectTimeout" value="60000" />
            </bean>
        </constructor-arg>
    </bean>
    
<bean id="httpClient" class="org.apache.http.impl.client.DefaultHttpClient">
        <constructor-arg>
            <bean class="org.apache.http.impl.conn.PoolingClientConnectionManager"/>
        </constructor-arg>
    </bean>    
    
 * 
 * 
 */

public class SpringRestClient {
	 public static final String REST_SERVICE_URI = "http://localhost:8080/springsecurityoauth2";
     
	    public static final String AUTH_SERVER_URI = "http://localhost:8080/springsecurityoauth2/oauth/token";
	     
	    public static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=bill&password=abc123";
	     
	    public static final String QPM_ACCESS_TOKEN = "?access_token=";
	 
	    /*
	     * Prepare HTTP Headers.
	     */
	    private static HttpHeaders getHeaders(){
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        return headers;
	    }
	     
	    /*
	     * Add HTTP Authorization header, using Basic-Authentication to send client-credentials.
	     */
	    private static HttpHeaders getHeadersWithClientCredentials(){
	        String plainClientCredentials="my-trusted-client:secret";
	        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
	         
	        HttpHeaders headers = getHeaders();
	        headers.add("Authorization", "Basic " + base64ClientCredentials);
	        return headers;
	    }    
	     
	    /*
	     * Send a POST request [on /oauth/token] to get an access-token, which will then be send with each request.
	     */
	    @SuppressWarnings({ "unchecked"})
	    private static AuthTokenInfo sendTokenRequest(){
	        RestTemplate restTemplate = new RestTemplate(); 
	         
	        HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
	        ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI+QPM_PASSWORD_GRANT, HttpMethod.POST, request, Object.class);
	        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)response.getBody();
	        AuthTokenInfo tokenInfo = null;
	         
	        if(map!=null){
	            tokenInfo = new AuthTokenInfo();
	            tokenInfo.setAccess_token((String)map.get("access_token"));
	            tokenInfo.setToken_type((String)map.get("token_type"));
	            tokenInfo.setRefresh_token((String)map.get("refresh_token"));
	            tokenInfo.setExpires_in((int)map.get("expires_in"));
	            tokenInfo.setScope((String)map.get("scope"));
	            System.out.println(tokenInfo);
	            //System.out.println("access_token ="+map.get("access_token")+", token_type="+map.get("token_type")+", refresh_token="+map.get("refresh_token")
	            //+", expires_in="+map.get("expires_in")+", scope="+map.get("scope"));;
	        }else{
	            System.out.println("No user exist----------");
	             
	        }
	        return tokenInfo;
	    }
	     
	    /*
	     * Send a GET request to get list of all users.
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static void listAllUsers(AuthTokenInfo tokenInfo){
	        Assert.notNull(tokenInfo, "Authenticate first please......");
	 
	        System.out.println("\nTesting listAllUsers API-----------");
	        RestTemplate restTemplate = new RestTemplate(); 
	         
	        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
	        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/user/"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
	                HttpMethod.GET, request, List.class);
	        List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>)response.getBody();
	         
	        if(usersMap!=null){
	            for(LinkedHashMap<String, Object> map : usersMap){
	                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	            }
	        }else{
	            System.out.println("No user exist----------");
	        }
	    }
	      
	    /*
	     * Send a GET request to get a specific user.
	     */
	    private static void getUser(AuthTokenInfo tokenInfo){
	        Assert.notNull(tokenInfo, "Authenticate first please......");
	        System.out.println("\nTesting getUser API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
	        ResponseEntity<User> response = restTemplate.exchange(REST_SERVICE_URI+"/user/1"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
	                HttpMethod.GET, request, User.class);
	        User user = response.getBody();
	        System.out.println(user);
	    }
	      
	    /*
	     * Send a POST request to create a new user.
	     */
	    private static void createUser(AuthTokenInfo tokenInfo) {
	        Assert.notNull(tokenInfo, "Authenticate first please......");
	        System.out.println("\nTesting create User API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        User user = new User(0,"Sarah",51,134);
	        HttpEntity<Object> request = new HttpEntity<Object>(user, getHeaders());
	        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
	                request, User.class);
	        System.out.println("Location : "+uri.toASCIIString());
	    }
	  
	    /*
	     * Send a PUT request to update an existing user.
	     */
	    private static void updateUser(AuthTokenInfo tokenInfo) {
	        Assert.notNull(tokenInfo, "Authenticate first please......");
	        System.out.println("\nTesting update User API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        User user  = new User(1,"Tomy",33, 70000);
	        HttpEntity<Object> request = new HttpEntity<Object>(user, getHeaders());
	        ResponseEntity<User> response = restTemplate.exchange(REST_SERVICE_URI+"/user/1"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
	                HttpMethod.PUT, request, User.class);
	        System.out.println(response.getBody());
	    }
	  
	    /*
	     * Send a DELETE request to delete a specific user.
	     */
	    private static void deleteUser(AuthTokenInfo tokenInfo) {
	        Assert.notNull(tokenInfo, "Authenticate first please......");
	        System.out.println("\nTesting delete User API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
	        restTemplate.exchange(REST_SERVICE_URI+"/user/3"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
	                HttpMethod.DELETE, request, User.class);
	    }
	  
	  
	    /*
	     * Send a DELETE request to delete all users.
	     */
	    private static void deleteAllUsers(AuthTokenInfo tokenInfo) {
	        Assert.notNull(tokenInfo, "Authenticate first please......");
	        System.out.println("\nTesting all delete Users API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
	        restTemplate.exchange(REST_SERVICE_URI+"/user/"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
	                HttpMethod.DELETE, request, User.class);
	    }
	  
	    public static void main(String args[]){
	        AuthTokenInfo tokenInfo = sendTokenRequest();
	        listAllUsers(tokenInfo);
	         
	        getUser(tokenInfo);
	         
	        createUser(tokenInfo);
	        listAllUsers(tokenInfo);
	         
	        updateUser(tokenInfo);
	        listAllUsers(tokenInfo);
	         
	        deleteUser(tokenInfo);
	        listAllUsers(tokenInfo);
	         
	        deleteAllUsers(tokenInfo);
	        listAllUsers(tokenInfo);
	    }
}
