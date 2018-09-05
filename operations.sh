# Operations to manipulate this repository.
# Written by Tiger Sachse.

DOCS="docs"
LIB="libraries"
SOURCE="source"
BUILD="bytecode"
PACKAGE="escala"
MAIN_CLASS="Escala"
DIST="distribution"
DERBY_LOG="derby.log"
MANIFEST="manifest.txt"

# Build the project.
function build_project {
    rm -rf $BUILD
    javac -cp $LIB/*.jar -d $BUILD $SOURCE/graphics/*.java $SOURCE/database/*.java $SOURCE/*.java
}

# Run the project and clean up afterwards.
function run_project {
    build_project &&
    java -cp $BUILD:$LIB/* $PACKAGE.$MAIN_CLASS &&
    rm -f $DERBY_LOG
    rm -rf $BUILD
}

# Package the project into a jar and clean up afterwards.
function package_project {
    build_project

    # Prepare the distribution space. 
    mkdir -p $DIST
    rm -f $DIST/$NAME.jar

    # Create a manifest.
    echo "Main-Class: $PACKAGE.$MAIN_CLASS" > $MANIFEST
    echo "Class-Path: $LIB/derby.jar" >> $MANIFEST

    # Compress everything into a jar.
    cd $BUILD
    jar cvfm ../$PACKAGE.jar ../$MANIFEST $PACKAGE/*
    cd ..

    # Zip the jar with its libraries and assets.
    zip -r $DIST/$PACKAGE.zip $PACKAGE.jar $SOURCE/ $DOCS/ $LIB/

    # Clean up after yourself!
    rm $MANIFEST $PACKAGE.jar
    rm -rf $BUILD
}

# Main entry point of this script.
case "$1" in
    "--build")
        build_project
        ;;
    "--run")
        run_project
        ;;
    "--pack")
        package_project
        ;;
esac
