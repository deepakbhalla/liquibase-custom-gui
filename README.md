# liquibase-custom-gui
This is a custom web based GUI for Liquibase integrated with Spring Boot Application and H2 Database.

This GUI has been created additionally for running the liquibase update operation from UI instead of it's default behaviour of getting triggered at server start. Also, this UI provides a feature to generate a unique changeset id which developers can use in the changelog files. This would 100% ensure the uniqueness of changeset ids across all the developers.

Run this project and launch url: http://localhost:8080/liquibase/home to view the custom liquibase GUI application.

## Custom GUI for Liquibase

![Service Logs](screenshots/1_custom_liquibase_gui_screenshot.png?raw=true "Liquibase Customm GUI")
