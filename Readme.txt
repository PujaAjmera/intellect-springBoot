Url: to access the webservices :

To Retrieve all the users :   /users
To Retrieve user by userId :  /users/{usersId}
AddUser                    : /users
					body :   
					[id:3,fname:ABC,lname:LastName,email:abc@gmail.com,pinCode:pinCode,birthDate: [value:{"
             + "date:16-FEB-2017}],isActive=true]
update user :     /users/{userId}
					[id:3,fname:ABC,lname:LastName,email:abc@gmail.com,pinCode:pinCode,birthDate: [value:{"
             + "date:16-FEB-2017}],isActive=true]
delete User : 	users/{usersId}/delete