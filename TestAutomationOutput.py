import zmq

print('Output Test Automation Output Started...')

connect_address = "ipc://output"

context = zmq.Context()

#print ('Creating Socket')
socket = context.socket(zmq.SUB)
#print ('Socket Created')

#print ("Connecting Socket to " + connect_address)
socket.connect(connect_address)
#print ('Bind Success')

#print ("Setting Filter")
socket.setsockopt(zmq.SUBSCRIBE, '')
#print ("Filter Set")

while True:
  #  print("Waiting for Result from Business Logic")
    result = socket.recv_string()
   # print("Result Received")
    print (result)
    


