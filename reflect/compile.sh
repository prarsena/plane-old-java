# Compile Person.java to bin directory
if [ ! -d "bin" ]; then
  mkdir bin
fi
javac -d bin src/Person.java

# Run WhooAreU from bin directory
java -cp "bin" WhooAreU
