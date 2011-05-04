#!/bin/sh
export JAR_FILE

if [ -z "$BRAIN_HOME" ]
then
    export BRAIN_HOME=./brains
fi

java -Xmx512m -cp "./body-1.0-SNAPSHOT-small.jar:./deps/*" se.citerus.crazysnake.gui.SwingSnakes -home $BRAIN_HOME



