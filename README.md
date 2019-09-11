# Custom_Classloader

A custom class loader allows you to control how Java classes are loaded in the JVM based on your application specific needs. 

Application-
> [Tomcat](http://tomcat.apache.org/tomcat-6.0-doc/class-loader-howto.html)
When a request to load a class from the web application's WebappX class loader is processed, this class loader will look in the local repositories first, instead of delegating before looking. 
![](https://i.ibb.co/80q97vc/Annotation-2019-09-11-225805.png)

***Extention class loader provided by JVM is sun.misc.Launcher$ExtClassLoader***

![](https://i.ibb.co/X4tSzVJ/Annotation-2019-09-11-230332.png)
***For each new webapplication X a new WebAppX(where X=1,2,3..) classloader is created. This is done so that X application's classes are loaded by its own WebAppX classloader from X directory only.***

![](https://i.ibb.co/dbPthK9/Annotation-2019-09-11-230441.png)

> [JBoss classloader](developer.jboss.org/wiki/JBossClassLoaderHistory?_sscc=t)

