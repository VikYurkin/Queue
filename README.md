# Queue
Test task for Geek Prtns

Createв RestAPI app for generating series on RestAPI_to_series branch.

API:

1) Request: GET /series 

   Exaple response: JSON: {
                          "series": "a0a0"
                        }
                        
2) Request: GET /series/next 

    Exaple response: JSON: {
                          "series": "a0a1"
                        } 
                        
                        
3)  Request: POST /series

    Exaple request: JSON: {
                          "series": "d0d1"
                          }

    Response: "OK"

Apps generated and saved in DB unique series for get request and continues series for get request.   
  
