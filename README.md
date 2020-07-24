# Campus Path Server
a simple server built with [Java Spark Framework](http://sparkjava.com/).
  
accepts 3 kinds of GET requests and send response back in JSON.
| Routes | | 
| --- | --- |
| /buildingName | returns a map of building short names to long names |
| /location | retuns a map of building short names to the corresponding points |
| /:routes | returns the shortest path between building1-building2
