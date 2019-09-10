
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Our Custom ClassLoader to load the classes. Any class in the com.journaldev
 * package will be loaded using this ClassLoader. For other classes, it will
 * delegate the request to its Parent ClassLoader.
 *
 */
public class CCLoader extends ClassLoader {

    /**
     * This constructor is used to set the parent ClassLoader
     */
    public CCLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Loads the class from the file system. The class file should be located in
     * the file system. The name should be relative to get the file location
     *
     * @param name
     *            Fully Classified name of the class, for example, com.journaldev.Foo
     */
    private Class getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] b = null;
        try {
            // This loads the byte code data from the file
            b = loadClassFileData(file);
            // defineClass is inherited from the ClassLoader class
            // that converts byte array into a Class. defineClass is Final
            // so we cannot override it
            Class c = defineClass(name, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Every request for a class passes through this method. If the class is in
     * com.journaldev package, we will use this classloader or else delegate the
     * request to parent classloader.
     *
     *
     * @param name
     *            Full class name
     */
    @Override
    public Class loadClass(String name) throws ClassNotFoundException
    {
        System.out.println("Loading Class '" + name + "'");

        if (name.startsWith("com.journaldev")) {
            System.out.println("=>Loading Class using CCLoader");
            return getClass(name);
        }
        System.out.println("**Going to parent class loader to load="+name+"\n");
        return super.loadClass(name);
    }

    /**
     * Reads the file (.class) into a byte array. The file should be
     * accessible as a resource and make sure that it's not in Classpath to avoid
     * any confusion.
     *
     * @param name
     *            File name
     * @return Byte array read from the file
     * @throws IOException
     *             if any exception comes in reading the file
     */
    private byte[] loadClassFileData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(
                name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
/**
 Loading Class 'com.journaldev.cl.Foo'
 =>Loading Class using CCLoader
 Loading Class 'java.lang.Object'
 **Going to parent class loader to load=java.lang.Object THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) OR EXT CL

 Loading Class 'java.lang.String'
 **Going to parent class loader to load=java.lang.String THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) OR EXT CL

 Loading Class 'java.lang.Exception'
 **Going to parent class loader to load=java.lang.Exception THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) OR EXT CL

 Loading Class 'java.lang.System'
 **Going to parent class loader to load=java.lang.System THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) OR EXT CL

 Loading Class 'java.lang.invoke.StringConcatFactory'
 **Going to parent class loader to load=java.lang.invoke.StringConcatFactory THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) OR EXT CL

 Loading Class 'java.io.PrintStream'
 **Going to parent class loader to load=java.io.PrintStream THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) OR EXT CL

 Foo Constructor >>> 1212 1313

 // AS soon as instantiation by new class as seen(Bar bar) then that will be seen immediate loadclass() ie loadclass of CCLoader class ..if Bar found by immediate then load else delegate to parent
 Loading Class 'com.journaldev.cl.Bar'
 =>Loading Class using CCLoader
 Bar Constructor >>> 1212 1313
 Loading Class 'java.lang.Class'
 **Going to parent class loader to load=java.lang.Class

 Bar ClassLoader: CCLoader@7291c18f
 Loading Class 'java.lang.Integer'
 **Going to parent class loader to load=java.lang.Integer

 INTEGER loaded by= null THIS LOADED BY BOOTSTRAP CL(GRANDPARENT) and NOT by EXT CL as null was shown.. null means its was loaded by boostrap cl(grandparent)
 Foo ClassLoader: CCLoader@7291c18f
 */