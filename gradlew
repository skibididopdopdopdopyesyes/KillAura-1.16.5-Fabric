#!/bin/sh
wrapperJar="gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$wrapperJar" ]; then
    echo "Downloading Gradle Wrapper JAR..."
    curl -L -o "$wrapperJar" "https://raw.githubusercontent.com/gradle/gradle/v7.6.0/gradle/wrapper/gradle-wrapper.jar"
fi
exec java -jar "$wrapperJar" "$@"
