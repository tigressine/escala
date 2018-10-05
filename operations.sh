# Operations to manipulate this repository.
# Written by Tiger Sachse.

LIB_DIR="libs"
DIST_DIR="dist"
DOCS_DIR="docs"
DATA_DIR="data"
TEST_DIR="tests"
BUILD_DIR="build"
SOURCE_DIR="source"
MAIN_CLASS="Escala"
SCRIPT_DIR="scripts"
DERBY_LOG="derby.log"
DERBY_JAR="derby.jar"
PACKAGE_NAME="escala"
GRAPHICS_DIR="graphics"
RUN_UNIX_SCRIPT="run.sh"
JUNIT_JAR="junit-4.10.jar"
RUN_WINDOWS_SCRIPT="run.bat"
DERBY_RUN_JAR="derbyrun.jar"
EVENT_SCRIPT="add_events.sql"
SKILL_SCRIPT="add_skills.sql"

# Build the project.
function build_project {
    rm -rf $BUILD_DIR
    mkdir $BUILD_DIR

    cp -r $LIB_DIR $BUILD_DIR
    cp -r $DATA_DIR $BUILD_DIR

    javac -cp $BUILD_DIR/$LIB_DIR/$DERBY_JAR \
        -d $BUILD_DIR $SOURCE_DIR/$GRAPHICS_DIR/*.java \
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

# Run unit tests for this project.
function test_project {
    build_project
    javac -cp $BUILD_DIR:$BUILD_DIR/$LIB_DIR/$JUNIT_JAR \
        -d $BUILD_DIR $TEST_DIR/*.java
    cd $BUILD_DIR
    java -cp .:$LIB_DIR/$JUNIT_JAR:$LIB_DIR/$DERBY_JAR \
        org.junit.runner.JUnitCore \
        $TEST_DIR.DatabaseTester
    cd ..
    rm -rf $BUILD_DIR
}

# Run an SQL script located in the script directory.
function run_sql {
    cd $LIB_DIR
    java -jar $DERBY_RUN_JAR ij ../$1
    rm $DERBY_LOG
    cd ..
}

# Run an interactive prompt for the database.
function run_ij {
    cd $LIB_DIR
    java -jar $DERBY_RUN_JAR ij
    rm $DERBY_LOG
    cd ..
}

# Load events from raw files into an SQL script, then execute that script.
function load_events {
    python3 $SCRIPT_DIR/convert_events.py $DATA_DIR/events/*
    mv $EVENT_SCRIPT $SCRIPT_DIR/$EVENT_SCRIPT
    run_sql $SCRIPT_DIR/$EVENT_SCRIPT
}

# Load skills from raw files into an SQL script, then execute that script.
function load_skills {
    python3 $SCRIPT_DIR/convert_skills.py $DATA_DIR/skills/*
    mv $SKILL_SCRIPT $SCRIPT_DIR/$SKILL_SCRIPT
    run_sql $SCRIPT_DIR/$SKILL_SCRIPT
}

# Rebuild the main database from scratch.
function rebuild_tables {
    run_sql $SCRIPT_DIR/drop_tables.sql
    run_sql $SCRIPT_DIR/make_tables.sql
    run_sql $SCRIPT_DIR/add_regions.sql
    load_events
    load_skills
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
    "--test")
        test_project
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
    "--load-skills")
        load_skills
        ;;
    "--rebuild-tables")
        rebuild_tables
        ;;
esac
