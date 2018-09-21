# Operations to manipulate this repository.
# Written by Tiger Sachse.

LIB_DIR="libs"
DIST_DIR="dist"
DOCS_DIR="docs"
DATA_DIR="data"
BUILD_DIR="build"
SOURCE_DIR="source"
MAIN_CLASS="Escala"
SCRIPT_DIR="scripts"
DERBY_LOG="derby.log"
DERBY_JAR="derby.jar"
PACKAGE_NAME="escala"
RUN_UNIX_SCRIPT="run.sh"
RUN_WINDOWS_SCRIPT="run.bat"
EVENT_SCRIPT="add_events.sql"

# Build the project.
function build_project {
    rm -rf $BUILD_DIR
    mkdir $BUILD_DIR

    cp -r $LIB_DIR $BUILD_DIR
    cp -r $DATA_DIR $BUILD_DIR

    javac -cp $BUILD_DIR/$LIB_DIR/$DERBY_JAR -d $BUILD_DIR $SOURCE_DIR/graphics/*.java \
        $SOURCE_DIR/*.java
}

# Run the project and clean up afterwards.
function run_project {
    build_project &&
    cd $BUILD_DIR
    java -cp .:$LIB_DIR/$DERBY_JAR $PACKAGE_NAME.$MAIN_CLASS
    cd ..
    rm -rf $BUILD_DIR
}

# Package the project into an archive and clean up afterwards.
function package_project {

    # Build the project and copy extras into the build directory.
    build_project
    cp -r $DOCS_DIR $BUILD_DIR
    cp -r $SOURCE_DIR $BUILD_DIR

    # Create executable files for the package for Windows and Unix.
    echo "java -cp .:$LIB_DIR/$DERBY_JAR $PACKAGE_NAME.$MAIN_CLASS" > \
        $BUILD_DIR/$RUN_UNIX_SCRIPT
    echo "@echo off" > $BUILD_DIR/$RUN_WINDOWS_SCRIPT
    echo "java -cp .;$LIB_DIR/$DERBY_JAR $PACKAGE_NAME.$MAIN_CLASS" >> \
        $BUILD_DIR/$RUN_WINDOWS_SCRIPT

    chmod +x $BUILD_DIR/$RUN_UNIX_SCRIPT
    chmod +x $BUILD_DIR/$RUN_WINDOWS_SCRIPT

    # Prepare the distribution space. 
    mkdir -p $DIST_DIR
    rm -f $DIST_DIR/$NAME.zip

    # Zip everything in the build directory.
    cd $BUILD_DIR
    zip -r ../$DIST_DIR/$PACKAGE_NAME.zip *
    cd ..

    # Clean up after yourself!
    rm -rf $BUILD_DIR
}

# Run an SQL script located in the script directory.
function run_sql {
    cd $LIB_DIR
    java -jar derbyrun.jar ij ../$1
    rm $DERBY_LOG
}

# Run an interactive prompt for the database.
function run_ij {
    cd $LIB_DIR
    java -jar derbyrun.jar ij
    rm $DERBY_LOG
}

function load_events {
    python3 $SCRIPT_DIR/convert_events.py data/events/*
    mv $EVENT_SCRIPT $SCRIPT_DIR/$EVENT_SCRIPT
    run_sql $SCRIPT_DIR/$EVENT_SCRIPT
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
    "--sql")
        run_sql $2
        ;;
    "--ij")
        run_ij
        ;;
    "--load-events")
        load_events
        ;;
esac
