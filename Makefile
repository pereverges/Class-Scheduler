# JINSON PARDO

NOMJARPROJECT := GeneradorHoraris.jar

#Ubicació
NOM_DIR_SALIDA	:= bin
PROJECT					:= com/prop/fib/horaris/
DIR_ORIGEN 			:= src/$(PROJECT)
DIR_SALIDA 			:= $(NOM_DIR_SALIDA)
DIR_SALIDA_PROJ	:= $(DIR_SALIDA)/$(PROJECT)

#Eines Unix
MKDIR := mkdir -p
RM 		:= rm -rf

#Eines JAVA
JC 			:= javac
JVM 		:= java

#Llibreries
JSON 					:= lib/json-simple-1.1.1.jar
JUNIT 				:= lib/junit-4.12.jar
HAMCREST 			:= lib/hamcrest-core-1.3.jar
UI_DESIGNER 	:= lib/UiDesigner.jar

LIBRERIES := $(JSON):$(JUNIT):$(HAMCREST):$(UI_DESIGNER)

#Rutes fitxers
DATA 					:= $(DIR_ORIGEN)data/
DCLASSES 			:= $(DIR_ORIGEN)domain/Classes/
DCONTROLLERS 	:= $(DIR_ORIGEN)domain/Controllers/
DTEST					:= $(DIR_ORIGEN)domain/Test/
ENUMERATIONS 	:= $(DIR_ORIGEN)Enumerations/
PRESENTATION 	:= $(DIR_ORIGEN)presentation/

DATA2 					:= $(DIR_SALIDA_PROJ)data/
DCLASSES2 			:= $(DIR_SALIDA_PROJ)domain/Classes/
DCONTROLLERS2 	:= $(DIR_SALIDA_PROJ)domain/Controllers/
DTEST2					:= $(DIR_SALIDA_PROJ)domain/Test/
ENUMERATIONS2 	:= $(DIR_SALIDA_PROJ)Enumerations/
PRESENTATION2 	:= $(DIR_SALIDA_PROJ)presentation/

ALLCLASSESJAVA :=	$(ENUMERATIONS)*.java \
									$(DCLASSES)*.java \
									$(DTEST)*.java \
									$(DATA)*.java \
									$(DCONTROLLERS)*.java \
									$(PRESENTATION)*.java \
									$(DIR_ORIGEN)Main.java

ALLCLASSESCLASS2 := 	$(ENUMERATIONS2)*.class \
											$(DCLASSES2)*.class \
											$(DTEST2)*.class \
											$(DATA2)*.class \
											$(DCONTROLLERS2)*.class \
											$(PRESENTATION2)*.class \
											$(DIR_SALIDA_PROJ)Main.class

default: compile

compile:
	@$(MKDIR) $(NOM_DIR_SALIDA)
	@$(JC) -d $(DIR_SALIDA) -cp $(LIBRERIES) $(ALLCLASSESJAVA)

run:
	@$(JVM) -classpath $(LIBRERIES):bin $(PROJECT)Main

jar:
	@jar cf $(NOMJARPROJECT) $(ALLCLASSESCLASS2)

runjar:
	@$(JVM) -classpath $(LIBRERIES):bin $(PROJECT)Main -jar $(NOMJARPROJECT)

clean:
	@$(RM) $(NOM_DIR_SALIDA)
	@$(RM) $(NOMJARPROJECT)
