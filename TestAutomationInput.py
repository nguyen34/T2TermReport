import zmq


int1 = False;
int2 = False;
operation = False;
connectAddress = "ipc://input"


print("Starting Python Test Automation: Input Stub")
print("This input component will ask for three inputs from the users, an [integer 1], [integer 2] and an [operation sign] and construct an equation in the following format:")
print("[integer 1] [operation sign] [integer 2] (e.g. 1234 + 12)")



def checkInt(x):
    try:
        integer = int(x)
    except ValueError:
        print("This is not an integer.")
        return False
    else:
        return integer

while int1 == False:
    print("First, what is [Integer 1]?")
    userInput = raw_input()
    int1 = checkInt(userInput)

while int2 == False:
    print("Next, what is [Integer 2]?")
    userInput = raw_input()
    int2 = checkInt(userInput)

while operation == False:
    print("Finally, what is the [Operation Sign]? Choose from: +,-,* or /")
    userInput = raw_input()
    if userInput == '+' or userInput == '-' or userInput == '*' or userInput == '/':
        operation = userInput
    else:
        print("Input is not a valid option")


print("The expression you wish to calculate is: " + str(int1) + " " + operation + " " + str(int2))
context = zmq.Context()

print("Creating Socket")
sender = context.socket(zmq.PUSH)

print("Connecting to: " + connectAddress)
sender.connect(connectAddress)

s = str(int1) + " " + str(int2) + " " + operation

print("Sending: " + s)
sender.send(s)
