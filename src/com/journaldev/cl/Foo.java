
package com.journaldev.cl;

public class Foo {
    static public void main(String args[]) throws Exception {
        System.out.println("Foo Constructor >>> " + args[0] + " " + args[1]);


        //This to show you that
        //-from foo class we are loading bar class also- so bar class will also be loaded by same custom class loader
        Bar bar = new Bar(args[0], args[1]);
        bar.printCL();

        System.out.println("INTEGER loaded by= "+Integer.class.getClassLoader());
        /**
         * Loading Class 'java.lang.Integer'
         * **Going to parent class loader***
         * INTEGER loaded by= null
         */
    }

    public static void printCL() {
        System.out.println("Foo ClassLoader: "+Foo.class.getClassLoader());
    }
}
/***
 * before that compile foo and bar then compile CRun
"C:\Program Files\Java\jdk-12.0.2\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\lib\idea_rt.jar=62514:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\hs140\IdeaProjects\CCL\out\production\CCL;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\plugins\Kotlin\kotlinc\lib\kotlin-stdlib.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\plugins\Kotlin\kotlinc\lib\kotlin-reflect.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\plugins\Kotlin\kotlinc\lib\kotlin-test.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\plugins\Kotlin\kotlinc\lib\kotlin-stdlib-jdk7.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.3\plugins\Kotlin\kotlinc\lib\kotlin-stdlib-jdk8.jar" CCRun com.journaldev.cl.Foo 1212 1313
        Loading Class 'com.journaldev.cl.Foo'
        Loading Class using CCLoader
        Loading Class 'java.lang.Object'
        Loading Class 'java.lang.String'
        Loading Class 'java.lang.Exception'
        Loading Class 'java.lang.System'
        Loading Class 'java.lang.invoke.StringConcatFactory'
        Loading Class 'java.io.PrintStream'
        Foo Constructor >>> 1212 1313
        Loading Class 'com.journaldev.cl.Bar'
        Loading Class using CCLoader
        Bar Constructor >>> 1212 1313
        Loading Class 'java.lang.Class'
        Bar ClassLoader: CCLoader@7291c18f
        Foo ClassLoader: CCLoader@7291c18f

        Process finished with exit code 0
**/

//After changing location of bar class to ext
/**Error:(11, 9) java: cannot find symbol
        symbol:   class Bar
  location: class com.journaldev.cl.Foo
 **/