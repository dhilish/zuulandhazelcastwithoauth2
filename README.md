# Sample project with zuul, oauth2, and hazelcast
Sample project with zuul for api gataway, oauth2 for security and hazelcast for distributed session

SCENERIO
--------------------------------------------------------------------------------------------------
I have Zuul Server as API gateway, Identity Server with Oauth2, ResourceServer1 and ResourceServer2 in the following code sample

ZuulServer, ResourceServer1 and ResourceServer2 have a distributed session with Hazelcast.

Following are the steps that i had performed - 
1. Http page reqest that comes to zuul server will be redirected to login page at identity server. Autheticate user with oauth2. (A jsessionid is returned with the path "/")

2. On receipt of auth token at zuul gateway, retained the auth token in distributed session using Hazelcast.

3. On authentication success handler of Zuul Gateway, page is redirected to home page at ResourceServer1

4. Web page call to resourceServer1 got routed through zuul api gateway and here at gateway, the auth token was appended

5. Inside home page controller I wrote two functionalities, before showing the home page

6. Functionality 1 - Set a value to the distributed hazelcast session with the key "TEST_SESSION"

7. Functionality 2 - Made RestTemplate calls to ResourceServer2 (here I appended the cookie)

8. RestTemplate calls to ResourceServer2 were routed through Zuul API Gateway and here at gateway, the auth token was appended

9. Control reached the ResourceServer2 rest api controller and checked the session values. Value kept in step2 was available, but step6 value was not available.

10. Soon afterwards, the homepage was displayed in browser (A new session was returned with ResourceServer1 context path)

11. From web page, made rest api call to ResourceServer1 through Zuul API Gateway and here the auth token was appended.

12. Control reached ResourceServer2 controller and returned the expected value.

13. Found that for every api call to ResourceServer1 from web browser, a new session was created for ResourceServer1.

PROBLEM
-------------------------------------------------------------------------------------------------------------
I have been stuck with the following problems for the past few weeks. Please help us in this regard:- 

1. I want to have ResourceServer2 having access to session information created by ResourceServer1. But I could not get session information on ResourceServer2, it is always returning null. I had checked Hazelcast server using Hazelcast Mancenter and found that session is distributed.

2. How do I restrict the creation of new sessions while making an api call to resource server? This is noticed in step 9, 10 and 12. Please note that here I want to get the value of session created in step 2 and 6 using HttpServletReqest.getSession(), and which creates a new session. If I remove the code to get the value in session,  the new session will not be created. 

Thanks in advance
