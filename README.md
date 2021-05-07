# TicketupportRoleMangement


My EndPoints-> 

Just -> Use POST-MAN

========================================================================

#Post Method => http://localhost:8080/api/auth/signup

{
	  "email":"email@gmail.com",
		"username":"username",
		"firstName":"username",
		"lastName":"username",
    "password":"123456",
    "role":"admin"
}

==========================================================================

#POST Method => localhost:8080/api/auth/login
{

   		"username":"username",
        "password":"password"
}


========================================================================

#Post Method => http://localhost:8080/api/tickets/add

{
	"message":"Message example 2"
}

========================================================================

#PUT Method => localhost:8080/api/user/edit-firstName
{
   		"firstName":"hishamsdddsdds5"
}

========================================================================

#Get Method => http://localhost:8080/api/tickets/list


