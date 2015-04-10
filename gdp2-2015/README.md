#GitHub-Repository für GdP2 2015

Dieses [GitHub-Repository](https://github.com/fgr/whz-gdp/gdp2-2015) enthält den Quellcode zur Vorlesung ["Grundlagen der Programmierung"](http://fh-zwickau.de/~fgr/gdp) 2015.

#Zugriff auf den Quellcode

In diesem Repository befinden sich verschiedene Eclipse-Projekte. Sie können die Projekte entweder per Git oder Subversion in Ihr Eclipse importieren.

##Eclipse-Import mithilfe von Git

Führen Sie folgende Schritte aus, um ein Eclipse-Projekt aus [diesem GitHub-Repository](https://github.com/fgr/whz-gdp) in einen Eclipse-Workspace zu importieren:

1. Menü "File" > "Import" > "Git" > "Projects from Git" und mit "Next" abschließen.
2. "URI" wählen und mit "Next" abschließen.
3. Im "URI"-Feld https://github.com/fgr/whz-gdp eintragen und mit "Next" abschließen.
4. "master" auswählen (sollte schon ausgewählt sein) und mit "Next" abschließen.
5. Im "Directory"-Feld ein Verzeichnis angeben, in das die Projekte importiert werden und mit "Next" abschließen.
6. "Import existing projects" auswählen und undmit "Next" abschließen.
7. Die zu importierenden Projekte auswählen und mit "Finish" in Eclipse importieren.

##Eclipse-Import mithilfe von Subversion (SVN)

GitHubs (Git-)Repositorys sind auch SVN-Repositorys. Aus diesem Grund können Sie die Eclipse-Projekte [dieses Repository](https://github.com/fgr/whz-gdp) auch mithilfe von SVN in einen Eclipse-Workspace zu importieren. Die URL des Repositories ist https://github.com/fgr/whz-gdp.

Weitere Details finden Sie hier: https://help.github.com/articles/support-for-subversion-clients.

#JavaFX einrichten

Einige Eclipse-Projekte in [diesem GitHub-Repository](https://github.com/fgr/whz-gdp) verwenden [JavaFX 2](http://docs.oracle.com/javafx/2/). Es folgt eine Beschreibung, wie Sie JavaFX in Ihrer Eclipe-Installation einrichten.

##1. Java Development Kit einrichten

1. Die hier gezeigten Java-Projekte verwenden Programmierkonstrukte aus Java 8. Daher benötigen Sie mindestens ein Java Runtime Environment (JRE) (oder ein Java Development Kit, JDK) für Java 8. Sie können es [hier herunterladen](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  - Hinweis: Fall Sie Compile-Fehler sehen wie "Access restriction: The type Application is not accessible due to restriction on required library jfxrt.jar", dann verwendet Ihr Eclipse ein JRE, mit welchem JavaFX nicht korrekt funktioniert. Dann müssen Sie das JDK installieren.
2. Konfigurieren Sie Eclipse so, dass es das JDK verwendet, in dem Sie folgende Schritte ausführen:
  1. Menü "Window" > "Preferences" > links "Java" > "Installed JREs" wählen.
  2. Wenn in der Tabelle rechts kein *JDK* ausgewählt ist, fügen Sie mit "Add..." (rechts) denn Pfad zu einem JDK hinzu (z.B. /programs/oracle-jdk1.8.0_40).
  3. ... und aktivieren dieses JDK durch Anklicken des Hakens als Ihren Eclipse-Compiler (und -Laufzeitumgebung).
    - Compile-Fehler in der Art "Access restriction: The type Application is not accessible due to restriction on required library jfxrt.jar" sollten nun nicht mehr existieren.

##2. JavaFX via e(fx)clipse einrichten

Am einfachsten können Sie JavaFX für Eclipse konfigurieren, indem Sie [e(fx)eclipse](http://www.eclipse.org/efxclipse/index.html) in Ihr Eclipse installieren:

1. Den Marketplace in Eclipse öffnen: Im Menü Help > Eclipse Marketplace... auswählen
2. Im Suchfeld (Find) "e(fx)clipse" eingeben und "Go" klicken.
3. "Install" klicken (im Resultat "e(fx)clipse")
4. Den Anweisung folgen ...

Weitere Details zur Installation [finden Sie hier](http://www.eclipse.org/efxclipse/install.html).
