import zmq
import time

# Specify the ipc/tcp port that the business logic is listening to for input
inputPort = "ipc://input"
# Specift the ipc/tcp port that the business logic will be publishing output on
outputPort = "ipc://output"
# Specify Test Cases here

def main():
    
   result = calculate("123" , 13, '+')
   print(result)
   result = calculate("123" , 13, '-')
   print(result)
   result = calculate("100", 10, '/')
   print(result)
   result = calculate("123" , 13, '*')
   print(result)










# calculate method accepts three parameters, an [integer1], an [integer2] and an [operation] sign
# and construct an equation in the following format: [integer 1] [operation sign] [integer 2] (e.g. 1234 + 12)


    



def calculate (integer1, integer2, operation):
    try:
        int1 = int(integer1)
    except ValueError:
        print("integer1 is not an integer.")
        return False

    try:
        int2 = int(integer2)
    except ValueError:
        print("integer2 is not an integer.")
        return False

    if operation != '+' and operation != '-' and operation != '*' and operation != '/':
        print ("'" + operation + "' is not a valid operation sign")
        return False



    
    context = zmq.Context()
    
    # print("Creating Push Socket")
    sender = context.socket(zmq.PUSH)
    
    # print("Connecting to: " + inputPort)
    sender.connect(inputPort)
    
    # print('Creating Subscription Socket')
    subscriber = context.socket(zmq.SUB)
    
    # print("Connecting Socket to " + outputPort)
    subscriber.connect(outputPort)
    
    # print("Setting Filter")

    subscriber.setsockopt(zmq.SUBSCRIBE, '')
    
    # print("The expression you wish to calculate is: " + str(int1) + " " + operation + " " + str(int2) )

    s = str(int1) + " " + str(int2) + " " + operation
    # print("Sending: " + s)

    sender.send(s)
    time.pause(1)
    result = subscriber.recv_string()

    # print("Received " + result)
    return result


if __name__ == '__main__':
    main()
