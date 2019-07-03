#Server

port=9999
context-path=/jw

#DATABASE

username = "example"
password = "example"
dbname = "jw_dev" // for dev profile


#USER CREDENTIAL
------------------------------------------------------
	Username		Password	Role
------------------------------------------------------
"shadhin.aust@gmail.com"	"admin" 	"admin"
"karim@gmail.com"		"admin"		"user"
"bayezid@gmail.com"		"admin"		"user"
"ariful@gmail.com"		"admin"		"user"

NOTE: Only #Admin has the authority to delete any data(as for example)


----------------------------------------------------------------
-----------------------------TOKEN------------------------------
----------------------------------------------------------------

When a user login, a #TOKEN is given in the #HEADER part as #token
#TOKEN is sent Authorization as Bearer Token
		
