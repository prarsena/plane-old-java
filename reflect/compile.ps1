param(
    [string]$Action 
)

if (!(Test-Path -Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

Write-Host "Cleaning existing .class files in bin directory..."
Remove-Item -Path "bin\*.class" -ErrorAction SilentlyContinue # Cleans only .class files


if ($Action -eq "jar") {
    Write-Host "Executing 'jar' action: Compiling and Running ExploreJar with AspectJ..."

    Set-Location objects
    javac -d ../bin ExploreJar.java Person.java

    Set-Location ..
    java -cp "bin;bin/aspectjrt-1.9.24.jar" objects/ExploreJar.java
    
} else {
    Write-Host "Executing default action: Compiling and Running WhooAreU with Person.."
    
    Set-Location objects
    javac -d ../bin Person.java

    Set-Location ..
    java -cp "bin;bin/objects" objects/WhooAreU.java
}

Write-Host "Script finished."