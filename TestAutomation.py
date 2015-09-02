import zmq
import time

# Specify the ipc/tcp port that the business logic is listening to for input
inputPort = "ipc://input"
# Specift the ipc/tcp port that the business logic will be publishing output on
outputPort = "ipc://output"
# Set up global counter variables simply to count the number of tests run and failed tests.
testCounter = 0
failCounter = 0
# Specify Test Cases here under main()

def main():
   log = open("log.txt", "w")
   print("Test Suite Started.")
   #specify test cases here, using test method with the following parameters: [integer1], [integer2], [operation], [expected]
   #to test the following equation: [integer1] [operation] [integer2] = [expected]
   ####################################################################################################################################
   #Test cases:
   test(1, 2,"+", 3)
   test(2, 2, "-", 0)
   test(1, 4, "+", 6)


   








   ####################################################################################################################################
   print("Test Suite Finished.")
   print(str(testCounter) + " tests run.")

   if failCounter < 1:
      print("All Passed.")
   else:
      print(str(failCounter) + " failed. Please see log for more details.")
   log.close()




def test(int1, int2, op, expected):
   try:
      e = int(expected)
   except ValueError:
      print("integer1 is not an integer.")
      return False
   global log
   global testCounter

   if testCounter == 0:
      log = open("log.txt", "w")
   else:
      log = open("log.txt", "a")
   
   testCounter += 1
   result = calculate(int1, int2, op)
   if int(result) == e:
      log.write("Test " + str(testCounter) + ": Passed!\n")
   else:
      global failCounter
      failCounter += 1
      log.write("Test " + str(testCounter) + ": Failed! Expected: " + str(expected) + " Actual: " + str(result) + "\n")
   log.close()
      

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

        # print('Creating Subscription Socket')
    subscriber = context.socket(zmq.SUB)
    
    # print("Connecting Socket to " + outputPort)
    subscriber.connect(outputPort)

     # print("Setting Filter")

    subscriber.setsockopt(zmq.SUBSCRIBE, '')
    
    # print("Creating Push Socket")
    sender = context.socket(zmq.PUSH)
    
    # print("Connecting to: " + inputPort)
    sender.connect(inputPort)
    

    
   
    
    # print("The expression you wish to calculate is: " + str(int1) + " " + operation + " " + str(int2) )

    s = str(int1) + " " + str(int2) + " " + operation
    # print("Sending: " + s)

    sender.send(s)


   # time.sleep(1)
    
    result = subscriber.recv_string()

    # print("Received " + result)
    return result


if __name__ == '__main__':
    main()
