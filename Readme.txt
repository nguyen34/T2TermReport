ReadMe

Instructions to run Java Calculator Automation:

Pre-conditions:
Required Packages to Install:
ZeroMQ: http://zeromq.org/area:download
libzmq: ZeroMQ Core Engine: http://zeromq.org/docs:source-git
pyzmq: Python Bindings for ZeroMQ: http://zeromq.org/bindings:python
jzmq: Java Bindings for ZeroMQ: http://zeromq.org/bindings:java

Note: Installation instructions provided in the links


How to build:

1.  cd into the src folder of the project (i.e. In the command line, run ‘cd /Where/Project/Is/src’)
2. Run the following commands to build each component of the calculator:

javac -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. businessLogic/BusinessLogic.java
javac -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. input/InputUI.java
javac -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. output/OutputUI.java
javac control/JavaExecutor.java






How to run Java Calculator:

1. cd into the src folder of the project (i.e. In the command line, run ‘cd /Where/Project/Is/src’)
2. run JavaExecutor.class. 
Command: java control/JavaExecutor

Alternatively, if you wish to run the InputUI, BusinessLogic and OutputUI separately, follow these instructions.

1. Set LD_LIBRARY_PATH environment variable to the location the jzmq library is installed (Should be /usr/local/lib/ by default if installed on Linux)
Command: export LD_LIBRARY_PATH=/usr/local/lib
2. Set JAVA_LIBRARY_PATH to location the jzmq library is installed, like in step 1.
Command: export JAVA_LIBRARY_PATH = /usr/local/lib
3. cd into the src folder of the project (i.e. In the command line, run ‘cd /Where/Project/Is/src’)
4. run any of the BusinessLogic.class, InputUI.class or OutputUI.class of your choosing:
Commands:
BusinessLogic: java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. businessLogic/BusinessLogic
InputUI: java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. input/InputUI
OutputUI: javac -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. output/OutputUI

Note: Just running the JavaExecutor class is enough as it will set the environment variables and run all three components for you. However, each component still needs to be built beforehand before it can be run.




How to run Python Test Automation:
Using IDLE:
1. Open TestAutomation.py
2. Select ‘Run Module’ under ‘Run’. The TestAutomation will start up the BusinessLogic (expect 0.5 second delay) and run test cases written in the test script.
3. How to formulate the test cases are commented in the Python Script.