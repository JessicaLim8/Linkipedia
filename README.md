# Linkipedia

Final Project for Software Engineering 2XB3, Software Engineering Practice and Experience: Binding Theory to Practice.

Creators: Maanav Dalal, Pranay Kotian, Jessica Lim, Jay Mody

## Setup / Deploy

For the project, there are two different methods of deployment:

### 1. Via Eclipse
- In Eclipse, click **File > Open Projects From File System...**
- Choose **Archive** for the import source, and select the archived version of the project.
- Confirm that the linkipedia folder appears below, and select the internal folder 'linkipedia', then click **Finish**.
- Select Right-click the linkipedia project in the project explorer tab, and choose **Run As > Run Configurations...**
- Choose **Maven Build** on the left sidebar, press new configuration, and under the goals field, type **spring-boot:run**. 
- Ensure that your base directory is the project location. If not, select **Workspace > linkipedia**.
- Select **Run**.
- Access the deployment on https://localhost:8080

### 2. Via Script (Unix)
- Open the directory in your terminal
- Type `./mvnw spring-boot:run`
- Access the deployment on https://localhost:8080