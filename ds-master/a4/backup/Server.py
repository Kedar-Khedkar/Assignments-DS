# Python3 program imitating a clock server

from functools import reduce
from dateutil import parser
import threading
import datetime
import socket
import time


# datastructure used to store client address and clock data
client_data = {}


''' nested thread function used to receive
	clock time from a connected client '''
def startReceivingClockTime(connector, address):
	"""
	This function continuously receives clock time from a client, calculates the time difference between
	the received time and the current time, and updates the client data with this information.
	
	:param connector: The connector parameter is likely a socket object that is used to establish a
	connection between the server and the client. It is used to send and receive data between the two
	endpoints
	:param address: The `address` parameter is the address of the client that is connected to the server
	through the `connector` object. It is used as a key to store the client's clock time and time
	difference in the `client_data` dictionary
	"""

	while True:
		# receive clock time
		clock_time_string = connector.recv(1024).decode()
		clock_time = parser.parse(clock_time_string)
		clock_time_diff = datetime.datetime.now() - \
												clock_time

		client_data[address] = {
					"clock_time"	 : clock_time,
					"time_difference" : clock_time_diff,
					"connector"	 : connector
					}

		print("Client Data updated with: "+ str(address),
											end = "\n\n")
		time.sleep(5)


''' master thread function used to open portal for
	accepting clients over given port '''
def startConnecting(master_server):
	"""
	The function starts a server to accept connections from slave clients and fetches their clock time.
	
	:param master_server: It is a socket object representing the master server that is listening for
	incoming connections from slave clients
	"""
	
	# fetch clock time at slaves / clients
	while True:
		# accepting a client / slave clock client
		master_slave_connector, addr = master_server.accept()
		slave_address = str(addr[0]) + ":" + str(addr[1])

		print(slave_address + " got connected successfully")

		current_thread = threading.Thread(
						target = startReceivingClockTime,
						args = (master_slave_connector,
										slave_address, ))
		current_thread.start()


# subroutine function used to fetch average clock difference
def getAverageClockDiff():
	"""
	This function calculates the average clock difference between multiple clients.
	:return: the average clock difference of all the clients in the `client_data` dictionary.
	"""

	current_client_data = client_data.copy()

	time_difference_list = list(client['time_difference']
								for client_addr, client
									in client_data.items())
									

	sum_of_clock_difference = sum(time_difference_list, \
								datetime.timedelta(0, 0))

	average_clock_difference = sum_of_clock_difference \
										/ len(client_data)

	return average_clock_difference


''' master sync thread function used to generate
	cycles of clock synchronization in the network '''
def synchronizeAllClocks():
	"""
	The function synchronizes the clocks of multiple clients by calculating the average clock difference
	and sending the synchronized time to each client.
	"""

	while True:

		print("New synchronization cycle started.")
		print("Number of clients to be synchronized: " + \
									str(len(client_data)))

		if len(client_data) > 0:

			average_clock_difference = getAverageClockDiff()

			for client_addr, client in client_data.items():
				try:
					synchronized_time = \
						datetime.datetime.now() + \
									average_clock_difference

					client['connector'].send(str(
							synchronized_time).encode())

				except Exception as e:
					print("Something went wrong while " + \
						"sending synchronized time " + \
						"through " + str(client_addr))

		else :
			print("No client data." + \
						" Synchronization not applicable.")

		print("\n\n")

		time.sleep(5)


# function used to initiate the Clock Server / Master Node
def initiateClockServer(port = 8080):
	"""
	This function initiates a clock server by creating a socket, listening to requests, making
	connections, and starting synchronization.
	
	:param port: The port number on which the clock server will listen for incoming connections. By
	default, it is set to 8080 if no port number is specified, defaults to 8080 (optional)
	"""

	master_server = socket.socket()
	master_server.setsockopt(socket.SOL_SOCKET,
								socket.SO_REUSEADDR, 1)

	print("Socket at master node created successfully\n")
	
	master_server.bind(('', port))

	# Start listening to requests
	master_server.listen(10)
	print("Clock server started...\n")

	# start making connections
	print("Starting to make connections...\n")
	master_thread = threading.Thread(
						target = startConnecting,
						args = (master_server, ))
	master_thread.start()

	# start synchronization
	print("Starting synchronization parallelly...\n")
	sync_thread = threading.Thread(
						target = synchronizeAllClocks,
						args = ())
	sync_thread.start()



# Driver function
if __name__ == '__main__':

	# Trigger the Clock Server
	initiateClockServer(port = 8080)
