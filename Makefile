all: main
main:
	javac -sourcepath src/ -d out/ src/*.java
run: main
	java -cp out/ Main
clean:
	rm -r out/