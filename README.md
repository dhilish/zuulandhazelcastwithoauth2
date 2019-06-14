# zuulandhazelcastwithoauth2
Sample project with zuul as gataway, oauth2 for security and hazelcast as distributed session

SCENERIO
--------------------------------------------------------------------------------------------------
I have Zuul Server as API gateway, Identity Server with Oauth2, ResourceServer1 and ResourceServer2 in the following code sample

ZuulServer, ResourceServer1 and ResourceServer2 got a distributed session with Hazelcast.

Following are the workflow
1. Http page reqest comes to zuul server which will be redirected to login page at identity server. Autheticate user with oauth2. (A jsessionid is returned with the path "/")

2. On receipt of auth token at zuul gateway, kept the auth token in distributed session using hazelcast.

3. On authentication success handler of Zuul Gateway, page is redirected to home page at ResourceServer1

4. Web page call to resourceServer1 are routed through zuul api gateway and here at gateway the auth token is appended

5. Before showing the home page, inside home page controller i need two functionalities

6. Functionality 1 - Set a value to the distributed hazelcast session with the key "TEST_SESSION"

7. Functionality 2 - Make RestTemplate calls to ResourceServer2(here I append the cookie)

8. RestTemplate calls to ResourceServer2 are routed through Zuul API Gateway and here at gateway the auth token is appended

9. Control reached ResourceServer2 rest api controller and the checked session values. Value kept in step2 is available but step6 value is not available.

10. There after homepage is displayed in browser(A new session is returned with ResourceServer1 context path)

11. From web page made rest api call to ResourceServer1 through Zuul API Gateway and here the auth token is appended.

12. Control reached ResourceServer2 controller and returned the expected value.

13. Noticed for every api call to ResourceServer1 from web browser makes a new session for ResourceServer1.

PROBLEM
-------------------------------------------------------------------------------------------------------------
I am stuck with following problems for couple of weeks. Kindly please help on following:- 

1. I want to have all of resource servers having access to session information created by ResourceServer1. But I could not get session information on other resource servers. its alway return null, I have checked hazelcast server using hazelcast mancenter and seen session is distributed.

2. How to restrict the creation of new session while make an api call to resource server. This is noticed in step 9, 10 and 13. Please note here I want to get the value of session created in step 2 and 6 using HttpServletReqest.getSession, and this creates the new session. If I remove the code to get the value in session,  the new session will not be created. 

Thanks in advance
