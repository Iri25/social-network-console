# social-network-console
Java application with a 4-layered architecture: data access layer ([domain package](https://github.com/Iri25/social-network/tree/main/SocialNetwork/src/main/java/socialnetwork/domain)), persistence layer ([repository package](https://github.com/Iri25/social-network/tree/main/SocialNetwork/src/main/java/socialnetwork/repository)), business layer ([service package](https://github.com/Iri25/social-network/tree/main/SocialNetwork/src/main/java/socialnetwork/service)) and presentation layer ([userinterface package](https://github.com/Iri25/social-network/tree/main/SocialNetwork/src/main/java/socialnetwork/userinterface)). The data are saved in csv files ([data package](https://github.com/Iri25/social-network/tree/main/SocialNetwork/data)). The strategy model was used to validate the data and classes were defined to treat the exepects ([validators package](https://github.com/Iri25/social-network/tree/main/SocialNetwork/src/main/java/socialnetwork/domain/validators)). The user interface is console type.

Key concepts are abstraction, encapsulation, inheritance, polymorphism, validations, exceptions, reading from files and storing in files.

Application for a social network with user interaction from the console which supports operations such as:
1. Add user
2. Remove user
3. Add friend
4. Revove friend
5. Display number of communities (the number of connected components in the network graph)
6. Display the most sociable community (the connected component in the network with the longest path)
7. Display all friendships of a user read from the keyboard
8. Display all the friendships of a user, created in a certain month of the year, the user and the month of the year are read from the keyboard
9. Display (chronologically) the conversations of two users, read from the keyboard
10. Simulate sending a friendship invitation and adding the relationship only if the invited user accepts it. The status of the requests is pending, approved or rejected.
