# Custom_Classloader

> A custom class loader allows you to control how Java classes are loaded in the JVM based on your application specific needs. 

> ClassLoading is the JRE framework that ***allows user defined mechanisms to locate and load java classes***. To take advantage of this feature a ClassLoader must be constructed by the user.

 

ClassLoading is one of those features within java where it is easy to do simple things and notoriously difficult to do and understand harder things. Part of the reason why ClassLoading is difficult to understand is that it is dealing with many different issues:

 

* Locating, defining and validating classes
* Assigning security permissions to the loaded classes
* Allowing multiple versions of a class to be loaded
* Controlling the visibility of one class (version) from another
* Serving as a contextual object on each thread (the "TCL")

## Application-
> [Tomcat](http://tomcat.apache.org/tomcat-6.0-doc/class-loader-howto.html)
When a request to load a class from the web application's WebappX class loader is processed, this class loader will look in the local repositories first, instead of delegating before looking. 
![](https://i.ibb.co/80q97vc/Annotation-2019-09-11-225805.png)

***Extention class loader provided by JVM is sun.misc.Launcher$ExtClassLoader***

![](https://i.ibb.co/X4tSzVJ/Annotation-2019-09-11-230332.png)
***For each new webapplication X a new WebAppX(where X=1,2,3..) classloader is created. This is done so that X application's classes are loaded by its own WebAppX classloader from X directory only.***

![](https://i.ibb.co/dbPthK9/Annotation-2019-09-11-230441.png)

> [JBoss classloader](https://developer.jboss.org/wiki/JBossClassLoaderOverviewAndGoals)

