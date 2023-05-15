|-Server
|---ServerInterface.java
|---ServerImplementation.java
|---ServerMain.java
|-Client
|---ClientInterface.java
|---ClientImplementation.java
|---ClientMain.java

## First compile all the code in Client and Server folder
```
javac Client/*.java  && javac Server/*.java  
```

### Open one terminal and Run:

```  
rmiregistry 5000
```  

### Open another terminal and Run:

```
java Server.ServerMain 
```


### Open another terminal and Run:
```
java Client.ClientMain  
```


#### With no input from user change ClientMain and compile 
```
  try {
            ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost/Server");
            ClientInterface client = new ClientImplementation();
            server.registerClient(client);

            server.broadcastMessage("Hello from client!");
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
```