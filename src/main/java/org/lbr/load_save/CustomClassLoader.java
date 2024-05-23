//package org.lbr.load_save;
//
//public class CustomClassLoader {
//    public class CustomClassLoader extends ClassLoader {
//
//        @Override
//        public Class findClass(String name) throws ClassNotFoundException {
//            byte[] b = loadClassFromFile(name);
//            return defineClass(name, b, 0, b.length);
//        }
//
//        private byte[] loadClassFromFile(String fileName) {
//            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
//                    fileName.replace('.', File.separatorChar) + ".class");
//            byte[] buffer;
//            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//            int nextValue = 0;
//            try {
//                while ((nextValue = inputStream.read()) != -1) {
//                    byteStream.write(nextValue);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            buffer = byteStream.toByteArray();
//            return buffer;
//        }
//    }
//
//    protected Class<?> loadClass(String name, boolean resolve)
//            throws ClassNotFoundException {
//
//        synchronized (getClassLoadingLock(name)) {
//            // First, check if the class has already been loaded
//            Class<?> c = findLoadedClass(name);
//            if (c == null) {
//                long t0 = System.nanoTime();
//                try {
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else {
//                        c = findBootstrapClassOrNull(name);
//                    }
//                } catch (ClassNotFoundException e) {
//                    // ClassNotFoundException thrown if class not found
//                    // from the non-null parent class loader
//                }
//
//                if (c == null) {
//                    // If still not found, then invoke findClass in order
//                    // to find the class.
//                    c = findClass(name);
//                }
//            }
//            if (resolve) {
//                resolveClass(c);
//            }
//            return c;
//        }
//    }
//
//    protected final Class<?> defineClass(
//            String name, byte[] b, int off, int len) throws ClassFormatError
//    }
//
//    protected Class<?> findClass(
//            String name) throws ClassNotFoundException
//        }
//}
