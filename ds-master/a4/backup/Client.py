# Python3 program imitating a client process

from timeit import default_timer as timer
from dateutil import parser
import threading
import datetime
import socket
import time


# client thread function used to send time at client side
def startSendingTime(slave_client):
	"""
	The function sends the current time to a server every 5 seconds using a client socket.
	
	:param slave_client: The parameter `slave_client` is likely a socket object representing a client
	that is connected to a server. The function `startSendingTime` sends the current time from the
	client to the server every 5 seconds using the `send` method of the socket object
	"""

	while True:
		# provide server with clock time at the client
		slave_client.send(str(
					datetime.datetime.now()).encode())

		print("Recent time sent successfully",
										end = "\n\n")
		time.sleep(5)


# client thread function used to receive synchronized time
def startReceivingTime(slave_client):
	"""
	This function receives synchronized time data from a server and prints it to the console.
	
	:param slave_client: The parameter `slave_client` is likely a socket object representing the
	client-side connection to a server. The function `startReceivingTime` receives data from the server
	through this socket and prints the received data to the console
	"""

	while True:
		# receive data from the server
		Synchronized_time = parser.parse(
						slave_client.recv(1024).decode())

		print("Synchronized time at the client is: " + \
									str(Synchronized_time),
									end = "\n\n")


# function used to Synchronize client process time
def initiateSlaveClient(port = 8080):
	"""
	The function initiates a socket connection to a clock server and starts two threads for sending and
	receiving time from the server.
	
	:param port: The port number on which the slave client will connect to the clock server. By default,
	it is set to 8080, defaults to 8080 (optional)
	"""

	slave_client = socket.socket()		
	
	# connect to the clock server on local computer
	slave_client.connect(('127.0.0.1', port))

	# start sending time to server
	print("Starting to receive time from server\n")
	send_time_thread = threading.Thread(
					target = startSendingTime,
					args = (slave_client, ))
	send_time_thread.start()


	# start receiving synchronized from server
	print("Starting to receiving " + \
						"synchronized time from server\n")
	receive_time_thread = threading.Thread(
					target = startReceivingTime,
					args = (slave_client, ))
	receive_time_thread.start()


# Driver function
if __name__ == '__main__':

	# initialize the Slave / Client
	initiateSlaveClient(port = 8080)
