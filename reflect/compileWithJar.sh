# Compile ExploreJar.java with aspectjrt-1.9.24.jar, outputting to bin directory
if [ ! -d "bin" ]; then
  mkdir bin
fi
javac -d bin -cp "bin/aspectjrt-1.9.24.jar" src/ExploreJar.java

# Run ExploreJar with aspectjrt-1.9.24.jar and bin directory in classpath
java -cp "bin:bin/aspectjrt-1.9.24.jar" ExploreJar
