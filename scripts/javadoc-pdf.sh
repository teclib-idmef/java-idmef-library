#!/bin/bash
DOCLET=com.tarsec.javadoc.pdfdoclet.PDFDoclet
DOCLETPATH=/home/francois/softs/install/pdfdoclet-1.0.3/jar/pdfdoclet-1.0.3-all.jar:/home/francois/softs/install/pdfdoclet-1.0.3/jar/com.sun.tools-1.8.0_jdk8u275-b01_linux_x64.jar

PACKAGE=$1

HERE=$(cd $(dirname $0)/.. ; pwd)
SOURCEPATH=$HERE/src/main/java
PDF=$HERE/doc/pdf/$PACKAGE.pdf
CONFIG=$HERE/scripts/javadoc-pdf.properties

javadoc -doclet $DOCLET -docletpath $DOCLETPATH -config $CONFIG -pdf $PDF -sourcepath $SOURCEPATH $PACKAGE
