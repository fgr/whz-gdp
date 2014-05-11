#GitHub-Repository "whz-gdp"

Dieses [GitHub-Repository](https://github.com/fgr/whz-gdp) enthält den Quellcode zur Vorlesung ["Grundlagen der Programmierung"](http://fh-zwickau.de/~fgr/gdp).

#Zugriff auf den Quellcode

In diesem Repository befinden sich verschiedene Eclipse-Projekte. Sie können die Projekte entweder als per Git oder Subversion in Ihr Eclipse importieren.

##Eclipse-Import mithilfe von Git

Führen Sie folgende Schritte aus, um ein Eclipse-Projekt aus [diesem GitHub-Repository](https://github.com/fgr/whz-gdp) in einen Eclipse-Workspace zu importieren:

1. Menü "File" > "Import" > "Git" > "Projects from Git" und mit "Next" abschließen
2. "URI" wählen und mit "Next" abschließen
3. Im "URI"-Feld https://github.com/fgr/whz-gdp eintragen und mit "Next" abschließen
4. "master" auswählen (sollte schon ausgewählt sein) und mit "Next" abschließen
5. Im "Directory"-Feld ein Verzeichnis angeben, in das die Projekte importiert werden und mit "Next" abschließen
6. "Import existing projects" auswählen und undmit "Next" abschließen
7. Die zu importierenden Projekte auswählen und mit "Finish" in Eclipse importieren.

##Eclipse-Import mithilfe von Subversion (SVN)

Jedes Git-Repository auf GitHub ist auch ein SVN-Repository. Aus diesem Grund können Sie die Eclipse-Projekte [dieses Repository](https://github.com/fgr/whz-gdp) auch mithilfe von SVN in einen Eclipse-Workspace zu importieren. Details finden Sie hier: https://help.github.com/articles/support-for-subversion-clients.

#JavaFX einrichten

Einige Eclipse-Projekte in [diesem GitHub-Repository](https://github.com/fgr/whz-gdp) verwenden [JavaFX 2](http://docs.oracle.com/javafx/2/). Es folgt eine Beschreibung, wie Sie JavaFX in Ihrer Eclipe-Installation einrichten.

##1. Java Development Kit einrichten

1. Ihre Eclipse-Installation muss das Java *Development* Kit (JDK) als Compiler (und Laufzeitumgebung) verwenden. Das Java Runtime Environment (JRE) *reicht nicht aus*, um JavaFX-Anwendungen zu entwickeln.
  - Wenn Sie Compile-Fehler sehen wie "Access restriction: The type Application is not accessible due to restriction on required library jfxrt.jar", dann verwendet Eclipse ein JRE (und JavaFX-Programme können nicht compiliert werden).
2. Konfigurieren Sie Eclipse so, dass es das JDK verwendet, in dem Sie folgende Schritte ausführen:
  1. Menü "Window" > "Preferences" > links "Java" > "Installed JREs" wählen
  2. Wenn in der Tabelle rechts kein *JDK* ausgewählt ist, fügen Sie mit "Add..." (rechts) denn Pfad zu einem JDK hinzu (z.B. /programs/oracle-jdk1.7.0_45)
  3. ... und aktivieren dieses JDK durch Anklicken des Hakens als Ihren Eclipse-Compiler (und -Laufzeitumgebung) aus.
