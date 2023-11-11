# commenter-app-task
This is a simple service api in which user can comment to other user and see who commented him

# add comment
- This API will add a comment. eg. A comment from user A to user B.
  
- http://localhost:8080/comment/addComment
  
- payload
  {
    "commentFrom" : "Rahul"
    "commentTo" : "Tom",
    "comment" : "I am good java spring boot developer"
  }

# get comment
- This API will get/show the comment. The API will show all the comments that are 
available for a user using the app. Eg. User B is using the app and wants to see what comments has 
s/he received.

- http://localhost:8080/comment/getComment?commentTo=rahul

