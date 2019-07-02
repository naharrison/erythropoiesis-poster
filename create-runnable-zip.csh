#!/bin/csh -f

rm -rf erythropoiesis-poster-app erythropoiesis-poster-app.zip

mvn install
mvn clean compile assembly:single
mkdir erythropoiesis-poster-app
cp target/erythropoiesis-poster-1.0-SNAPSHOT-jar-with-dependencies.jar erythropoiesis-poster-app
chmod +x erythropoiesis-poster-app/erythropoiesis-poster-1.0-SNAPSHOT-jar-with-dependencies.jar
mv erythropoiesis-poster-app/erythropoiesis-poster-1.0-SNAPSHOT-jar-with-dependencies.jar erythropoiesis-poster-app/erythropoiesis-poster.jar
cp -r data erythropoiesis-poster-app
zip -r erythropoiesis-poster-app.zip erythropoiesis-poster-app
rm -rf erythropoiesis-poster-app

echo "unzip erythropoiesis-poster-app.zip ; cd erythropoiesis-poster-app ; java -jar erythropoiesis-poster.jar"
