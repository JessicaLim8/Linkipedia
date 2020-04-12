# Linkipedia

Final Project for Software Engineering 2XB3, Software Engineering Practice and Experience: Binding Theory to Practice.

Creators: Maanav Dalal, Pranay Kotian, Jessica Lim, Jay Mody

## Setup Via Eclipse
Import the project given an eclipse archive zip file:
- In Eclipse, click **File > Import**.
- Choose **General > Existing Projects into Workspace**.
- Choose **Select archive file:** and select the given eclipse archive zip file. Click Finish.
- The project should now be imported into eclipse.

Run the unit tests:
- Right-click the project in package explore.
- Select **Run As > Maven Test**
- The console should appear that will load the dependencies and run the test cases.

Run the application:
- Right-click the project in package explore.
- Select **Run As > Maven Build**
- Under Goals, input `spring-boot:run`.
- Click **Apply** and then click **Run**.
- A console should load the dependencies and deploy the web app.
- Deploying the web application will take anywhere between 1-8 minutes (has to parse over 29 million lines of data). Likely, you'll see `... Parsing Nodes ...` or `... Parsing Connections/Building Graph ...` in the console. The webapp will deploy shortly after the above two lines are passed.
- Access the app in your favourite internet browser (e.g. Chrome) at https://localhost:8080

## Errors
- In the case that you get an error similar to `Application failed to build, process already running on port 8080`, you must remove any processes interacting on port 8080. If you are unable to do so, restart Eclipse, and try again. If you are still unsuccessful, restart your computer and the application should load. 
