# Compile Person.java to bin directory
if [ ! -d "bin" ]; then
  mkdir bin
fi

javac -d bin objects/Person.java objects/Inspectigator.java objects/WhooAreU.java

# Run WhooAreU from bin directory
java -cp "bin" WhooAreU
