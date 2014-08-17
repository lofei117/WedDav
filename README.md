# About
This is a simple webdav library for android. Absolutely it is not a independent webdav library, I just design the interfaces to use it simply. 

This library relies on [JackRabbit](http://jackrabbit.apache.org/ "JackRabbit") project. Owing to JackRabbit standalone is too large and complex to use, I used a third part library of JackRabbit, [Link here](http://ashimita.blogspot.com/2011/05/using-jackrabbit-as-webdav-client-for.html "JackRabbit for Android").

# Note
* This project is not a realization of WebDav, just a simple encapsulation for using it.
* The JackRabbit library in this project is not intergrated with dependencies like commons-httpclient.
* You may implement these interfaces using any webdav library as you like, and also, you can remove the JackRabbit relization.
* I wrote this because I can change the webdav library easily in my other projects without modify their code. 


