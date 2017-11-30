# 4Year Distributed Systems  - Asynchronous RMI Dictionary Service

# DESCRIPTION

Multi-threaded file server and logging application that allows a client application to download files using a set of options presented in a terminal user
interface:

The Package (oop.jar) contains a Server and Client.



# INSTRUCTIONS FOR USE:

## CEATING JAR FILE

You can create the JAR file using Ant or with the following command from inside the "bin" folder of the Eclipse project:

```
jar –cf oop.jar *
```


## STARTING THE SERVER:

```
java -cp oop.jar ie.gmit.sw.server.Server 7777 /serverFiles
```
Can pass arguments:
* Port number (7777).
* Path file location (/serverFiles).


## STARTING THE CLIENT:

```
java -cp oop.jar ie.gmit.sw.client.Client
```

No arguments, default port in 7777.

To run Client, the config.xml file must be present.

### config.xml

```
<client-config username="gmit-sw2016">
	<server-host>127.0.0.1</server-host>
	<server-port>7777</server-port>
	<download-dir>C:/Users/User/Downloads/</download-dir>
</client-config>
```

## WORKING OF THE SERVER:
=========================

Every New Packet received by the server, along with information of the corresponding client, is stored in a data structure 'Request' and directly passed onto a new thread and continues to listen on the port (7777).

The Server keeps all information about the Client's actions in a log.txt file

```
log.txt
------------------------------------------------------
[INFO] Connecting requested by 127.0.0.1 at 10:57 AM on 08 January 2017
[ERROR] Connection reset requested by 127.0.0.1 at 11:02 AM on 08 January 2017
[INFO] Connecting requested by 127.0.0.1 at 11:02 AM on 08 January 2017
[INFO] Listing files requested by 127.0.0.1 at 11:02 AM on 08 January 2017
[INFO] End connection requested by 127.0.0.1 at 11:02 AM on 08 January 2017
[INFO] Connecting requested by 127.0.0.1 at 11:05 AM on 08 January 2017
[INFO] Listing files requested by 127.0.0.1 at 11:05 AM on 08 January 2017
[INFO] alex.txt requested by 127.0.0.1 at 11:05 AM on 08 January 2017
[WARNING] file /serverFiles/alex.txt does not exist requested by 127.0.0.1 at 11:05 AM on 08 January 2017
[ERROR] Connection reset requested by 127.0.0.1 at 11:05 AM on 08 January 2017
```



## WORKING OF THE CLIENT:

Client sends a request to the server on the port 7777 with the specified option.

```
Menu Client Side
---------------------------
1. Connect to Server
2. Print File Listing
3. Download File
4. Quit

Type Option [1-4]



OP 1 - Create a new connection to the server.
OP 2 - List the file list available for download.
OP 3 - Introduction of file name to download.
OP 4 - Terminates the connection to the server.
```

