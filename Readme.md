# RMI

RMI stands for Remote Method Invocation. It is a mechanism in Java that allows a program to invoke methods on objects residing in a different Java Virtual Machine (JVM), which could be running on a remote computer or a different process on the same computer.
<br>
In simple terms, RMI enables communication and interaction between different Java programs running on different machines or processes. It allows a program to call methods on objects that are not located locally, as if they were located within the same program.
<br>
Here's an example to illustrate how RMI works:

Let's say you have two computers, Computer A and Computer B. On Computer A, there is a Java program running that has an object called "Calculator" with a method called "add." On Computer B, there is another Java program running that needs to use the "add" method of the "Calculator" object.
<br>
With RMI, you can make the "add" method of the "Calculator" object available for remote invocation. Computer B can create a proxy object that represents the "Calculator" object on Computer A. When Computer B wants to use the "add" method, it calls the method on the proxy object as if it were a local method call. The RMI system takes care of transferring the method call and any necessary data to Computer A, executing the method on the actual "Calculator" object, and returning the result back to Computer B.
<br>

In summary, RMI allows Java programs to communicate and interact with remote objects as if they were local objects. It simplifies distributed computing by providing a transparent way to invoke methods on objects located in different Java Virtual Machines.
# Berkeleys Algorithm
Berkeley time synchronization is an algorithm used in distributed systems to coordinate and adjust the clocks of different nodes. The goal is to minimize clock differences and ensure a common notion of time among the nodes.

In the Berkeley algorithm, there is a master node that acts as a time reference. The algorithm proceeds as follows:

- The master node periodically sends its current time to all the slave nodes in the system.

- Each slave node receives the master's time and calculates the difference between its own clock and the received time. This difference represents the clock drift or inaccuracy of the slave node's clock.

- The slave nodes send their clock differences back to the master node.

- The master node receives the clock differences from the slave nodes and calculates the average difference.

- The master node adjusts the time of each slave node by sending them individual correction values, which are derived from the average difference.

- The slave nodes update their clocks by applying the received correction values, thereby synchronizing their clocks with the master's time.

By using this algorithm, the clock differences among the nodes can be reduced, resulting in a more consistent and synchronized notion of time within the distributed system.

Overall, Berkeley time synchronization aims to achieve clock synchronization in a distributed system by periodically exchanging time information between the master node and the slave nodes, and then adjusting the clocks based on the calculated average difference.

# Ring Algorithm
https://www.youtube.com/watch?v=PMjWOWDp9Vo
<br>
The Ring algorithm is a distributed algorithm used by a group of processes to elect a leader among themselves. Here's a simplified explanation of how the Ring algorithm works:

- Each process in the system is organized in a logical ring structure, where each process has knowledge of its neighbors.
- Initially, no process is aware of who the leader is.
- When a process decides to initiate an election, it starts by sending an election message to its neighbor in a predetermined direction.
- Upon receiving the election message, each process compares the ID of the sender with its own ID.
- If the receiving process has a lower ID, it forwards the election message to its neighbor in the same direction.
- The process continues until the election message completes a full round of the ring and returns to the initiating process.
- If a process receives the election message and has a higher ID, it sends a message called "OK" back to the sender to indicate that it is still active.
- If a process does not receive any "OK" messages after sending the election message, it assumes that it has the highest ID and becomes the leader.
- The new leader then broadcasts a "Coordinator" message to inform all other processes of its leadership.
Upon receiving the "Coordinator" message, each process acknowledges the new leader and updates its internal knowledge of the current leader.
- By following this process, the Ring algorithm ensures that the process with the highest ID in the system becomes the leader. The algorithm utilizes the logical ring structure to pass the election message and determine the leader based on the order of IDs.

# Bully Algorithm 
https://www.youtube.com/watch?v=DSiHKW3jA04
<br>
The Bully algorithm is a distributed algorithm used by a group of processes to elect a leader among themselves. Here's a simplified explanation of how the Bully algorithm works:

- Each process in the system has a unique identifier or process ID.
- When a process detects that the current leader has failed or is unreachable, it initiates an election.
- The initiating process sends an election message to all processes with higher IDs.
- Upon receiving an election message, each higher ID process checks if it is alive.
- If a higher ID process is alive, it sends an "OK" message back to the initiating process to indicate that it is still active.
- If a higher ID process does not respond within a certain timeout period, the initiating process assumes it has failed and continues the election.
- If no higher ID processes respond, the initiating process becomes the new leader and broadcasts a "Coordinator" message to inform all other processes of its leadership.
- Upon receiving the "Coordinator" message, each process acknowledges the new leader and updates its internal knowledge of the current leader.
- By following this process, the Bully algorithm ensures that the process with the highest ID in the system becomes the leader. The algorithm assumes that a process with a higher ID is more powerful and can take over leadership responsibilities.

# Token Ring based mutual exclusion

A token ring based mutual exclusion algorithm is a distributed algorithm used to achieve mutual exclusion among processes in a computer network. The algorithm operates by passing a token, a special message or signal, around a logical ring formed by the participating processes.

Here's a concise description of the algorithm:

- Each process in the network is assigned a unique identifier.
- The processes are organized in a logical ring structure, where each process has knowledge of its neighbors.
- A special token is passed around the ring in a predetermined direction.
When a process wants to enter a critical section (i.e., access a shared resource exclusively), it must possess the token.
- If a process does not possess the token, it must forward the token to its next neighbor.
- When a process with the token receives a request for the token from another process, it passes the token to the requesting process.
- The requesting process can now enter the critical section, while other processes wait for the token to be passed to them.
- Once a process is done with the critical section, it releases the token and forwards it to the next process in the ring.
- The token continues to circulate in the ring, allowing other processes to access the critical section.
- This algorithm ensures that only one process at a time can enter the critical section, and the token ensures fairness by allowing processes to request access in a predetermined order.